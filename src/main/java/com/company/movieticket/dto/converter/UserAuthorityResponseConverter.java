package com.company.movieticket.dto.converter;

import com.company.movieticket.dto.UserAuthorityResponse;
import com.company.movieticket.entity.UserAuthorityEntity;

public class UserAuthorityResponseConverter {
    public UserAuthorityResponse convertTo(UserAuthorityEntity source) {
        if (source == null) {
            return null;
        }
        UserAuthorityResponse target = new UserAuthorityResponse();
        convertTo(target, source);
        return target;
    }

    public void convertTo(UserAuthorityResponse target, UserAuthorityEntity source) {
        target.setId(source.getId());
        target.setUserId(source.getUser().getId());
        target.setAuthority(source.getAuthority());
    }
}
