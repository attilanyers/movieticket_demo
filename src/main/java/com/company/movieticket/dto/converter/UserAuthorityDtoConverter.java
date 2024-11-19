package com.company.movieticket.dto.converter;

import com.company.movieticket.dto.UserAuthorityDto;
import com.company.movieticket.entity.UserAuthorityEntity;
import com.company.movieticket.entity.UserEntity;

public class UserAuthorityDtoConverter {
    public UserAuthorityEntity convertTo(UserAuthorityDto source, UserEntity user) {
        if (source == null) {
            return null;
        }
        UserAuthorityEntity target = new UserAuthorityEntity();
        convertTo(target, source, user);
        return target;
    }

    public void convertTo(UserAuthorityEntity target, UserAuthorityDto source, UserEntity user) {
        target.setId(source.getId());
        target.setUser(user);
        target.setAuthority(source.getAuthority());
    }

    public UserAuthorityDto convertTo(UserAuthorityEntity source) {
        if (source == null) {
            return null;
        }
        UserAuthorityDto target = new UserAuthorityDto();
        convertTo(target, source);
        return target;
    }

    public void convertTo(UserAuthorityDto target, UserAuthorityEntity source) {
        target.setId(source.getId());
        target.setUserId(source.getUser().getId());
        target.setAuthority(source.getAuthority());
    }
}
