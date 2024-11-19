package com.company.movieticket.dto.converter;

import com.company.movieticket.dto.CustomReservationResponse;
import com.company.movieticket.entity.ReservationEntity;

public class CustomReservationResponseConverter {
    public CustomReservationResponse convertTo(ReservationEntity source) {
        if (source == null) {
            return null;
        }
        CustomReservationResponse target = new CustomReservationResponse();
        convertTo(target, source);
        return target;
    }

    public void convertTo(CustomReservationResponse target, ReservationEntity source) {

        // todo: replace this random value assignment
        target.setY("y-209");
    }
}
