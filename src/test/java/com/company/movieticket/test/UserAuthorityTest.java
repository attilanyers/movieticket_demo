package com.company.movieticket.test;

import com.company.movieticket.dto.ListUserAuthorityRequest;
import com.company.movieticket.dto.PageResponse;
import com.company.movieticket.dto.UserAuthorityCreateRequest;
import com.company.movieticket.dto.UserAuthorityResponse;
import com.company.movieticket.dto.UserAuthorityUpdateRequest;
import com.company.movieticket.dto.list_user_authority.ListUserAuthorityResponse;
import com.company.movieticket.entity.UserAuthorityEntity;
import com.company.movieticket.entity.UserEntity;
import com.company.movieticket.repository.UserAuthorityRepository;
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
public class UserAuthorityTest {
    @Autowired UserAuthorityRepository userAuthorityRepository;
    @Autowired EntityHelper entityHelper;
    @Autowired UserHelper userHelper;

    @Test
    void createUserAuthority() {
        // given
        UserEntity user = userHelper.getOrCreateUserInfo().getEntity();
        UserAuthorityCreateRequest request = new UserAuthorityCreateRequest();
        // when
        Response response =
                given().header(userHelper.getOrCreateAdminInfo().getAuthHeader())
                        .body(request)
                        .post("/userAuthorities");

        // then
        assertThat(response.statusCode()).isEqualTo(200);
        UserAuthorityResponse actual = response.as(UserAuthorityResponse.class);
        assertThat(actual.getUserId()).isEqualTo(request.getUserId());
        assertThat(actual.getAuthority()).isEqualTo(request.getAuthority());
    }

    @Test
    void updateUserAuthority() {
        // given
        UserEntity userBeforeUpdate = userHelper.getOrCreateUserInfo().getEntity();
        UserEntity userAfterUpdate = userHelper.getOrCreateUserInfo().getEntity();
        UserAuthorityEntity userAuthorityEntity = new UserAuthorityEntity();

        userAuthorityEntity.setUser(userBeforeUpdate);

        userAuthorityEntity.setAuthority("authority-301");

        userAuthorityEntity = userAuthorityRepository.save(userAuthorityEntity);

        UserAuthorityUpdateRequest request = new UserAuthorityUpdateRequest();
        UUID userAuthorityId = userAuthorityEntity.getId();
        // when
        Response response =
                given().header(userHelper.getOrCreateAdminInfo().getAuthHeader())
                        .body(request)
                        .put("/userAuthorities/{userAuthorityId}", userAuthorityId);

        // then
        assertThat(response.statusCode()).isEqualTo(200);
        Optional<UserAuthorityEntity> actualOpt =
                userAuthorityRepository.findById(userAuthorityEntity.getId());
        assertThat(actualOpt.isPresent());
        UserAuthorityEntity actual = actualOpt.orElseThrow();
        assertThat(actual.getUser().getId()).isEqualTo(request.getUserId());
        assertThat(actual.getAuthority()).isEqualTo(request.getAuthority());
    }

    @Test
    void deleteUserAuthority() {
        // given
        UserEntity user = userHelper.getOrCreateUserInfo().getEntity();
        UserAuthorityEntity userAuthorityEntity = new UserAuthorityEntity();

        userAuthorityEntity.setUser(user);

        userAuthorityEntity.setAuthority("authority-666");

        userAuthorityEntity = userAuthorityRepository.save(userAuthorityEntity);

        UUID userAuthorityId = userAuthorityEntity.getId();
        // when
        Response response =
                given().header(userHelper.getOrCreateAdminInfo().getAuthHeader())
                        .delete("/userAuthorities/{userAuthorityId}", userAuthorityId);

        // then
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(userAuthorityRepository.existsById(userAuthorityEntity.getId()))
                .isEqualTo(false);
    }

    @Test
    void deleteAllUserAuthority() {
        // given
        UserEntity user = userHelper.getOrCreateUserInfo().getEntity();
        UserAuthorityEntity userAuthorityEntity = new UserAuthorityEntity();

        userAuthorityEntity.setUser(user);

        userAuthorityEntity.setAuthority("authority-812");

        userAuthorityEntity = userAuthorityRepository.save(userAuthorityEntity);

        assertThat(userAuthorityRepository.findAll()).isNotEmpty();
        // when
        Response response =
                given().header(userHelper.getOrCreateAdminInfo().getAuthHeader())
                        .delete("/userAuthorities");

        // then
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(userAuthorityRepository.findAll()).isEmpty();
    }

    @Test
    void getUserAuthority() {
        // given
        UserEntity user = userHelper.getOrCreateUserInfo().getEntity();
        UserAuthorityEntity userAuthorityEntity = new UserAuthorityEntity();

        userAuthorityEntity.setUser(user);

        userAuthorityEntity.setAuthority("authority-454");

        userAuthorityEntity = userAuthorityRepository.save(userAuthorityEntity);

        UUID userAuthorityId = userAuthorityEntity.getId();
        // when
        Response response =
                given().header(userHelper.getOrCreateUserInfo().getAuthHeader())
                        .get("/userAuthorities/{userAuthorityId}", userAuthorityId);

        // then
        assertThat(response.statusCode()).isEqualTo(200);
        UserAuthorityResponse actual = response.as(UserAuthorityResponse.class);
        assertThat(actual.getId()).isEqualTo(userAuthorityEntity.getId());
        assertThat(actual.getUserId()).isEqualTo(userAuthorityEntity.getUser().getId());
        assertThat(actual.getAuthority()).isEqualTo(userAuthorityEntity.getAuthority());
    }

    @Test
    void listUserAuthority() {
        // given
        UserEntity user = userHelper.getOrCreateUserInfo().getEntity();
        UserAuthorityEntity userAuthorityEntity = new UserAuthorityEntity();

        userAuthorityEntity.setUser(user);

        userAuthorityEntity.setAuthority("authority-335");

        userAuthorityEntity = userAuthorityRepository.save(userAuthorityEntity);

        ListUserAuthorityRequest request = new ListUserAuthorityRequest();
        // when
        Response response =
                given().header(userHelper.getOrCreateAdminInfo().getAuthHeader())
                        .queryParam("id", request.getId())
                        .queryParam("userId", request.getUserId())
                        .queryParam("authority", request.getAuthority())
                        .queryParam("pageIndex", request.getPageIndex())
                        .queryParam("pageSize", request.getPageSize())
                        .get("/userAuthorities/listUserAuthority");

        // then
        assertThat(response.statusCode()).isEqualTo(200);
        PageResponse<ListUserAuthorityResponse> result = response.as(new TypeRef<>() {});
        assertThat(result.getTotalElements()).isNotZero();
    }
}
