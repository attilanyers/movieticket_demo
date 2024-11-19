package com.company.movieticket.controller;

import com.company.movieticket.dto.UserLoginRequest;
import com.company.movieticket.dto.UserLoginResponse;
import com.company.movieticket.dto.RequestPasswordResetRequest;
import com.company.movieticket.dto.PasswordResetRequest;
import com.company.movieticket.security.AuthService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public UserLoginResponse login(@RequestBody @Valid UserLoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/requestPasswordReset")
    public void requestPasswordReset(@RequestBody @Valid RequestPasswordResetRequest request) {
        authService.requestPasswordReset(request);
    }

    @PostMapping("/passwordReset")
    public void passwordReset(@RequestBody @Valid PasswordResetRequest request) {
        authService.passwordReset(request);
    }
}
