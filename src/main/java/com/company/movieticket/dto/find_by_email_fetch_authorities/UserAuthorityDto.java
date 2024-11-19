package com.company.movieticket.dto.find_by_email_fetch_authorities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Data;

@Data
public class UserAuthorityDto {
    @NotNull private UUID id;
    @NotNull private UUID userId;
    @NotBlank private String authority;
}
