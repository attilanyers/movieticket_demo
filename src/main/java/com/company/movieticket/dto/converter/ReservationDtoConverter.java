package com.company.movieticket.dto.converter;

import com.company.movieticket.dto.ReservationDto;
import com.company.movieticket.entity.ReservationEntity;
import com.company.movieticket.entity.ScreeningEntity;
import com.company.movieticket.entity.UserEntity;

public class ReservationDtoConverter {
    public ReservationEntity convertTo(
            ReservationDto source, ScreeningEntity screening, UserEntity user) {
        if (source == null) {
            return null;
        }
        ReservationEntity target = new ReservationEntity();
        convertTo(target, source, screening, user);
        return target;
    }

    public void convertTo(
            ReservationEntity target,
            ReservationDto source,
            ScreeningEntity screening,
            UserEntity user) {
        target.setId(source.getId());
        target.setScreening(screening);
        target.setUser(user);
        target.setSeatNumber(source.getSeatNumber());
    }

    public ReservationDto convertTo(ReservationEntity source) {
        if (source == null) {
            return null;
        }
        ReservationDto target = new ReservationDto();
        convertTo(target, source);
        return target;
    }

    public void convertTo(ReservationDto target, ReservationEntity source) {
        target.setId(source.getId());
        target.setScreeningId(source.getScreening() != null ? source.getScreening().getId() : null);
        target.setUserId(source.getUser() != null ? source.getUser().getId() : null);
        target.setSeatNumber(source.getSeatNumber());
    }
}
