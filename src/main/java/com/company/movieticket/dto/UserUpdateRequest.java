package com.company.movieticket.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserUpdateRequest {
    @Email @NotNull private String email;
    @NotNull private String password;
}
