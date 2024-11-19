package com.company.movieticket.dto.converter;

import com.company.movieticket.dto.MovieDto;
import com.company.movieticket.entity.MovieEntity;

public class MovieDtoConverter {
    public MovieEntity convertTo(MovieDto source) {
        if (source == null) {
            return null;
        }
        MovieEntity target = new MovieEntity();
        convertTo(target, source);
        return target;
    }

    public void convertTo(MovieEntity target, MovieDto source) {
        target.setId(source.getId());
        target.setTitle(source.getTitle());
        target.setDirector(source.getDirector());
        target.setGenre(source.getGenre());
        target.setDuration(source.getDuration());
    }

    public MovieDto convertTo(MovieEntity source) {
        if (source == null) {
            return null;
        }
        MovieDto target = new MovieDto();
        convertTo(target, source);
        return target;
    }

    public void convertTo(MovieDto target, MovieEntity source) {
        target.setId(source.getId());
        target.setTitle(source.getTitle());
        target.setDirector(source.getDirector());
        target.setGenre(source.getGenre());
        target.setDuration(source.getDuration());
    }
}
