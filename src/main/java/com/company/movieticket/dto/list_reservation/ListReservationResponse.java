package com.company.movieticket.dto.list_reservation;

import java.util.UUID;
import lombok.Data;

@Data
public class ListReservationResponse {

    private Integer id;

    private Integer screeningId;

    private UUID userId;

    private String seatNumber;
}
