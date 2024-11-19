package com.company.movieticket.dto;

import com.company.movieticket.dto.Order;
import jakarta.validation.constraints.Max;
import java.util.List;
import lombok.Data;

@Data
public class ListCinemaRequest {

    private Integer id;

    private String name;

    private String location;

    private List<Order> sort;

    private int pageIndex;

    @Max(value = 100)
    private int pageSize = 20;
}
