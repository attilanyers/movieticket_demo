package com.company.movieticket.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Data;

@Data
public class UserAuthorityCreateRequest {
    @NotNull private UUID userId;
    @NotBlank private String authority;
}
