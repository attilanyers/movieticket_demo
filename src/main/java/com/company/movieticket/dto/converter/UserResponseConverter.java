package com.company.movieticket.dto.converter;

import com.company.movieticket.dto.UserResponse;
import com.company.movieticket.entity.UserEntity;

public class UserResponseConverter {
    public UserResponse convertTo(UserEntity source) {
        if (source == null) {
            return null;
        }
        UserResponse target = new UserResponse();
        convertTo(target, source);
        return target;
    }

    public void convertTo(UserResponse target, UserEntity source) {
        target.setId(source.getId());
        target.setEmail(source.getEmail());
    }
}
