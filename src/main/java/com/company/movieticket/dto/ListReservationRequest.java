package com.company.movieticket.dto;

import com.company.movieticket.dto.Order;
import jakarta.validation.constraints.Max;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class ListReservationRequest {

    private Integer id;

    private Integer screeningId;

    private UUID userId;

    private String seatNumber;

    private List<Order> sort;

    private int pageIndex;

    @Max(value = 100)
    private int pageSize = 20;
}
