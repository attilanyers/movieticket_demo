package com.company.movieticket.service;

import com.company.movieticket.dao.UserDao;
import com.company.movieticket.dto.ListUserRequest;
import com.company.movieticket.dto.UserCreateRequest;
import com.company.movieticket.dto.UserResponse;
import com.company.movieticket.dto.UserUpdateRequest;
import com.company.movieticket.dto.converter.UserCreateRequestConverter;
import com.company.movieticket.dto.converter.UserResponseConverter;
import com.company.movieticket.dto.converter.UserUpdateRequestConverter;
import com.company.movieticket.dto.converter.list_user.ListUserResponseConverter;
import com.company.movieticket.dto.list_user.ListUserResponse;
import com.company.movieticket.entity.UserEntity;
import com.company.movieticket.entity.UserRole;
import com.company.movieticket.security.SecurityHelper;
import com.company.movieticket.service.UserAuthorityService;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private UserDao userDao;
    private PasswordEncoder passwordEncoder;
    private UserAuthorityService userAuthorityService;
    private SecurityHelper securityHelper;

    public UserService(
            UserDao userDao,
            PasswordEncoder passwordEncoder,
            UserAuthorityService userAuthorityService,
            SecurityHelper securityHelper) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.userAuthorityService = userAuthorityService;
        this.securityHelper = securityHelper;
    }

    @Transactional
    public UserResponse createUser(UserCreateRequest dto) {
        securityHelper.currentUserHasAnyRole(UserRole.ADMIN);

        UserCreateRequestConverter userCreateRequestConverter = new UserCreateRequestConverter();
        UserEntity entity = userCreateRequestConverter.convertTo(dto);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));

        entity = userDao.save(entity);
        userAuthorityService.addAuthority(entity, UserRole.valueOf(dto.getRole().name()));

        UserResponseConverter userResponseConverter = new UserResponseConverter();
        return userResponseConverter.convertTo(entity);
    }

    @Transactional
    public void updateUser(UUID userId, UserUpdateRequest dto) {
        securityHelper.currentUserHasAnyRole(UserRole.ADMIN, UserRole.USER);
        if (securityHelper.getCurrentUser().hasAnyRole(UserRole.USER)) {
            securityHelper.currentUserIdEquals(userId);
        }

        UserEntity entity = userDao.findById(userId);

        UserUpdateRequestConverter converter = new UserUpdateRequestConverter();
        converter.convertTo(entity, dto);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));

        userDao.save(entity);
    }

    @Transactional
    public void deleteUser(UUID userId) {
        securityHelper.currentUserHasAnyRole(UserRole.ADMIN, UserRole.USER);
        if (securityHelper.getCurrentUser().hasAnyRole(UserRole.USER)) {
            securityHelper.currentUserIdEquals(userId);
        }

        UserEntity entity = userDao.findById(userId);

        userDao.delete(entity);
    }

    @Transactional
    public void deleteAllUser() {
        userDao.deleteAll();
    }

    public UserResponse getUser(UUID userId) {
        securityHelper.currentUserHasAnyRole(UserRole.ADMIN, UserRole.USER);
        if (securityHelper.getCurrentUser().hasAnyRole(UserRole.USER)) {
            securityHelper.currentUserIdEquals(userId);
        }

        UserEntity entity = userDao.findById(userId);

        UserResponseConverter converter = new UserResponseConverter();
        return converter.convertTo(entity);
    }

    public Page<ListUserResponse> listUser(ListUserRequest request) {
        Page<UserEntity> entities = userDao.listUser(request);
        ListUserResponseConverter converter = new ListUserResponseConverter();
        return entities.map(converter::convertTo);
    }
}
