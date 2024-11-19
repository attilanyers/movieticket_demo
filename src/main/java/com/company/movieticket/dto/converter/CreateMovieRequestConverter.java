package com.company.movieticket.dto.converter;

import com.company.movieticket.dto.CreateMovieRequest;
import com.company.movieticket.entity.MovieEntity;

public class CreateMovieRequestConverter {
    public MovieEntity convertTo(CreateMovieRequest source) {
        if (source == null) {
            return null;
        }
        MovieEntity target = new MovieEntity();
        convertTo(target, source);
        return target;
    }

    public void convertTo(MovieEntity target, CreateMovieRequest source) {
        target.setId(source.getId());
        target.setTitle(source.getTitle());
        target.setDirector(source.getDirector());
        target.setGenre(source.getGenre());
        target.setDuration(source.getDuration());
    }
}
