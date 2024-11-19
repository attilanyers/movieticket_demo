package com.company.movieticket.dto.find_by_email_fetch_authorities;

import com.company.movieticket.dto.find_by_email_fetch_authorities.UserAuthorityDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class FindByEmailFetchAuthoritiesResponse {
    @NotNull private UUID id;
    @Email @NotNull private String email;

    private String passwordResetToken;

    private List<UserAuthorityDto> userAuthorities;
}
