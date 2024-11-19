package com.company.movieticket.dto.list_user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Data;

@Data
public class ListUserResponse {
    @NotNull private UUID id;
    @Email @NotNull private String email;

    private String passwordResetToken;
}
