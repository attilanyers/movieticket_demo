package com.company.movieticket.dto.converter;

import com.company.movieticket.dto.CinemaDto;
import com.company.movieticket.entity.CinemaEntity;

public class CinemaDtoConverter {
    public CinemaEntity convertTo(CinemaDto source) {
        if (source == null) {
            return null;
        }
        CinemaEntity target = new CinemaEntity();
        convertTo(target, source);
        return target;
    }

    public void convertTo(CinemaEntity target, CinemaDto source) {
        target.setId(source.getId());
        target.setName(source.getName());
        target.setLocation(source.getLocation());
    }

    public CinemaDto convertTo(CinemaEntity source) {
        if (source == null) {
            return null;
        }
        CinemaDto target = new CinemaDto();
        convertTo(target, source);
        return target;
    }

    public void convertTo(CinemaDto target, CinemaEntity source) {
        target.setId(source.getId());
        target.setName(source.getName());
        target.setLocation(source.getLocation());
    }
}
