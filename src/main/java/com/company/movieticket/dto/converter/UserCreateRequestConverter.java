package com.company.movieticket.dto.converter;

import com.company.movieticket.dto.UserCreateRequest;
import com.company.movieticket.entity.UserEntity;

public class UserCreateRequestConverter {
    public UserEntity convertTo(UserCreateRequest source) {
        if (source == null) {
            return null;
        }
        UserEntity target = new UserEntity();
        convertTo(target, source);
        return target;
    }

    public void convertTo(UserEntity target, UserCreateRequest source) {
        target.setEmail(source.getEmail());
        // skip attribute: 'password'
    }
}
