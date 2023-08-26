package com.example.projectperfectus.Authentication;


import com.example.projectperfectus.Service.EmailServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/authentication")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("**")
public class AuthenticationController {
    private final AuthenticationService service;
    @Autowired
    private EmailServiceImpl emailService;


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticatonRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return ResponseEntity.ok("User logged out successfully.");
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        emailService.sendSimpleEmail(request.getEmail(), "Welcome Mail", "Welcome Mail");
        System.out.print("Account successfully created");
        return ResponseEntity.ok(service.register(request));
    }

}
