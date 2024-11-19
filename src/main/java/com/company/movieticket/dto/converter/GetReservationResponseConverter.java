package com.company.movieticket.dto.converter;

import com.company.movieticket.dto.GetReservationResponse;
import com.company.movieticket.entity.ReservationEntity;

public class GetReservationResponseConverter {
    public GetReservationResponse convertTo(ReservationEntity source) {
        if (source == null) {
            return null;
        }
        GetReservationResponse target = new GetReservationResponse();
        convertTo(target, source);
        return target;
    }

    public void convertTo(GetReservationResponse target, ReservationEntity source) {
        target.setId(source.getId());
        target.setScreeningId(source.getScreening() != null ? source.getScreening().getId() : null);
        target.setUserId(source.getUser() != null ? source.getUser().getId() : null);
        target.setSeatNumber(source.getSeatNumber());
    }
}
