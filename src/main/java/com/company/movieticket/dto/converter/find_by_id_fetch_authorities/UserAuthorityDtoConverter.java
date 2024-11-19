package com.company.movieticket.dto.converter.find_by_id_fetch_authorities;

import com.company.movieticket.dto.find_by_id_fetch_authorities.UserAuthorityDto;
import com.company.movieticket.entity.UserAuthorityEntity;

public class UserAuthorityDtoConverter {
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
