package com.company.movieticket.dto.converter;

import com.company.movieticket.dto.CustomReservationRequest;
import com.company.movieticket.entity.ReservationEntity;

public class CustomReservationRequestConverter {
    public ReservationEntity convertTo(CustomReservationRequest source) {
        if (source == null) {
            return null;
        }
        ReservationEntity target = new ReservationEntity();
        convertTo(target, source);
        return target;
    }

    public void convertTo(ReservationEntity target, CustomReservationRequest source) {}
}
