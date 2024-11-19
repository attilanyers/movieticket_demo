package com.company.movieticket.controller;

import com.company.movieticket.dto.ListUserRequest;
import com.company.movieticket.dto.PageResponse;
import com.company.movieticket.dto.UserCreateRequest;
import com.company.movieticket.dto.UserResponse;
import com.company.movieticket.dto.UserUpdateRequest;
import com.company.movieticket.dto.list_user.ListUserResponse;
import com.company.movieticket.entity.UserRole;
import com.company.movieticket.service.UserService;
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
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    @Secured(UserRole.ROLE_ADMIN)
    public UserResponse createUser(@RequestBody @Valid UserCreateRequest dto) {
        return userService.createUser(dto);
    }

    @PutMapping("/users/{userId}")
    @Secured({UserRole.ROLE_USER, UserRole.ROLE_ADMIN})
    public void updateUser(@PathVariable UUID userId, @RequestBody @Valid UserUpdateRequest dto) {
        userService.updateUser(userId, dto);
    }

    @DeleteMapping("/users/{userId}")
    @Secured({UserRole.ROLE_USER, UserRole.ROLE_ADMIN})
    public void deleteUser(@PathVariable UUID userId) {
        userService.deleteUser(userId);
    }

    @DeleteMapping("/users")
    @Secured(UserRole.ROLE_ADMIN)
    public void deleteAllUser() {
        userService.deleteAllUser();
    }

    @GetMapping("/users/{userId}")
    @Secured({UserRole.ROLE_USER, UserRole.ROLE_ADMIN})
    public UserResponse getUser(@PathVariable UUID userId) {
        return userService.getUser(userId);
    }

    @GetMapping("/users/listUser")
    @Secured(UserRole.ROLE_ADMIN)
    public PageResponse<ListUserResponse> listUser(@Valid ListUserRequest request) {
        return PageResponse.of(userService.listUser(request));
    }
}
