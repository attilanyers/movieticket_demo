package com.company.movieticket.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomReservationResponse {
    @Size(min = 0, max = 0)
    private String y;
}
