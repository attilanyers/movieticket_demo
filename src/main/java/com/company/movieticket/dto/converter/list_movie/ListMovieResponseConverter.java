package com.company.movieticket.dto.converter.list_movie;

import com.company.movieticket.dto.list_movie.ListMovieResponse;
import com.company.movieticket.entity.MovieEntity;

public class ListMovieResponseConverter {
    public ListMovieResponse convertTo(MovieEntity source) {
        if (source == null) {
            return null;
        }
        ListMovieResponse target = new ListMovieResponse();
        convertTo(target, source);
        return target;
    }

    public void convertTo(ListMovieResponse target, MovieEntity source) {
        target.setId(source.getId());
        target.setTitle(source.getTitle());
        target.setDirector(source.getDirector());
        target.setGenre(source.getGenre());
        target.setDuration(source.getDuration());
        target.setLength(source.getLength());
    }
}
