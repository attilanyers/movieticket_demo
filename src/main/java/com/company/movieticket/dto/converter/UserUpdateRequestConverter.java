package com.company.movieticket.dto.converter;

import com.company.movieticket.dto.UserUpdateRequest;
import com.company.movieticket.entity.UserEntity;

public class UserUpdateRequestConverter {
    public UserEntity convertTo(UserUpdateRequest source) {
        if (source == null) {
            return null;
        }
        UserEntity target = new UserEntity();
        convertTo(target, source);
        return target;
    }

    public void convertTo(UserEntity target, UserUpdateRequest source) {
        target.setEmail(source.getEmail());
        // skip attribute: 'password'
    }
}
