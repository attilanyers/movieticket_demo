package com.company.movieticket.dto.converter;

import com.company.movieticket.dto.GetMovieResponse;
import com.company.movieticket.entity.MovieEntity;

public class GetMovieResponseConverter {
    public GetMovieResponse convertTo(MovieEntity source) {
        if (source == null) {
            return null;
        }
        GetMovieResponse target = new GetMovieResponse();
        convertTo(target, source);
        return target;
    }

    public void convertTo(GetMovieResponse target, MovieEntity source) {
        target.setId(source.getId());
        target.setTitle(source.getTitle());
        target.setDirector(source.getDirector());
        target.setGenre(source.getGenre());
        target.setDuration(source.getDuration());
    }
}
