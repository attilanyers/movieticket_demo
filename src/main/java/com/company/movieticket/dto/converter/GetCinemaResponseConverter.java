package com.company.movieticket.dto.converter;

import com.company.movieticket.dto.GetCinemaResponse;
import com.company.movieticket.entity.CinemaEntity;

public class GetCinemaResponseConverter {
    public GetCinemaResponse convertTo(CinemaEntity source) {
        if (source == null) {
            return null;
        }
        GetCinemaResponse target = new GetCinemaResponse();
        convertTo(target, source);
        return target;
    }

    public void convertTo(GetCinemaResponse target, CinemaEntity source) {
        target.setId(source.getId());
        target.setName(source.getName());
        target.setLocation(source.getLocation());
    }
}
