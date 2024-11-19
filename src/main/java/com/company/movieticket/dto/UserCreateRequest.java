package com.company.movieticket.dto;

import com.company.movieticket.dto.UserRoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserCreateRequest {
    @Email @NotNull private String email;
    @NotNull private String password;
    @NotNull private UserRoleEnum role;
}
