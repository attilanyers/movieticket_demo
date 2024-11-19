package com.company.movieticket.test;

import com.company.movieticket.dto.UserCreateRequest;
import com.company.movieticket.dto.UserRoleEnum;
import com.company.movieticket.entity.UserAuthorityEntity;
import com.company.movieticket.entity.UserEntity;
import com.company.movieticket.entity.UserRole;
import com.company.movieticket.repository.UserAuthorityRepository;
import com.company.movieticket.repository.UserRepository;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import static io.restassured.RestAssured.given;

import static org.assertj.core.api.Assertions.assertThat;

@Component
public class UserHelper {
    private UserInfo adminInfo;
    private UserInfo userInfo;

    @Autowired private UserRepository userRepository;

    @Autowired private UserAuthorityRepository userAuthorityRepository;

    public UserInfo getOrCreateAdminInfo() {
        if (adminInfo == null) {
            adminInfo = new UserInfo(createAdmin(), userRepository);
        }
        return adminInfo;
    }

    private UserCreateRequest createAdmin() {
        UserCreateRequest request = new UserCreateRequest();

        request.setEmail("geraldo-842@email.com");

        request.setPassword("password");

        request.setRole(UserRoleEnum.USER);

        Response response = given().body(request).post("/users");
        assertThat(response.statusCode()).isEqualTo(200);
        UserEntity user =
                userRepository.findByEmailFetchAuthorities(request.getEmail()).orElseThrow();
        UserAuthorityEntity authority = user.getAuthorities().get(0);
        authority.setAuthority(UserRole.ROLE_ADMIN);
        userAuthorityRepository.save(authority);

        return request;
    }

    public UserInfo getOrCreateUserInfo() {
        if (userInfo == null) {
            userInfo = new UserInfo(createUser(), userRepository);
        }
        return userInfo;
    }

    private UserCreateRequest createUser() {
        UserCreateRequest request = new UserCreateRequest();

        request.setEmail("lea-671@email.com");

        request.setPassword("password");

        request.setRole(UserRoleEnum.USER);

        Response response = given().body(request).post("/users");
        assertThat(response.statusCode()).isEqualTo(200);

        return request;
    }
}
