package com.company.movieticket.dto.converter;

import com.company.movieticket.dto.UpdateScreeningRequest;
import com.company.movieticket.entity.CinemaEntity;
import com.company.movieticket.entity.MovieEntity;
import com.company.movieticket.entity.ScreeningEntity;

public class UpdateScreeningRequestConverter {
    public ScreeningEntity convertTo(
            UpdateScreeningRequest source, CinemaEntity cinema, MovieEntity movie) {
        if (source == null) {
            return null;
        }
        ScreeningEntity target = new ScreeningEntity();
        convertTo(target, source, cinema, movie);
        return target;
    }

    public void convertTo(
            ScreeningEntity target,
            UpdateScreeningRequest source,
            CinemaEntity cinema,
            MovieEntity movie) {
        target.setId(source.getId());
        target.setCinema(cinema);
        target.setMovie(movie);
        target.setStartTime(source.getStartTime());
    }
}
