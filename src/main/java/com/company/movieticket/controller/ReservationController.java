package com.company.movieticket.controller;

import com.company.movieticket.dto.CreateReservationRequest;
import com.company.movieticket.dto.CreateReservationResponse;
import com.company.movieticket.dto.CustomReservationRequest;
import com.company.movieticket.dto.CustomReservationResponse;
import com.company.movieticket.dto.GetReservationResponse;
import com.company.movieticket.dto.ListReservationRequest;
import com.company.movieticket.dto.PageResponse;
import com.company.movieticket.dto.UpdateReservationRequest;
import com.company.movieticket.dto.list_reservation.ListReservationResponse;
import com.company.movieticket.entity.UserRole;
import com.company.movieticket.service.MockService;
import com.company.movieticket.service.ReservationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {

    private ReservationService reservationService;
    private MockService mockService;

    public ReservationController(ReservationService reservationService, MockService mockService) {
        this.reservationService = reservationService;
        this.mockService = mockService;
    }

    @PostMapping("/reservation")
    @Secured({UserRole.ROLE_USER, UserRole.ROLE_ADMIN})
    public CreateReservationResponse createReservation(
            @RequestBody @Valid CreateReservationRequest dto) {
        return reservationService.createReservation(dto);
    }

    @GetMapping("/reservation/{reservationId}")
    @Secured({UserRole.ROLE_USER, UserRole.ROLE_ADMIN})
    public GetReservationResponse getReservation(@PathVariable Integer reservationId) {
        return reservationService.getReservation(reservationId);
    }

    @PutMapping("/reservation/{reservationId}")
    @Secured(UserRole.ROLE_ADMIN)
    public void updateReservation(
            @PathVariable Integer reservationId, @RequestBody @Valid UpdateReservationRequest dto) {
        reservationService.updateReservation(reservationId, dto);
    }

    @DeleteMapping("/reservation/{reservationId}")
    @Secured({UserRole.ROLE_USER, UserRole.ROLE_ADMIN})
    public void deleteReservation(@PathVariable Integer reservationId) {
        reservationService.deleteReservation(reservationId);
    }

    @GetMapping("/reservation")
    @Secured({UserRole.ROLE_USER, UserRole.ROLE_ADMIN})
    public PageResponse<ListReservationResponse> listReservation(
            @Valid ListReservationRequest request) {
        return PageResponse.of(reservationService.listReservation(request));
    }

    @PostMapping("/reservationCustom")
    @Secured(UserRole.ROLE_ADMIN)
    public ResponseEntity<CustomReservationResponse> customReservation(
            @RequestBody @Valid CustomReservationRequest dto,
            HttpServletRequest httpServletRequest) {
        return null;
    }
}
