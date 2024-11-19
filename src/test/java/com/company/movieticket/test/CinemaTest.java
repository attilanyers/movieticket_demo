package com.company.movieticket.test;

import com.company.movieticket.dto.CreateCinemaRequest;
import com.company.movieticket.dto.CreateCinemaResponse;
import com.company.movieticket.dto.GetCinemaResponse;
import com.company.movieticket.dto.ListCinemaRequest;
import com.company.movieticket.dto.PageResponse;
import com.company.movieticket.dto.UpdateCinemaRequest;
import com.company.movieticket.dto.list_cinema.ListCinemaResponse;
import com.company.movieticket.entity.CinemaEntity;
import com.company.movieticket.repository.CinemaRepository;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static io.restassured.RestAssured.given;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(TestSetup.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CinemaTest {
    @Autowired CinemaRepository cinemaRepository;
    @Autowired EntityHelper entityHelper;
    @Autowired UserHelper userHelper;

    @Test
    void createCinema() {
        // given
        CreateCinemaRequest request = new CreateCinemaRequest();
        // when
        Response response =
                given().header(userHelper.getOrCreateAdminInfo().getAuthHeader())
                        .body(request)
                        .post("/cinema");

        // then
        assertThat(response.statusCode()).isEqualTo(200);
        CreateCinemaResponse actual = response.as(CreateCinemaResponse.class);
        assertThat(actual.getId()).isEqualTo(request.getId());
        assertThat(actual.getName()).isEqualTo(request.getName());
        assertThat(actual.getLocation()).isEqualTo(request.getLocation());
    }

    @Test
    void getCinema() {
        // given
        CinemaEntity cinemaEntity = new CinemaEntity();

        cinemaEntity.setName("name-690");

        cinemaEntity.setLocation("location-335");

        cinemaEntity = cinemaRepository.save(cinemaEntity);

        Integer cinemaId = cinemaEntity.getId();
        // when
        Response response =
                given().header(userHelper.getOrCreateUserInfo().getAuthHeader())
                        .get("/cinema/{cinemaId}", cinemaId);

        // then
        assertThat(response.statusCode()).isEqualTo(200);
        GetCinemaResponse actual = response.as(GetCinemaResponse.class);
        assertThat(actual.getId()).isEqualTo(cinemaEntity.getId());
        assertThat(actual.getName()).isEqualTo(cinemaEntity.getName());
        assertThat(actual.getLocation()).isEqualTo(cinemaEntity.getLocation());
    }

    @Test
    void updateCinema() {
        // given
        CinemaEntity cinemaEntity = new CinemaEntity();

        cinemaEntity.setName("name-693");

        cinemaEntity.setLocation("location-662");

        cinemaEntity = cinemaRepository.save(cinemaEntity);

        UpdateCinemaRequest request = new UpdateCinemaRequest();
        Integer cinemaId = cinemaEntity.getId();
        // when
        Response response =
                given().header(userHelper.getOrCreateAdminInfo().getAuthHeader())
                        .body(request)
                        .put("/cinema/{cinemaId}", cinemaId);

        // then
        assertThat(response.statusCode()).isEqualTo(200);
        Optional<CinemaEntity> actualOpt = cinemaRepository.findById(cinemaEntity.getId());
        assertThat(actualOpt.isPresent());
        CinemaEntity actual = actualOpt.orElseThrow();
        assertThat(actual.getId()).isEqualTo(request.getId());
        assertThat(actual.getName()).isEqualTo(request.getName());
        assertThat(actual.getLocation()).isEqualTo(request.getLocation());
    }

    @Test
    void deleteCinema() {
        // given
        CinemaEntity cinemaEntity = new CinemaEntity();

        cinemaEntity.setName("name-830");

        cinemaEntity.setLocation("location-449");

        cinemaEntity = cinemaRepository.save(cinemaEntity);

        Integer cinemaId = cinemaEntity.getId();
        // when
        Response response =
                given().header(userHelper.getOrCreateAdminInfo().getAuthHeader())
                        .delete("/cinema/{cinemaId}", cinemaId);

        // then
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(cinemaRepository.existsById(cinemaEntity.getId())).isEqualTo(false);
    }

    @Test
    void listCinema() {
        // given
        CinemaEntity cinemaEntity = new CinemaEntity();

        cinemaEntity.setName("name-572");

        cinemaEntity.setLocation("location-508");

        cinemaEntity = cinemaRepository.save(cinemaEntity);

        ListCinemaRequest request = new ListCinemaRequest();
        // when
        Response response =
                given().header(userHelper.getOrCreateUserInfo().getAuthHeader())
                        .queryParam("id", request.getId())
                        .queryParam("name", request.getName())
                        .queryParam("location", request.getLocation())
                        .queryParam("pageIndex", request.getPageIndex())
                        .queryParam("pageSize", request.getPageSize())
                        .get("/cinema");

        // then
        assertThat(response.statusCode()).isEqualTo(200);
        PageResponse<ListCinemaResponse> result = response.as(new TypeRef<>() {});
        assertThat(result.getTotalElements()).isNotZero();
    }
}
