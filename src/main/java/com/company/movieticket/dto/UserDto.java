package com.company.movieticket.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Data;

@Data
public class UserDto {
    @NotNull private UUID id;
    @Email @NotNull private String email;
    @NotNull private String password;

    private String passwordResetToken;
}
