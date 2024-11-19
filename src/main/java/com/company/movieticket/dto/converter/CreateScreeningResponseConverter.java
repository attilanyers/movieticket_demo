package com.company.movieticket.dto.converter;

import com.company.movieticket.dto.CreateScreeningResponse;
import com.company.movieticket.entity.ScreeningEntity;

public class CreateScreeningResponseConverter {
    public CreateScreeningResponse convertTo(ScreeningEntity source) {
        if (source == null) {
            return null;
        }
        CreateScreeningResponse target = new CreateScreeningResponse();
        convertTo(target, source);
        return target;
    }

    public void convertTo(CreateScreeningResponse target, ScreeningEntity source) {
        target.setId(source.getId());
        target.setCinemaId(source.getCinema() != null ? source.getCinema().getId() : null);
        target.setMovieId(source.getMovie() != null ? source.getMovie().getId() : null);
        target.setStartTime(source.getStartTime());
    }
}
