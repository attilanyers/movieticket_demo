package com.company.movieticket.dto.converter;

import com.company.movieticket.dto.UpdateCinemaRequest;
import com.company.movieticket.entity.CinemaEntity;

public class UpdateCinemaRequestConverter {
    public CinemaEntity convertTo(UpdateCinemaRequest source) {
        if (source == null) {
            return null;
        }
        CinemaEntity target = new CinemaEntity();
        convertTo(target, source);
        return target;
    }

    public void convertTo(CinemaEntity target, UpdateCinemaRequest source) {
        target.setId(source.getId());
        target.setName(source.getName());
        target.setLocation(source.getLocation());
    }
}
