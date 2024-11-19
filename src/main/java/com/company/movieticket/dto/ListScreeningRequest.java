package com.company.movieticket.dto;

import com.company.movieticket.dto.Order;
import jakarta.validation.constraints.Max;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class ListScreeningRequest {

    private Integer id;

    private Integer cinemaId;

    private Integer movieId;

    private LocalDateTime startTimeIsBefore;

    private LocalDateTime startTimeIsAfter;

    private LocalDateTime startTime;

    private List<Order> sort;

    private int pageIndex;

    @Max(value = 100)
    private int pageSize = 20;
}
