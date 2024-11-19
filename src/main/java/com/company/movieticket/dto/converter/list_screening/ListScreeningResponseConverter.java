package com.company.movieticket.dto.converter.list_screening;

import com.company.movieticket.dto.list_screening.ListScreeningResponse;
import com.company.movieticket.entity.ScreeningEntity;

public class ListScreeningResponseConverter {
    public ListScreeningResponse convertTo(ScreeningEntity source) {
        if (source == null) {
            return null;
        }
        ListScreeningResponse target = new ListScreeningResponse();
        convertTo(target, source);
        return target;
    }

    public void convertTo(ListScreeningResponse target, ScreeningEntity source) {
        target.setId(source.getId());
        target.setCinemaId(source.getCinema() != null ? source.getCinema().getId() : null);
        target.setMovieId(source.getMovie() != null ? source.getMovie().getId() : null);
        target.setStartTime(source.getStartTime());
    }
}
