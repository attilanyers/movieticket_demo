package com.company.movieticket.dto;

import java.util.UUID;
import lombok.Data;

@Data
public class GetReservationResponse {

    private Integer id;

    private Integer screeningId;

    private UUID userId;

    private String seatNumber;
}