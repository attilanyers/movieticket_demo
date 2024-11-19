package com.company.movieticket.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class UserLoginResponse {

    private String token;

    private List<String> authorities;
    @NotNull private UUID id;
}
