package com.company.movieticket.service;

import com.company.movieticket.dao.UserAuthorityDao;
import com.company.movieticket.dao.UserDao;
import com.company.movieticket.dto.ListUserAuthorityRequest;
import com.company.movieticket.dto.UserAuthorityCreateRequest;
import com.company.movieticket.dto.UserAuthorityResponse;
import com.company.movieticket.dto.UserAuthorityUpdateRequest;
import com.company.movieticket.dto.converter.UserAuthorityCreateRequestConverter;
import com.company.movieticket.dto.converter.UserAuthorityResponseConverter;
import com.company.movieticket.dto.converter.UserAuthorityUpdateRequestConverter;
import com.company.movieticket.dto.converter.list_user_authority.ListUserAuthorityResponseConverter;
import com.company.movieticket.dto.list_user_authority.ListUserAuthorityResponse;
import com.company.movieticket.entity.UserAuthorityEntity;
import com.company.movieticket.entity.UserEntity;
import com.company.movieticket.entity.UserRole;
import com.company.movieticket.security.SecurityHelper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserAuthorityService {
    private UserDao userDao;
    private UserAuthorityDao userAuthorityDao;
    private SecurityHelper securityHelper;

    public UserAuthorityService(UserDao userDao, UserAuthorityDao userAuthorityDao,
                                SecurityHelper securityHelper) {
        this.userDao = userDao;
        this.userAuthorityDao = userAuthorityDao;
        this.securityHelper = securityHelper;
    }

    @Transactional
    public void addAuthority(UserEntity user, UserRole role) {
        if (role != UserRole.USER) {
            securityHelper.currentUserHasAnyRole(UserRole.ADMIN);
        }
        UserAuthorityEntity userAuthority = new UserAuthorityEntity();
        userAuthority.setUser(user);
        userAuthority.setAuthority(role.name());
        userAuthorityDao.save(userAuthority);
    }

    @Transactional
    public UserAuthorityResponse createUserAuthority(UserAuthorityCreateRequest dto) {
        securityHelper.currentUserHasAnyRole(UserRole.ADMIN);
        UserEntity user = userDao.findById(dto.getUserId());

        UserAuthorityCreateRequestConverter userAuthorityCreateRequestConverter =
                new UserAuthorityCreateRequestConverter();
        UserAuthorityEntity entity = userAuthorityCreateRequestConverter.convertTo(dto, user);

        entity = userAuthorityDao.save(entity);

        UserAuthorityResponseConverter userAuthorityResponseConverter =
                new UserAuthorityResponseConverter();
        return userAuthorityResponseConverter.convertTo(entity);
    }

    @Transactional
    public void updateUserAuthority(UUID userAuthorityId, UserAuthorityUpdateRequest dto) {
        securityHelper.currentUserHasAnyRole(UserRole.ADMIN);
        UserAuthorityEntity entity = userAuthorityDao.findById(userAuthorityId);

        UserEntity user = userDao.findById(dto.getUserId());

        UserAuthorityUpdateRequestConverter converter = new UserAuthorityUpdateRequestConverter();
        converter.convertTo(entity, dto, user);

        userAuthorityDao.save(entity);
    }

    @Transactional
    public void deleteUserAuthority(UUID userAuthorityId) {
        securityHelper.currentUserHasAnyRole(UserRole.ADMIN);
        UserAuthorityEntity entity = userAuthorityDao.findById(userAuthorityId);

        userAuthorityDao.delete(entity);
    }

    @Transactional
    public void deleteAllUserAuthority() {
        userAuthorityDao.deleteAll();
    }

    public UserAuthorityResponse getUserAuthority(UUID userAuthorityId) {
        securityHelper.currentUserHasAnyRole(UserRole.ADMIN, UserRole.USER);
        UserAuthorityEntity entity = userAuthorityDao.findById(userAuthorityId);

        if (securityHelper.getCurrentUser().hasAnyRole(UserRole.USER)) {
            securityHelper.currentUserEquals(entity.getUser());
        }

        UserAuthorityResponseConverter converter = new UserAuthorityResponseConverter();
        return converter.convertTo(entity);
    }

    public Page<ListUserAuthorityResponse> listUserAuthority(ListUserAuthorityRequest request) {
        Page<UserAuthorityEntity> entities = userAuthorityDao.listUserAuthority(request);
        ListUserAuthorityResponseConverter converter = new ListUserAuthorityResponseConverter();
        return entities.map(converter::convertTo);
    }
}
