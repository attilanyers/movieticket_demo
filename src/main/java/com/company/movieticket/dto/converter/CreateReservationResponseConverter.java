package com.company.movieticket.dto.converter;

import com.company.movieticket.dto.CreateReservationResponse;
import com.company.movieticket.entity.ReservationEntity;

public class CreateReservationResponseConverter {
    public CreateReservationResponse convertTo(ReservationEntity source) {
        if (source == null) {
            return null;
        }
        CreateReservationResponse target = new CreateReservationResponse();
        convertTo(target, source);
        return target;
    }

    public void convertTo(CreateReservationResponse target, ReservationEntity source) {
        target.setId(source.getId());
        target.setScreeningId(source.getScreening() != null ? source.getScreening().getId() : null);
        target.setUserId(source.getUser() != null ? source.getUser().getId() : null);
        target.setSeatNumber(source.getSeatNumber());
    }
}
