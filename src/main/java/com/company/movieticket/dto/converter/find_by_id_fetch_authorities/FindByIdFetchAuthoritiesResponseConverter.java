package com.company.movieticket.dto.converter.find_by_id_fetch_authorities;

import com.company.movieticket.dto.converter.find_by_id_fetch_authorities.UserAuthorityDtoConverter;
import com.company.movieticket.dto.find_by_id_fetch_authorities.FindByIdFetchAuthoritiesResponse;
import com.company.movieticket.entity.UserEntity;

public class FindByIdFetchAuthoritiesResponseConverter {
    public FindByIdFetchAuthoritiesResponse convertTo(UserEntity source) {
        if (source == null) {
            return null;
        }
        FindByIdFetchAuthoritiesResponse target = new FindByIdFetchAuthoritiesResponse();
        convertTo(target, source);
        return target;
    }

    public void convertTo(FindByIdFetchAuthoritiesResponse target, UserEntity source) {
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
