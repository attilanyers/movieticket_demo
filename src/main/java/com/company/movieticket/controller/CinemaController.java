package com.company.movieticket.controller;

import com.company.movieticket.dto.CreateCinemaRequest;
import com.company.movieticket.dto.CreateCinemaResponse;
import com.company.movieticket.dto.GetCinemaResponse;
import com.company.movieticket.dto.ListCinemaRequest;
import com.company.movieticket.dto.PageResponse;
import com.company.movieticket.dto.UpdateCinemaRequest;
import com.company.movieticket.dto.list_cinema.ListCinemaResponse;
import com.company.movieticket.entity.UserRole;
import com.company.movieticket.service.CinemaService;
import jakarta.validation.Valid;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CinemaController {

    private CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @PostMapping("/cinema")
    @Secured(UserRole.ROLE_ADMIN)
    public CreateCinemaResponse createCinema(@RequestBody @Valid CreateCinemaRequest dto) {
        return cinemaService.createCinema(dto);
    }

    @GetMapping("/cinema/{cinemaId}")
    @Secured({UserRole.ROLE_USER, UserRole.ROLE_ADMIN})
    public GetCinemaResponse getCinema(@PathVariable Integer cinemaId) {
        return cinemaService.getCinema(cinemaId);
    }

    @PutMapping("/cinema/{cinemaId}")
    @Secured(UserRole.ROLE_ADMIN)
    public void updateCinema(
            @PathVariable Integer cinemaId, @RequestBody @Valid UpdateCinemaRequest dto) {
        cinemaService.updateCinema(cinemaId, dto);
    }

    @DeleteMapping("/cinema/{cinemaId}")
    @Secured(UserRole.ROLE_ADMIN)
    public void deleteCinema(@PathVariable Integer cinemaId) {
        cinemaService.deleteCinema(cinemaId);
    }

    @GetMapping("/cinema")
    @Secured({UserRole.ROLE_USER, UserRole.ROLE_ADMIN})
    public PageResponse<ListCinemaResponse> listCinema(@Valid ListCinemaRequest request) {
        return PageResponse.of(cinemaService.listCinema(request));
    }
}
