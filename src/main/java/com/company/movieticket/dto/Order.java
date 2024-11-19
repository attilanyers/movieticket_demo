package com.company.movieticket.dto;

import com.company.movieticket.dto.DirectionEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Order {
    @NotBlank private String property;
    @NotNull private DirectionEnum direction;
}
