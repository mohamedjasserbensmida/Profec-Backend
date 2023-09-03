package com.example.SonedeReclamation.authentication;

import com.example.SonedeReclamation.configuration.JwtService;
import com.example.SonedeReclamation.entities.User;
import com.example.SonedeReclamation.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.example.SonedeReclamation.entities.Role.*;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        User user = null;
        if (request.getRole()==employee){
            user = User.builder()
                    .nomUser(request.getName())
                    .emailUser(request.getEmail())
                    .numdetelUser(request.getPhoneNumber())
                    .passwordUser(passwordEncoder.encode(request.getPassword()))
                    .dateCreation(new Date())
                    .role(employee)

                    .build();
            repository.save(user);
        }

        else if (request.getRole()==admin){
            user = User.builder()
                    .nomUser(request.getName())
                    .emailUser(request.getEmail())
                    .numdetelUser(request.getPhoneNumber())
                    .passwordUser(passwordEncoder.encode(request.getPassword()))
                    .role(admin)
                    .build();
            repository.save(user);
        }


        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmailUser(request.getEmail());
        var role=user.get().getRole();
        var userId=user.get().getIdUser();
        var jwtToken = jwtService.generateToken(user.get());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .role(role.toString())
                .id(userId)
                .build();
    }
}
