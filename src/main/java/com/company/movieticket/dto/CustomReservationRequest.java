package com.company.movieticket.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomReservationRequest {
    @Size(min = 0, max = 0)
    @NotBlank
    private String x;
}
