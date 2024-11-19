package com.company.movieticket.dto.converter.list_cinema;

import com.company.movieticket.dto.list_cinema.ListCinemaResponse;
import com.company.movieticket.entity.CinemaEntity;

public class ListCinemaResponseConverter {
    public ListCinemaResponse convertTo(CinemaEntity source) {
        if (source == null) {
            return null;
        }
        ListCinemaResponse target = new ListCinemaResponse();
        convertTo(target, source);
        return target;
    }

    public void convertTo(ListCinemaResponse target, CinemaEntity source) {
        target.setId(source.getId());
        target.setName(source.getName());
        target.setLocation(source.getLocation());
    }
}
