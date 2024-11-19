package com.company.movieticket.dto.converter.list_user_authority;

import com.company.movieticket.dto.list_user_authority.ListUserAuthorityResponse;
import com.company.movieticket.entity.UserAuthorityEntity;

public class ListUserAuthorityResponseConverter {
    public ListUserAuthorityResponse convertTo(UserAuthorityEntity source) {
        if (source == null) {
            return null;
        }
        ListUserAuthorityResponse target = new ListUserAuthorityResponse();
        convertTo(target, source);
        return target;
    }

    public void convertTo(ListUserAuthorityResponse target, UserAuthorityEntity source) {
        target.setId(source.getId());
        target.setUserId(source.getUser().getId());
        target.setAuthority(source.getAuthority());
    }
}
