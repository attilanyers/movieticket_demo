package com.company.movieticket.dto;

import com.company.movieticket.dto.Order;
import jakarta.validation.constraints.Max;
import java.util.List;
import lombok.Data;

@Data
public class ListMovieRequest {

    private Integer id;

    private String title;

    private String director;

    private String genre;

    private Integer durationGreaterThanOrEqualTo;

    private Integer durationLessThanOrEqualTo;

    private Integer duration;

    private Short lengthGreaterThanOrEqualTo;

    private Short lengthLessThanOrEqualTo;

    private Short length;

    private List<Order> sort;

    private int pageIndex;

    @Max(value = 100)
    private int pageSize = 20;
}
