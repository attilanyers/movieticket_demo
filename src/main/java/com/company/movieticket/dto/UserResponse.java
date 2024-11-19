package com.company.movieticket.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Data;

@Data
public class UserResponse {
    @NotNull private UUID id;
    @Email @NotNull private String email;
}
