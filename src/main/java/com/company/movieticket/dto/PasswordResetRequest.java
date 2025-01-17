package com.company.movieticket.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PasswordResetRequest {
    @Email @NotNull private String email;
    @NotBlank private String password;
    @NotBlank private String token;
}
