package com.company.movieticket.dto.converter.list_reservation;

import com.company.movieticket.dto.list_reservation.ListReservationResponse;
import com.company.movieticket.entity.ReservationEntity;

public class ListReservationResponseConverter {
    public ListReservationResponse convertTo(ReservationEntity source) {
        if (source == null) {
            return null;
        }
        ListReservationResponse target = new ListReservationResponse();
        convertTo(target, source);
        return target;
    }

    public void convertTo(ListReservationResponse target, ReservationEntity source) {
        target.setId(source.getId());
        target.setScreeningId(source.getScreening() != null ? source.getScreening().getId() : null);
        target.setUserId(source.getUser() != null ? source.getUser().getId() : null);
        target.setSeatNumber(source.getSeatNumber());
    }
}
