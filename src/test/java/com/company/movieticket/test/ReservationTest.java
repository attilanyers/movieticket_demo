package com.company.movieticket.test;

import com.company.movieticket.dto.CreateReservationRequest;
import com.company.movieticket.dto.CreateReservationResponse;
import com.company.movieticket.dto.CustomReservationRequest;
import com.company.movieticket.dto.GetReservationResponse;
import com.company.movieticket.dto.ListReservationRequest;
import com.company.movieticket.dto.PageResponse;
import com.company.movieticket.dto.UpdateReservationRequest;
import com.company.movieticket.dto.list_reservation.ListReservationResponse;
import com.company.movieticket.entity.ReservationEntity;
import com.company.movieticket.entity.ScreeningEntity;
import com.company.movieticket.entity.UserEntity;
import com.company.movieticket.repository.ReservationRepository;
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
public class ReservationTest {
    @Autowired ReservationRepository reservationRepository;
    @Autowired EntityHelper entityHelper;
    @Autowired UserHelper userHelper;

    @Test
    void createReservation() {
        // given
        ScreeningEntity screening = entityHelper.createScreening();
        UserEntity user = userHelper.getOrCreateUserInfo().getEntity();
        CreateReservationRequest request = new CreateReservationRequest();
        // when
        Response response =
                given().header(userHelper.getOrCreateUserInfo().getAuthHeader())
                        .body(request)
                        .post("/reservation");

        // then
        assertThat(response.statusCode()).isEqualTo(200);
        CreateReservationResponse actual = response.as(CreateReservationResponse.class);
        assertThat(actual.getId()).isEqualTo(request.getId());
        assertThat(actual.getScreeningId()).isEqualTo(request.getScreeningId());
        assertThat(actual.getUserId()).isEqualTo(request.getUserId());
        assertThat(actual.getSeatNumber()).isEqualTo(request.getSeatNumber());
    }

    @Test
    void getReservation() {
        // given
        ScreeningEntity screening = entityHelper.createScreening();
        UserEntity user = userHelper.getOrCreateUserInfo().getEntity();
        ReservationEntity reservationEntity = new ReservationEntity();

        reservationEntity.setScreening(screening);

        reservationEntity.setUser(user);

        reservationEntity.setSeatNumber("seatNumber-501");

        reservationEntity = reservationRepository.save(reservationEntity);

        Integer reservationId = reservationEntity.getId();
        // when
        Response response =
                given().header(userHelper.getOrCreateUserInfo().getAuthHeader())
                        .get("/reservation/{reservationId}", reservationId);

        // then
        assertThat(response.statusCode()).isEqualTo(200);
        GetReservationResponse actual = response.as(GetReservationResponse.class);
        assertThat(actual.getId()).isEqualTo(reservationEntity.getId());
        assertThat(actual.getScreeningId()).isEqualTo(reservationEntity.getScreening().getId());
        assertThat(actual.getUserId()).isEqualTo(reservationEntity.getUser().getId());
        assertThat(actual.getSeatNumber()).isEqualTo(reservationEntity.getSeatNumber());
    }

    @Test
    void updateReservation() {
        // given
        ScreeningEntity screeningBeforeUpdate = entityHelper.createScreening();
        UserEntity userBeforeUpdate = userHelper.getOrCreateUserInfo().getEntity();
        ScreeningEntity screeningAfterUpdate = entityHelper.createScreening();
        UserEntity userAfterUpdate = userHelper.getOrCreateUserInfo().getEntity();
        ReservationEntity reservationEntity = new ReservationEntity();

        reservationEntity.setScreening(screeningBeforeUpdate);

        reservationEntity.setUser(userBeforeUpdate);

        reservationEntity.setSeatNumber("seatNumber-714");

        reservationEntity = reservationRepository.save(reservationEntity);

        UpdateReservationRequest request = new UpdateReservationRequest();
        Integer reservationId = reservationEntity.getId();
        // when
        Response response =
                given().header(userHelper.getOrCreateAdminInfo().getAuthHeader())
                        .body(request)
                        .put("/reservation/{reservationId}", reservationId);

        // then
        assertThat(response.statusCode()).isEqualTo(200);
        Optional<ReservationEntity> actualOpt =
                reservationRepository.findById(reservationEntity.getId());
        assertThat(actualOpt.isPresent());
        ReservationEntity actual = actualOpt.orElseThrow();
        assertThat(actual.getId()).isEqualTo(request.getId());
        assertThat(actual.getScreening().getId()).isEqualTo(request.getScreeningId());
        assertThat(actual.getUser().getId()).isEqualTo(request.getUserId());
        assertThat(actual.getSeatNumber()).isEqualTo(request.getSeatNumber());
    }

    @Test
    void deleteReservation() {
        // given
        ScreeningEntity screening = entityHelper.createScreening();
        UserEntity user = userHelper.getOrCreateUserInfo().getEntity();
        ReservationEntity reservationEntity = new ReservationEntity();

        reservationEntity.setScreening(screening);

        reservationEntity.setUser(user);

        reservationEntity.setSeatNumber("seatNumber-119");

        reservationEntity = reservationRepository.save(reservationEntity);

        Integer reservationId = reservationEntity.getId();
        // when
        Response response =
                given().header(userHelper.getOrCreateUserInfo().getAuthHeader())
                        .delete("/reservation/{reservationId}", reservationId);

        // then
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(reservationRepository.existsById(reservationEntity.getId())).isEqualTo(false);
    }

    @Test
    void listReservation() {
        // given
        ScreeningEntity screening = entityHelper.createScreening();
        UserEntity user = userHelper.getOrCreateUserInfo().getEntity();
        ReservationEntity reservationEntity = new ReservationEntity();

        reservationEntity.setScreening(screening);

        reservationEntity.setUser(user);

        reservationEntity.setSeatNumber("seatNumber-719");

        reservationEntity = reservationRepository.save(reservationEntity);

        ListReservationRequest request = new ListReservationRequest();
        // when
        Response response =
                given().header(userHelper.getOrCreateUserInfo().getAuthHeader())
                        .queryParam("id", request.getId())
                        .queryParam("screeningId", request.getScreeningId())
                        .queryParam("userId", request.getUserId())
                        .queryParam("seatNumber", request.getSeatNumber())
                        .queryParam("pageIndex", request.getPageIndex())
                        .queryParam("pageSize", request.getPageSize())
                        .get("/reservation");

        // then
        assertThat(response.statusCode()).isEqualTo(200);
        PageResponse<ListReservationResponse> result = response.as(new TypeRef<>() {});
        assertThat(result.getTotalElements()).isNotZero();
    }

    @Test
    void customReservation() {
        // given
        ScreeningEntity screening = entityHelper.createScreening();
        UserEntity user = userHelper.getOrCreateUserInfo().getEntity();
        ReservationEntity reservationEntity = new ReservationEntity();

        reservationEntity.setScreening(screening);

        reservationEntity.setUser(user);

        reservationEntity.setSeatNumber("seatNumber-882");

        reservationEntity = reservationRepository.save(reservationEntity);

        CustomReservationRequest request = new CustomReservationRequest();
        // when
        Response response =
                given().header(userHelper.getOrCreateAdminInfo().getAuthHeader())
                        .body(request)
                        .post("/reservationCustom");

        // then
        assertThat(response.statusCode()).isEqualTo(200);
    }
}
