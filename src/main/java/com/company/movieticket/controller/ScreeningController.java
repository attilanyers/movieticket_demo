package com.company.movieticket.controller;

import com.company.movieticket.dto.CreateScreeningRequest;
import com.company.movieticket.dto.CreateScreeningResponse;
import com.company.movieticket.dto.GetScreeningResponse;
import com.company.movieticket.dto.ListScreeningRequest;
import com.company.movieticket.dto.PageResponse;
import com.company.movieticket.dto.UpdateScreeningRequest;
import com.company.movieticket.dto.list_screening.ListScreeningResponse;
import com.company.movieticket.entity.UserRole;
import com.company.movieticket.service.ScreeningService;
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
public class ScreeningController {

    private ScreeningService screeningService;

    public ScreeningController(ScreeningService screeningService) {
        this.screeningService = screeningService;
    }

    @PostMapping("/screening")
    @Secured(UserRole.ROLE_ADMIN)
    public CreateScreeningResponse createScreening(@RequestBody @Valid CreateScreeningRequest dto) {
        return screeningService.createScreening(dto);
    }

    @GetMapping("/screening/{screeningId}")
    @Secured({UserRole.ROLE_USER, UserRole.ROLE_ADMIN})
    public GetScreeningResponse getScreening(@PathVariable Integer screeningId) {
        return screeningService.getScreening(screeningId);
    }

    @PutMapping("/screening/{screeningId}")
    @Secured(UserRole.ROLE_ADMIN)
    public void updateScreening(
            @PathVariable Integer screeningId, @RequestBody @Valid UpdateScreeningRequest dto) {
        screeningService.updateScreening(screeningId, dto);
    }

    @DeleteMapping("/screening/{screeningId}")
    @Secured(UserRole.ROLE_ADMIN)
    public void deleteScreening(@PathVariable Integer screeningId) {
        screeningService.deleteScreening(screeningId);
    }

    @GetMapping("/screening")
    @Secured({UserRole.ROLE_USER, UserRole.ROLE_ADMIN})
    public PageResponse<ListScreeningResponse> listScreening(@Valid ListScreeningRequest request) {
        return PageResponse.of(screeningService.listScreening(request));
    }
}
