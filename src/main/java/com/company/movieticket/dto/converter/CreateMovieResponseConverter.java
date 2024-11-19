package com.company.movieticket.dto.converter;

import com.company.movieticket.dto.CreateMovieResponse;
import com.company.movieticket.entity.MovieEntity;

public class CreateMovieResponseConverter {
    public CreateMovieResponse convertTo(MovieEntity source) {
        if (source == null) {
            return null;
        }
        CreateMovieResponse target = new CreateMovieResponse();
        convertTo(target, source);
        return target;
    }

    public void convertTo(CreateMovieResponse target, MovieEntity source) {
        target.setId(source.getId());
        target.setTitle(source.getTitle());
        target.setDirector(source.getDirector());
        target.setGenre(source.getGenre());
        target.setDuration(source.getDuration());
    }
}
