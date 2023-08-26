package com.example.projectperfectus.Controller;


import com.example.projectperfectus.Entite.User;
import com.example.projectperfectus.Entite.changePwd;
import com.example.projectperfectus.Repositorie.UserRepository;
import com.example.projectperfectus.Service.EmailServiceImpl;
import com.example.projectperfectus.Service.IUserService;
import com.example.projectperfectus.utils.PagingHeaders;
import com.example.projectperfectus.utils.PagingResponse;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import lombok.Data;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserRestController {
    @Autowired
    private EmailServiceImpl emailService;
    @Autowired
    IUserService userService;
    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserRestController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/send-mail")
    public void sendMail(@RequestBody Email email) {
        emailService.sendSimpleEmail(email.getTo(), email.getSubject(), email.getBody());

    }
    @GetMapping("/show-all-users")
    public List<User> retriveAllUsers() {
        List<User> listUsers = userService.retrieveAllUsers();
        return listUsers;
    }

    @GetMapping("/show-user/{id}")
    public User retrieveUser(@Valid @PathVariable("id") Long id) {
        return userService.retrieveUser(id);
    }

    @DeleteMapping("/remove-user/{id}")
    public ResponseEntity<String> removeUser(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return ResponseEntity.ok("User deleted successfully!");
    }
    @Transactional
    @GetMapping(value = "/recherche-avancee", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<User>> get(
            @And({
                    @Spec(path = "nomUser", params = "nomUser", spec = Like.class),
                    @Spec(path = "emailUser", params = "emailUser", spec = Like.class),
                    @Spec(path = "numdetelUser", params = "numdetelUser", spec = Like.class),
                    @Spec(path = "role", params = "role", spec = Like.class),
            }) Specification<User> spec, Sort sort, @RequestHeader HttpHeaders headers) {
        final PagingResponse response = userService.get3(spec, headers, sort);
        return new ResponseEntity<>(response.getElements(), returnHttpHeaders(response), HttpStatus.OK);

    }

    public HttpHeaders returnHttpHeaders(PagingResponse response) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(PagingHeaders.COUNT.getName(), String.valueOf(response.getCount()));
        headers.set(PagingHeaders.PAGE_SIZE.getName(), String.valueOf(response.getPageSize()));
        headers.set(PagingHeaders.PAGE_OFFSET.getName(), String.valueOf(response.getPageOffset()));
        headers.set(PagingHeaders.PAGE_NUMBER.getName(), String.valueOf(response.getPageNumber()));
        headers.set(PagingHeaders.PAGE_TOTAL.getName(), String.valueOf(response.getPageTotal()));
        return headers;
    }
    @PostMapping("/update-password")
    public ResponseEntity<?>  updatePassword(@RequestParam String email, @RequestParam String oldPWD, @RequestParam String newPWD) {
        User user = userRepository.findByEmailUser(email).get();
        boolean isMatch = passwordEncoder.matches(oldPWD, user.getPasswordUser());
        if (isMatch) {
            String encodedNewPassword = passwordEncoder.encode(newPWD);
            user.setPasswordUser(encodedNewPassword);
            userRepository.save(user);
            return ResponseEntity.ok("DONE");
        } else {
            return ResponseEntity.ok("Wrong Password");
        }
    }
    @GetMapping("/qrcode")
    public ResponseEntity<byte[]> generateQrCode(@RequestParam("data") String data,
                                                 @RequestParam(value = "format", defaultValue = "png") String format)
            throws Exception {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        int width = 300;
        int height = 300;
        BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, width, height);
        MatrixToImageWriter.writeToStream(matrix, format, stream);
        byte[] imageBytes = stream.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(imageBytes.length);
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }


    @PostMapping("/forgot-password/{email}")
    public String forgotPassword(@PathVariable (value = "email") String email) {
        Optional<User> user=userRepository.findByEmailUser(email);
        if(user.isPresent()){
            String newPassword = RandomStringUtils.randomAlphanumeric(10);
            emailService.sendSimpleEmail(email, "Password Reset", newPassword);
            user.get().setPasswordUser(passwordEncoder.encode(newPassword));
            userService.updateUser(user.get());
            return "Password reset email sent";
        }else {
            return "User not found";
        }

    }

    @GetMapping("/getusers")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }


    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable (value = "id") Long id){
        return userService.deleteUser(id);
    }

    @GetMapping("/getPrincipal/{id}")
    public User getPrincipal(@PathVariable (value = "id") Long id){
        return userService.getPrincipal(id);
    }


    @PostMapping("/updatePrincipal/{id}")
    public String updateP(@PathVariable (value = "id") Long id , @RequestBody User user){
        return userService.editPrincipal(id,user);
    }


    @PostMapping("/changepassword/{id}")
    public String changePsw(@PathVariable (value = "id") Long id , @RequestBody changePwd c){
        return userService.changepassword(id,c);
    }
}

@Data
class Email{
    private String to;
    private  String subject;
    private String body;
}
