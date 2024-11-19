package com.company.movieticket.test;

import com.company.movieticket.dto.ListUserRequest;
import com.company.movieticket.dto.PageResponse;
import com.company.movieticket.dto.UserCreateRequest;
import com.company.movieticket.dto.UserResponse;
import com.company.movieticket.dto.UserUpdateRequest;
import com.company.movieticket.dto.list_user.ListUserResponse;
import com.company.movieticket.entity.UserEntity;
import com.company.movieticket.repository.UserRepository;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static io.restassured.RestAssured.given;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(TestSetup.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserTest {
    @Autowired UserRepository userRepository;
    @Autowired EntityHelper entityHelper;
    @Autowired UserHelper userHelper;

    @Test
    void createUser() {
        // given
        UserCreateRequest request = new UserCreateRequest();
        // when
        Response response =
                given().header(userHelper.getOrCreateAdminInfo().getAuthHeader())
                        .body(request)
                        .post("/users");

        // then
        assertThat(response.statusCode()).isEqualTo(200);
        UserResponse actual = response.as(UserResponse.class);
        assertThat(actual.getEmail()).isEqualTo(request.getEmail());
    }

    @Test
    void updateUser() {
        // given
        UserEntity userEntity = new UserEntity();

        userEntity.setEmail("loni-677@email.com");

        userEntity.setPassword("$2a$10$T3XESiutfshIai9J/yslqeNYClcVK0kNaIVc2E6Uz/6BBzJ4ViQoi");

        userEntity.setPasswordResetToken("passwordResetToken-696");

        userEntity = userRepository.save(userEntity);

        UserUpdateRequest request = new UserUpdateRequest();
        UUID userId = userEntity.getId();
        // when
        Response response =
                given().header(userHelper.getOrCreateUserInfo().getAuthHeader())
                        .body(request)
                        .put("/users/{userId}", userId);

        // then
        assertThat(response.statusCode()).isEqualTo(200);
        Optional<UserEntity> actualOpt = userRepository.findById(userEntity.getId());
        assertThat(actualOpt.isPresent());
        UserEntity actual = actualOpt.orElseThrow();
        assertThat(actual.getEmail()).isEqualTo(request.getEmail());
        assertThat(actual.getPassword()).isEqualTo(request.getPassword());
    }

    @Test
    void deleteUser() {
        // given
        UserEntity userEntity = new UserEntity();

        userEntity.setEmail("sung-782@email.com");

        userEntity.setPassword("$2a$10$5uXhB5dBHCzV156VCkZwcOBVdtUWZ/EtzT6U376sMVB5O5X/Kuoge");

        userEntity.setPasswordResetToken("passwordResetToken-516");

        userEntity = userRepository.save(userEntity);

        UUID userId = userEntity.getId();
        // when
        Response response =
                given().header(userHelper.getOrCreateUserInfo().getAuthHeader())
                        .delete("/users/{userId}", userId);

        // then
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(userRepository.existsById(userEntity.getId())).isEqualTo(false);
    }

    @Test
    void deleteAllUser() {
        // given
        UserEntity userEntity = new UserEntity();

        userEntity.setEmail("annabelle-548@email.com");

        userEntity.setPassword("$2a$10$DOeDrL9XB60EJAAnHJ7HMusFgrhmhb.qDypP66tx86/wIIXvX23Fa");

        userEntity.setPasswordResetToken("passwordResetToken-433");

        userEntity = userRepository.save(userEntity);

        assertThat(userRepository.findAll()).isNotEmpty();
        // when
        Response response =
                given().header(userHelper.getOrCreateAdminInfo().getAuthHeader()).delete("/users");

        // then
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(userRepository.findAll()).isEmpty();
    }

    @Test
    void getUser() {
        // given
        UserEntity userEntity = new UserEntity();

        userEntity.setEmail("jeannine-115@email.com");

        userEntity.setPassword("$2a$10$3m20pkByPYHiZuu1VmFg0.BTZdM0RqAieBMBpFGwOuQHTfuaIA1sW");

        userEntity.setPasswordResetToken("passwordResetToken-321");

        userEntity = userRepository.save(userEntity);

        UUID userId = userEntity.getId();
        // when
        Response response =
                given().header(userHelper.getOrCreateUserInfo().getAuthHeader())
                        .get("/users/{userId}", userId);

        // then
        assertThat(response.statusCode()).isEqualTo(200);
        UserResponse actual = response.as(UserResponse.class);
        assertThat(actual.getId()).isEqualTo(userEntity.getId());
        assertThat(actual.getEmail()).isEqualTo(userEntity.getEmail());
    }

    @Test
    void listUser() {
        // given
        UserEntity userEntity = new UserEntity();

        userEntity.setEmail("jules-374@email.com");

        userEntity.setPassword("$2a$10$H.cw5yOZMxUZv8y9RojHFeJKZTDC/Kcpt97QkCA7UUaOc5PxTVNpu");

        userEntity.setPasswordResetToken("passwordResetToken-722");

        userEntity = userRepository.save(userEntity);

        ListUserRequest request = new ListUserRequest();
        // when
        Response response =
                given().header(userHelper.getOrCreateAdminInfo().getAuthHeader())
                        .queryParam("id", request.getId())
                        .queryParam("email", request.getEmail())
                        .queryParam("pageIndex", request.getPageIndex())
                        .queryParam("pageSize", request.getPageSize())
                        .get("/users/listUser");

        // then
        assertThat(response.statusCode()).isEqualTo(200);
        PageResponse<ListUserResponse> result = response.as(new TypeRef<>() {});
        assertThat(result.getTotalElements()).isNotZero();
    }
}
