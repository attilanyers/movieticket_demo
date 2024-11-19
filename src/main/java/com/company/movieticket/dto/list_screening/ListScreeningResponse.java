package com.company.movieticket.dto.list_screening;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ListScreeningResponse {

    private Integer id;

    private Integer cinemaId;

    private Integer movieId;

    private LocalDateTime startTime;
}
