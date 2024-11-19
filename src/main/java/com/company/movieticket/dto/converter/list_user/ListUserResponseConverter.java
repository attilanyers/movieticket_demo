package com.company.movieticket.dto.converter.list_user;

import com.company.movieticket.dto.list_user.ListUserResponse;
import com.company.movieticket.entity.UserEntity;

public class ListUserResponseConverter {
    public ListUserResponse convertTo(UserEntity source) {
        if (source == null) {
            return null;
        }
        ListUserResponse target = new ListUserResponse();
        convertTo(target, source);
        return target;
    }

    public void convertTo(ListUserResponse target, UserEntity source) {
        target.setId(source.getId());
        target.setEmail(source.getEmail());
        target.setPasswordResetToken(source.getPasswordResetToken());
    }
}
