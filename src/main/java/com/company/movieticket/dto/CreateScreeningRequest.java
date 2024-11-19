package com.company.movieticket.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class CreateScreeningRequest {

    private Integer id;

    private Integer cinemaId;

    private Integer movieId;

    private LocalDateTime startTime;
}
