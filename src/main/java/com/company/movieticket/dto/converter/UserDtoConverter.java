package com.company.movieticket.dto.converter;

import com.company.movieticket.dto.UserDto;
import com.company.movieticket.entity.UserEntity;

public class UserDtoConverter {
    public UserEntity convertTo(UserDto source) {
        if (source == null) {
            return null;
        }
        UserEntity target = new UserEntity();
        convertTo(target, source);
        return target;
    }

    public void convertTo(UserEntity target, UserDto source) {
        target.setId(source.getId());
        target.setEmail(source.getEmail());
        // skip attribute: 'password'
        target.setPasswordResetToken(source.getPasswordResetToken());
    }

    public UserDto convertTo(UserEntity source) {
        if (source == null) {
            return null;
        }
        UserDto target = new UserDto();
        convertTo(target, source);
        return target;
    }

    public void convertTo(UserDto target, UserEntity source) {
        target.setId(source.getId());
        target.setEmail(source.getEmail());
        // skip attribute: 'password'
        target.setPasswordResetToken(source.getPasswordResetToken());
    }
}
