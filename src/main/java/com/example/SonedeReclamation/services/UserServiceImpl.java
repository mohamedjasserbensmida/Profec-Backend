package com.example.SonedeReclamation.services;

import com.example.SonedeReclamation.entities.User;
import com.example.SonedeReclamation.entities.changePwd;
import com.example.SonedeReclamation.interfaces.IUserService;
import com.example.SonedeReclamation.repositories.UserRepository;
import com.example.SonedeReclamation.utils.PagingHeaders;
import com.example.SonedeReclamation.utils.PagingResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserRepository ur;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> retrieveAllUsers() {
        return ur.findAll();
    }
    @Override
    public User retrieveUser(Long id) {
        return ur.findById(id).orElse(null);
    }
    @Override
    public User updateUser(User user) {
        return ur.save(user);
    }
    @Override
    public User addUser(User user) {
        return ur.save(user);
    }
    @Override
    public void removeUser(Long id) {
        if ( retrieveUser(id)==null)
        {
            System.out.print("USER DOES NOT EXISTS");
        }
        ur.deleteById(id);
    }

    public PagingResponse get3(Specification<User> spec, HttpHeaders headers, Sort sort) {
        if (isRequestPaged(headers)) {
            return get(spec, buildPageRequest(headers, sort));
        } else {
            final List<User> entities = get(spec,sort);
            return new PagingResponse((long) entities.size(), 0L, 0L, 0L, 0L, entities);
        }
    }

    public List<User> get(Specification<User> spec, Sort sort) {
        return ur.findAll(spec,sort);
    }
    private boolean isRequestPaged(HttpHeaders headers) {
        return headers.containsKey(PagingHeaders.PAGE_NUMBER.getName()) && headers.containsKey(PagingHeaders.PAGE_SIZE.getName());
    }
    private Pageable buildPageRequest(HttpHeaders headers, Sort sort) {
        int page = Integer.parseInt(Objects.requireNonNull(headers.get(PagingHeaders.PAGE_NUMBER.getName())).get(0));
        int size = Integer.parseInt(Objects.requireNonNull(headers.get(PagingHeaders.PAGE_SIZE.getName())).get(0));
        return PageRequest.of(page, size, sort);
    }
    public PagingResponse get(Specification<User> spec, Pageable pageable) {
        Page<User> page = ur.findAll(spec, pageable);
        List<User> content = page.getContent();
        return new PagingResponse(page.getTotalElements(), (long) page.getNumber(), (long) page.getNumberOfElements(), pageable.getOffset(), (long) page.getTotalPages(), content);
    }


    @Override
    public List<User> getAllUsers(){

        return ur.findAll();
    }

    @Override
    public String deleteUser(Long id) {
        ur.deleteById(id);
        return "User Deleted";
    }
    public User getPrincipal(Long id){
        return ur.findById(id).get();
    }

    @Override
    public String editPrincipal(Long id, User user) {
        User user1=ur.findById(id).get();
        user1.setNomUser(user.getNomUser());
        user1.setEmailUser(user.getEmailUser());
        user1.setNumdetelUser(user.getNumdetelUser());
        ur.save(user1);
        return "Profile updated";
    }

    @Override
    public String changepassword(Long id, changePwd pwd) {
        User user = ur.findById(id).get();
        String oldPwd = user.getPasswordUser();
        if (passwordEncoder.matches(pwd.getOldpassword(), oldPwd)) {
            if (pwd.getPassword().equals(pwd.getPassword2())) {
                user.setPasswordUser(passwordEncoder.encode(pwd.getPassword()));
                ur.save(user);
                return "password changed";
            } else {
                return "Confirm password !!";
            }
        } else {
            return "Invalid Current password ";
        }
    }

}
