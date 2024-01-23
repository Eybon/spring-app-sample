package fr.forge.sample.spring.api.security.service;

import fr.forge.sample.spring.api.security.controller.LoginWeb;
import fr.forge.sample.spring.api.security.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class LoginService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginService(UserRepository userRepository, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Renvoie le token JWT suite à l'authentification
     */
    public String login(LoginWeb login) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.email(), login.password()));
        return jwtService.generateToken(userRepository
                .findByEmail(login.email())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password.")));
    }
}
