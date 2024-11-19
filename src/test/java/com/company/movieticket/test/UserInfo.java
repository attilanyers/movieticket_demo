package com.company.movieticket.test;

import com.company.movieticket.dto.UserCreateRequest;
import com.company.movieticket.dto.UserLoginRequest;
import com.company.movieticket.dto.UserLoginResponse;
import com.company.movieticket.entity.UserEntity;
import com.company.movieticket.repository.UserRepository;
import io.restassured.http.Header;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import static org.assertj.core.api.Assertions.assertThat;

public class UserInfo {
    private UserCreateRequest createRequest;
    private UserRepository userRepository;
    private UserLoginResponse loginResponse;
    private UserEntity entity;

    public UserInfo(UserCreateRequest createRequest, UserRepository userRepository) {
        this.createRequest = createRequest;
        this.userRepository = userRepository;
    }

    private String getAuthToken() {
        if (loginResponse == null) {
            UserLoginRequest request = new UserLoginRequest();
            request.setEmail(createRequest.getEmail());
            request.setPassword(createRequest.getPassword());
            Response response = given().body(request).post("/auth/login");
            assertThat(response.statusCode()).isEqualTo(200);
            loginResponse = response.as(UserLoginResponse.class);
        }
        return loginResponse.getToken();
    }

    public UserEntity getEntity() {
        if (entity == null) {
            entity =
                    userRepository
                            .findByEmailFetchAuthorities(createRequest.getEmail())
                            .orElseThrow();
        }
        return entity;
    }

    public Header getAuthHeader() {
        return new Header("Authorization", "Bearer " + getAuthToken());
    }
}
