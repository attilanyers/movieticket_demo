package com.company.movieticket.dto.converter;

import com.company.movieticket.dto.UserRoleEnum;
import com.company.movieticket.entity.UserRole;

public class UserRoleConverter {

    public UserRole convert(UserRoleEnum value) {
        return switch (value) {
            case ADMIN -> UserRole.ADMIN;
            case USER -> UserRole.USER;
        };
    }

    public UserRoleEnum convert(UserRole value) {
        return switch (value) {
            case ADMIN -> UserRoleEnum.ADMIN;
            case USER -> UserRoleEnum.USER;
        };
    }
}
