package com.company.movieticket.controller;

import com.company.movieticket.dto.CreateMovieRequest;
import com.company.movieticket.dto.CreateMovieResponse;
import com.company.movieticket.dto.GetMovieResponse;
import com.company.movieticket.dto.ListMovieRequest;
import com.company.movieticket.dto.PageResponse;
import com.company.movieticket.dto.UpdateMovieRequest;
import com.company.movieticket.dto.list_movie.ListMovieResponse;
import com.company.movieticket.entity.UserRole;
import com.company.movieticket.service.MovieService;
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
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/movie")
    @Secured(UserRole.ROLE_ADMIN)
    public CreateMovieResponse createMovie(@RequestBody @Valid CreateMovieRequest dto) {
        return movieService.createMovie(dto);
    }

    @GetMapping("/movie/{movieId}")
    @Secured({UserRole.ROLE_USER, UserRole.ROLE_ADMIN})
    public GetMovieResponse getMovie(@PathVariable Integer movieId) {
        return movieService.getMovie(movieId);
    }

    @PutMapping("/movie/{movieId}")
    @Secured(UserRole.ROLE_ADMIN)
    public void updateMovie(
            @PathVariable Integer movieId, @RequestBody @Valid UpdateMovieRequest dto) {
        movieService.updateMovie(movieId, dto);
    }

    @DeleteMapping("/movie/{movieId}")
    @Secured(UserRole.ROLE_ADMIN)
    public void deleteMovie(@PathVariable Integer movieId) {
        movieService.deleteMovie(movieId);
    }

    @GetMapping("/movie")
    @Secured({UserRole.ROLE_USER, UserRole.ROLE_ADMIN})
    public PageResponse<ListMovieResponse> listMovie(@Valid ListMovieRequest request) {
        return PageResponse.of(movieService.listMovie(request));
    }
}
