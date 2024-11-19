package com.company.movieticket.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestPasswordResetRequest {
    @Email @NotNull private String email;
}