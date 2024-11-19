package com.company.movieticket.dto.converter;

import com.company.movieticket.dto.ScreeningDto;
import com.company.movieticket.entity.CinemaEntity;
import com.company.movieticket.entity.MovieEntity;
import com.company.movieticket.entity.ScreeningEntity;

public class ScreeningDtoConverter {
    public ScreeningEntity convertTo(ScreeningDto source, CinemaEntity cinema, MovieEntity movie) {
        if (source == null) {
            return null;
        }
        ScreeningEntity target = new ScreeningEntity();
        convertTo(target, source, cinema, movie);
        return target;
    }

    public void convertTo(
            ScreeningEntity target, ScreeningDto source, CinemaEntity cinema, MovieEntity movie) {
        target.setId(source.getId());
        target.setCinema(cinema);
        target.setMovie(movie);
        target.setStartTime(source.getStartTime());
    }

    public ScreeningDto convertTo(ScreeningEntity source) {
        if (source == null) {
            return null;
        }
        ScreeningDto target = new ScreeningDto();
        convertTo(target, source);
        return target;
    }

    public void convertTo(ScreeningDto target, ScreeningEntity source) {
        target.setId(source.getId());
        target.setCinemaId(source.getCinema() != null ? source.getCinema().getId() : null);
        target.setMovieId(source.getMovie() != null ? source.getMovie().getId() : null);
        target.setStartTime(source.getStartTime());
    }
}
