package com.company.movieticket.controller;

import com.company.movieticket.dto.ListUserAuthorityRequest;
import com.company.movieticket.dto.PageResponse;
import com.company.movieticket.dto.UserAuthorityCreateRequest;
import com.company.movieticket.dto.UserAuthorityResponse;
import com.company.movieticket.dto.UserAuthorityUpdateRequest;
import com.company.movieticket.dto.list_user_authority.ListUserAuthorityResponse;
import com.company.movieticket.entity.UserRole;
import com.company.movieticket.service.UserAuthorityService;
import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAuthorityController {

    private UserAuthorityService userAuthorityService;

    public UserAuthorityController(UserAuthorityService userAuthorityService) {
        this.userAuthorityService = userAuthorityService;
    }

    @PostMapping("/userAuthorities")
    @Secured(UserRole.ROLE_ADMIN)
    public UserAuthorityResponse createUserAuthority(
            @RequestBody @Valid UserAuthorityCreateRequest dto) {
        return userAuthorityService.createUserAuthority(dto);
    }

    @PutMapping("/userAuthorities/{userAuthorityId}")
    @Secured(UserRole.ROLE_ADMIN)
    public void updateUserAuthority(
            @PathVariable UUID userAuthorityId,
            @RequestBody @Valid UserAuthorityUpdateRequest dto) {
        userAuthorityService.updateUserAuthority(userAuthorityId, dto);
    }

    @DeleteMapping("/userAuthorities/{userAuthorityId}")
    @Secured(UserRole.ROLE_ADMIN)
    public void deleteUserAuthority(@PathVariable UUID userAuthorityId) {
        userAuthorityService.deleteUserAuthority(userAuthorityId);
    }

    @DeleteMapping("/userAuthorities")
    @Secured(UserRole.ROLE_ADMIN)
    public void deleteAllUserAuthority() {
        userAuthorityService.deleteAllUserAuthority();
    }

    @GetMapping("/userAuthorities/{userAuthorityId}")
    @Secured({UserRole.ROLE_USER, UserRole.ROLE_ADMIN})
    public UserAuthorityResponse getUserAuthority(@PathVariable UUID userAuthorityId) {
        return userAuthorityService.getUserAuthority(userAuthorityId);
    }

    @GetMapping("/userAuthorities/listUserAuthority")
    @Secured(UserRole.ROLE_ADMIN)
    public PageResponse<ListUserAuthorityResponse> listUserAuthority(
            @Valid ListUserAuthorityRequest request) {
        return PageResponse.of(userAuthorityService.listUserAuthority(request));
    }
}
