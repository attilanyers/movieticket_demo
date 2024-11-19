package com.company.movieticket.test;

import com.company.movieticket.dto.CreateScreeningRequest;
import com.company.movieticket.dto.CreateScreeningResponse;
import com.company.movieticket.dto.GetScreeningResponse;
import com.company.movieticket.dto.ListScreeningRequest;
import com.company.movieticket.dto.PageResponse;
import com.company.movieticket.dto.UpdateScreeningRequest;
import com.company.movieticket.dto.list_screening.ListScreeningResponse;
import com.company.movieticket.entity.CinemaEntity;
import com.company.movieticket.entity.MovieEntity;
import com.company.movieticket.entity.ScreeningEntity;
import com.company.movieticket.repository.ScreeningRepository;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static io.restassured.RestAssured.given;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(TestSetup.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ScreeningTest {
    @Autowired ScreeningRepository screeningRepository;
    @Autowired EntityHelper entityHelper;
    @Autowired UserHelper userHelper;

    @Test
    void createScreening() {
        // given
        CinemaEntity cinema = entityHelper.createCinema();
        MovieEntity movie = entityHelper.createMovie();
        CreateScreeningRequest request = new CreateScreeningRequest();
        // when
        Response response =
                given().header(userHelper.getOrCreateAdminInfo().getAuthHeader())
                        .body(request)
                        .post("/screening");

        // then
        assertThat(response.statusCode()).isEqualTo(200);
        CreateScreeningResponse actual = response.as(CreateScreeningResponse.class);
        assertThat(actual.getId()).isEqualTo(request.getId());
        assertThat(actual.getCinemaId()).isEqualTo(request.getCinemaId());
        assertThat(actual.getMovieId()).isEqualTo(request.getMovieId());
        assertThat(actual.getStartTime()).isEqualTo(request.getStartTime());
    }

    @Test
    void getScreening() {
        // given
        CinemaEntity cinema = entityHelper.createCinema();
        MovieEntity movie = entityHelper.createMovie();
        ScreeningEntity screeningEntity = new ScreeningEntity();

        screeningEntity.setCinema(cinema);

        screeningEntity.setMovie(movie);

        screeningEntity.setStartTime(LocalDateTime.now());

        screeningEntity = screeningRepository.save(screeningEntity);

        Integer screeningId = screeningEntity.getId();
        // when
        Response response =
                given().header(userHelper.getOrCreateUserInfo().getAuthHeader())
                        .get("/screening/{screeningId}", screeningId);

        // then
        assertThat(response.statusCode()).isEqualTo(200);
        GetScreeningResponse actual = response.as(GetScreeningResponse.class);
        assertThat(actual.getId()).isEqualTo(screeningEntity.getId());
        assertThat(actual.getCinemaId()).isEqualTo(screeningEntity.getCinema().getId());
        assertThat(actual.getMovieId()).isEqualTo(screeningEntity.getMovie().getId());
        assertThat(actual.getStartTime()).isEqualTo(screeningEntity.getStartTime());
    }

    @Test
    void updateScreening() {
        // given
        CinemaEntity cinemaBeforeUpdate = entityHelper.createCinema();
        MovieEntity movieBeforeUpdate = entityHelper.createMovie();
        CinemaEntity cinemaAfterUpdate = entityHelper.createCinema();
        MovieEntity movieAfterUpdate = entityHelper.createMovie();
        ScreeningEntity screeningEntity = new ScreeningEntity();

        screeningEntity.setCinema(cinemaBeforeUpdate);

        screeningEntity.setMovie(movieBeforeUpdate);

        screeningEntity.setStartTime(LocalDateTime.now());

        screeningEntity = screeningRepository.save(screeningEntity);

        UpdateScreeningRequest request = new UpdateScreeningRequest();
        Integer screeningId = screeningEntity.getId();
        // when
        Response response =
                given().header(userHelper.getOrCreateAdminInfo().getAuthHeader())
                        .body(request)
                        .put("/screening/{screeningId}", screeningId);

        // then
        assertThat(response.statusCode()).isEqualTo(200);
        Optional<ScreeningEntity> actualOpt = screeningRepository.findById(screeningEntity.getId());
        assertThat(actualOpt.isPresent());
        ScreeningEntity actual = actualOpt.orElseThrow();
        assertThat(actual.getId()).isEqualTo(request.getId());
        assertThat(actual.getCinema().getId()).isEqualTo(request.getCinemaId());
        assertThat(actual.getMovie().getId()).isEqualTo(request.getMovieId());
        assertThat(actual.getStartTime()).isEqualTo(request.getStartTime());
    }

    @Test
    void deleteScreening() {
        // given
        CinemaEntity cinema = entityHelper.createCinema();
        MovieEntity movie = entityHelper.createMovie();
        ScreeningEntity screeningEntity = new ScreeningEntity();

        screeningEntity.setCinema(cinema);

        screeningEntity.setMovie(movie);

        screeningEntity.setStartTime(LocalDateTime.now());

        screeningEntity = screeningRepository.save(screeningEntity);

        Integer screeningId = screeningEntity.getId();
        // when
        Response response =
                given().header(userHelper.getOrCreateAdminInfo().getAuthHeader())
                        .delete("/screening/{screeningId}", screeningId);

        // then
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(screeningRepository.existsById(screeningEntity.getId())).isEqualTo(false);
    }

    @Test
    void listScreening() {
        // given
        CinemaEntity cinema = entityHelper.createCinema();
        MovieEntity movie = entityHelper.createMovie();
        ScreeningEntity screeningEntity = new ScreeningEntity();

        screeningEntity.setCinema(cinema);

        screeningEntity.setMovie(movie);

        screeningEntity.setStartTime(LocalDateTime.now());

        screeningEntity = screeningRepository.save(screeningEntity);

        ListScreeningRequest request = new ListScreeningRequest();
        // when
        Response response =
                given().header(userHelper.getOrCreateUserInfo().getAuthHeader())
                        .queryParam("id", request.getId())
                        .queryParam("cinemaId", request.getCinemaId())
                        .queryParam("movieId", request.getMovieId())
                        .queryParam("startTimeIsBefore", request.getStartTimeIsBefore())
                        .queryParam("startTimeIsAfter", request.getStartTimeIsAfter())
                        .queryParam("startTime", request.getStartTime())
                        .queryParam("pageIndex", request.getPageIndex())
                        .queryParam("pageSize", request.getPageSize())
                        .get("/screening");

        // then
        assertThat(response.statusCode()).isEqualTo(200);
        PageResponse<ListScreeningResponse> result = response.as(new TypeRef<>() {});
        assertThat(result.getTotalElements()).isNotZero();
    }
}
