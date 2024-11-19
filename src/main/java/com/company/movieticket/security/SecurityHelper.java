package com.company.movieticket.security;

import com.company.movieticket.dao.UserDao;
import com.company.movieticket.entity.UserEntity;
import com.company.movieticket.entity.UserRole;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Component
public class SecurityHelper {

    private UserDao userDao;

    public SecurityHelper(UserDao userDao) {
        this.userDao = userDao;
    }

    private static ThreadLocal<UserEntity> CURRENT_USER = new ThreadLocal<>();
    public static void cleanCurrentUser() {
        CURRENT_USER.remove();
    }

    public UserEntity getCurrentUser() {
        return getCurrentUserOpt().orElseThrow(() -> accessDeniedException());
    }


    public Optional<UserEntity> getCurrentUserOpt() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
            String userId = jwt.getSubject();
            UserEntity user = CURRENT_USER.get();
            if (user == null) {
                    user = userDao.findByIdFetchAuthorities(UUID.fromString(userId))
                                    .orElseThrow(() -> accessDeniedException());
                    CURRENT_USER.set(user);
            }
            return Optional.of(user);
        }
        return Optional.empty();
    }

    public UUID getCurrentUserId() {
        return getCurrentUser().getId();
    }

    public void currentUserHasPermission(Function<UserEntity, Boolean> condition) {
        if (!condition.apply(getCurrentUser())) {
            throw accessDeniedException();
        }
    }

    public void currentUserHasAnyRole(UserRole... roles) {
        currentUserHasPermission(userEntity -> userEntity.hasAnyRole(roles));
    }

    public void currentUserEquals(UserEntity user) {
        currentUserHasPermission(currentUser -> currentUser.getId().equals(user.getId()));
    }

    public void currentUserIdEquals(UUID userId) {
        currentUserHasPermission(currentUser -> currentUser.getId().equals(userId));
    }

    public AccessDeniedException accessDeniedException() {
        return new AccessDeniedException("Forbidden");
    }
}
