package com.example.projectperfectus.Authentication;


import com.example.projectperfectus.Configuration.JwtService;
import com.example.projectperfectus.Entite.User;
import com.example.projectperfectus.Repositorie.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import static com.example.projectperfectus.Entite.Role.admin;
import static com.example.projectperfectus.Entite.Role.client;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        User user = null;
        if (request.getRole()==client){
            user = User.builder()
                    .nomUser(request.getName())
                    .emailUser(request.getEmail())
                    .numdetelUser(request.getPhoneNumber())
                    .passwordUser(passwordEncoder.encode(request.getPassword()))
                    .role(client)
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

    public AuthenticationResponse authenticate(AuthenticatonRequest request) {
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
