package com.company.movieticket.dto.converter.find_by_email_fetch_authorities;

import com.company.movieticket.dto.converter.find_by_email_fetch_authorities.UserAuthorityDtoConverter;
import com.company.movieticket.dto.find_by_email_fetch_authorities.FindByEmailFetchAuthoritiesResponse;
import com.company.movieticket.entity.UserEntity;

public class FindByEmailFetchAuthoritiesResponseConverter {
    public FindByEmailFetchAuthoritiesResponse convertTo(UserEntity source) {
        if (source == null) {
            return null;
        }
        FindByEmailFetchAuthoritiesResponse target = new FindByEmailFetchAuthoritiesResponse();
        convertTo(target, source);
        return target;
    }

    public void convertTo(FindByEmailFetchAuthoritiesResponse target, UserEntity source) {
        target.setId(source.getId());
        target.setEmail(source.getEmail());
        target.setPasswordResetToken(source.getPasswordResetToken());
        UserAuthorityDtoConverter userAuthorityDtoConverter = new UserAuthorityDtoConverter();

        target.setUserAuthorities(
                source.getUserAuthorities().stream()
                        .map(userAuthorityDtoConverter::convertTo)
                        .toList());
    }
}
