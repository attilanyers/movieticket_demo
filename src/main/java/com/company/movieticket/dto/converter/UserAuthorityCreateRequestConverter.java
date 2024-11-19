package com.company.movieticket.dto.converter;

import com.company.movieticket.dto.UserAuthorityCreateRequest;
import com.company.movieticket.entity.UserAuthorityEntity;
import com.company.movieticket.entity.UserEntity;

public class UserAuthorityCreateRequestConverter {
    public UserAuthorityEntity convertTo(UserAuthorityCreateRequest source, UserEntity user) {
        if (source == null) {
            return null;
        }
        UserAuthorityEntity target = new UserAuthorityEntity();
        convertTo(target, source, user);
        return target;
    }

    public void convertTo(
            UserAuthorityEntity target, UserAuthorityCreateRequest source, UserEntity user) {
        target.setUser(user);
        target.setAuthority(source.getAuthority());
    }
}
