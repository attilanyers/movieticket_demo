package com.company.movieticket.dto.converter;

import com.company.movieticket.dto.UserAuthorityUpdateRequest;
import com.company.movieticket.entity.UserAuthorityEntity;
import com.company.movieticket.entity.UserEntity;

public class UserAuthorityUpdateRequestConverter {
    public UserAuthorityEntity convertTo(UserAuthorityUpdateRequest source, UserEntity user) {
        if (source == null) {
            return null;
        }
        UserAuthorityEntity target = new UserAuthorityEntity();
        convertTo(target, source, user);
        return target;
    }

    public void convertTo(
            UserAuthorityEntity target, UserAuthorityUpdateRequest source, UserEntity user) {
        target.setUser(user);
        target.setAuthority(source.getAuthority());
    }
}
