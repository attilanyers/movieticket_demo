package com.company.movieticket.test;

import com.company.movieticket.dto.CreateMovieRequest;
import com.company.movieticket.dto.CreateMovieResponse;
import com.company.movieticket.dto.GetMovieResponse;
import com.company.movieticket.dto.ListMovieRequest;
import com.company.movieticket.dto.PageResponse;
import com.company.movieticket.dto.UpdateMovieRequest;
import com.company.movieticket.dto.list_movie.ListMovieResponse;
import com.company.movieticket.entity.MovieEntity;
import com.company.movieticket.repository.MovieRepository;
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
public class MovieTest {
    @Autowired MovieRepository movieRepository;
    @Autowired EntityHelper entityHelper;
    @Autowired UserHelper userHelper;

    @Test
    void createMovie() {
        // given
        CreateMovieRequest request = new CreateMovieRequest();
        // when
        Response response =
                given().header(userHelper.getOrCreateAdminInfo().getAuthHeader())
                        .body(request)
                        .post("/movie");

        // then
        assertThat(response.statusCode()).isEqualTo(200);
        CreateMovieResponse actual = response.as(CreateMovieResponse.class);
        assertThat(actual.getId()).isEqualTo(request.getId());
        assertThat(actual.getTitle()).isEqualTo(request.getTitle());
        assertThat(actual.getDirector()).isEqualTo(request.getDirector());
        assertThat(actual.getGenre()).isEqualTo(request.getGenre());
        assertThat(actual.getDuration()).isEqualTo(request.getDuration());
    }

    @Test
    void getMovie() {
        // given
        MovieEntity movieEntity = new MovieEntity();

        movieEntity.setTitle("title-609");

        movieEntity.setDirector("director-322");

        movieEntity.setGenre("genre-593");

        movieEntity.setDuration(578);

        movieEntity.setLength((short)740);

        movieEntity = movieRepository.save(movieEntity);

        Integer movieId = movieEntity.getId();
        // when
        Response response =
                given().header(userHelper.getOrCreateUserInfo().getAuthHeader())
                        .get("/movie/{movieId}", movieId);

        // then
        assertThat(response.statusCode()).isEqualTo(200);
        GetMovieResponse actual = response.as(GetMovieResponse.class);
        assertThat(actual.getId()).isEqualTo(movieEntity.getId());
        assertThat(actual.getTitle()).isEqualTo(movieEntity.getTitle());
        assertThat(actual.getDirector()).isEqualTo(movieEntity.getDirector());
        assertThat(actual.getGenre()).isEqualTo(movieEntity.getGenre());
        assertThat(actual.getDuration()).isEqualTo(movieEntity.getDuration());
    }

    @Test
    void updateMovie() {
        // given
        MovieEntity movieEntity = new MovieEntity();

        movieEntity.setTitle("title-587");

        movieEntity.setDirector("director-251");

        movieEntity.setGenre("genre-555");

        movieEntity.setDuration(252);

        movieEntity.setLength((short)618);

        movieEntity = movieRepository.save(movieEntity);

        UpdateMovieRequest request = new UpdateMovieRequest();
        Integer movieId = movieEntity.getId();
        // when
        Response response =
                given().header(userHelper.getOrCreateAdminInfo().getAuthHeader())
                        .body(request)
                        .put("/movie/{movieId}", movieId);

        // then
        assertThat(response.statusCode()).isEqualTo(200);
        Optional<MovieEntity> actualOpt = movieRepository.findById(movieEntity.getId());
        assertThat(actualOpt.isPresent());
        MovieEntity actual = actualOpt.orElseThrow();
        assertThat(actual.getId()).isEqualTo(request.getId());
        assertThat(actual.getTitle()).isEqualTo(request.getTitle());
        assertThat(actual.getDirector()).isEqualTo(request.getDirector());
        assertThat(actual.getGenre()).isEqualTo(request.getGenre());
        assertThat(actual.getDuration()).isEqualTo(request.getDuration());
    }

    @Test
    void deleteMovie() {
        // given
        MovieEntity movieEntity = new MovieEntity();

        movieEntity.setTitle("title-118");

        movieEntity.setDirector("director-375");

        movieEntity.setGenre("genre-562");

        movieEntity.setDuration(914);

        movieEntity.setLength((short)752);

        movieEntity = movieRepository.save(movieEntity);

        Integer movieId = movieEntity.getId();
        // when
        Response response =
                given().header(userHelper.getOrCreateAdminInfo().getAuthHeader())
                        .delete("/movie/{movieId}", movieId);

        // then
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(movieRepository.existsById(movieEntity.getId())).isEqualTo(false);
    }

    @Test
    void listMovie() {
        // given
        MovieEntity movieEntity = new MovieEntity();

        movieEntity.setTitle("title-495");

        movieEntity.setDirector("director-346");

        movieEntity.setGenre("genre-855");

        movieEntity.setDuration(580);

        movieEntity.setLength((short)174);

        movieEntity = movieRepository.save(movieEntity);

        ListMovieRequest request = new ListMovieRequest();
        // when
        Response response =
                given().header(userHelper.getOrCreateUserInfo().getAuthHeader())
                        .queryParam("id", request.getId())
                        .queryParam("title", request.getTitle())
                        .queryParam("director", request.getDirector())
                        .queryParam("genre", request.getGenre())
                        .queryParam(
                                "durationGreaterThanOrEqualTo",
                                request.getDurationGreaterThanOrEqualTo())
                        .queryParam(
                                "durationLessThanOrEqualTo", request.getDurationLessThanOrEqualTo())
                        .queryParam("duration", request.getDuration())
                        .queryParam(
                                "lengthGreaterThanOrEqualTo",
                                request.getLengthGreaterThanOrEqualTo())
                        .queryParam("lengthLessThanOrEqualTo", request.getLengthLessThanOrEqualTo())
                        .queryParam("length", request.getLength())
                        .queryParam("pageIndex", request.getPageIndex())
                        .queryParam("pageSize", request.getPageSize())
                        .get("/movie");

        // then
        assertThat(response.statusCode()).isEqualTo(200);
        PageResponse<ListMovieResponse> result = response.as(new TypeRef<>() {});
        assertThat(result.getTotalElements()).isNotZero();
    }
}
