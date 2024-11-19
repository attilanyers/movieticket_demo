package com.company.movieticket.dto.converter;

import com.company.movieticket.dto.UpdateMovieRequest;
import com.company.movieticket.entity.MovieEntity;

public class UpdateMovieRequestConverter {
    public MovieEntity convertTo(UpdateMovieRequest source) {
        if (source == null) {
            return null;
        }
        MovieEntity target = new MovieEntity();
        convertTo(target, source);
        return target;
    }

    public void convertTo(MovieEntity target, UpdateMovieRequest source) {
        target.setId(source.getId());
        target.setTitle(source.getTitle());
        target.setDirector(source.getDirector());
        target.setGenre(source.getGenre());
        target.setDuration(source.getDuration());
    }
}
