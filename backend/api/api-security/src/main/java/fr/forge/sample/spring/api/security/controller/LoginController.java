package fr.forge.sample.spring.api.security.controller;

import fr.forge.sample.spring.api.security.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class LoginController {

    Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    private final LoginService loginService;

    public LoginController(final LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginWeb login) {
        LOGGER.info("[API] POST /login for mail : {}", login.email());
        return ResponseEntity.ok(loginService.login(login));
    }
}
