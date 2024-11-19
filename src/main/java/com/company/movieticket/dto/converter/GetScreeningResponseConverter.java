package com.company.movieticket.dto.converter;

import com.company.movieticket.dto.GetScreeningResponse;
import com.company.movieticket.entity.ScreeningEntity;

public class GetScreeningResponseConverter {
    public GetScreeningResponse convertTo(ScreeningEntity source) {
        if (source == null) {
            return null;
        }
        GetScreeningResponse target = new GetScreeningResponse();
        convertTo(target, source);
        return target;
    }

    public void convertTo(GetScreeningResponse target, ScreeningEntity source) {
        target.setId(source.getId());
        target.setCinemaId(source.getCinema() != null ? source.getCinema().getId() : null);
        target.setMovieId(source.getMovie() != null ? source.getMovie().getId() : null);
        target.setStartTime(source.getStartTime());
    }
}
