package com.company.movieticket.dto.converter;

import com.company.movieticket.dto.UpdateReservationRequest;
import com.company.movieticket.entity.ReservationEntity;
import com.company.movieticket.entity.ScreeningEntity;
import com.company.movieticket.entity.UserEntity;

public class UpdateReservationRequestConverter {
    public ReservationEntity convertTo(
            UpdateReservationRequest source, ScreeningEntity screening, UserEntity user) {
        if (source == null) {
            return null;
        }
        ReservationEntity target = new ReservationEntity();
        convertTo(target, source, screening, user);
        return target;
    }

    public void convertTo(
            ReservationEntity target,
            UpdateReservationRequest source,
            ScreeningEntity screening,
            UserEntity user) {
        target.setId(source.getId());
        target.setScreening(screening);
        target.setUser(user);
        target.setSeatNumber(source.getSeatNumber());
    }
}
