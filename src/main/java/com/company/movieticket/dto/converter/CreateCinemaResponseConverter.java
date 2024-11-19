package com.company.movieticket.dto.converter;

import com.company.movieticket.dto.CreateCinemaResponse;
import com.company.movieticket.entity.CinemaEntity;

public class CreateCinemaResponseConverter {
    public CreateCinemaResponse convertTo(CinemaEntity source) {
        if (source == null) {
            return null;
        }
        CreateCinemaResponse target = new CreateCinemaResponse();
        convertTo(target, source);
        return target;
    }

    public void convertTo(CreateCinemaResponse target, CinemaEntity source) {
        target.setId(source.getId());
        target.setName(source.getName());
        target.setLocation(source.getLocation());
    }
}
