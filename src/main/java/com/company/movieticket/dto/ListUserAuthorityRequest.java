package com.company.movieticket.dto;

import com.company.movieticket.dto.Order;
import jakarta.validation.constraints.Max;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class ListUserAuthorityRequest {

    private UUID id;

    private UUID userId;

    private String authority;

    private List<Order> sort;

    private int pageIndex;

    @Max(value = 100)
    private int pageSize = 20;
}
