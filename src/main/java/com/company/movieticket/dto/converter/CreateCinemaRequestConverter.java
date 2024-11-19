package com.company.movieticket.dto.converter;

import com.company.movieticket.dto.CreateCinemaRequest;
import com.company.movieticket.entity.CinemaEntity;

public class CreateCinemaRequestConverter {
    public CinemaEntity convertTo(CreateCinemaRequest source) {
        if (source == null) {
            return null;
        }
        CinemaEntity target = new CinemaEntity();
        convertTo(target, source);
        return target;
    }

    public void convertTo(CinemaEntity target, CreateCinemaRequest source) {
        target.setId(source.getId());
        target.setName(source.getName());
        target.setLocation(source.getLocation());
    }
}
