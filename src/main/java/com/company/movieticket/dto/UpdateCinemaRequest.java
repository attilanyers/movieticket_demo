package com.company.movieticket.dto;

import lombok.Data;

@Data
public class UpdateCinemaRequest {

    private Integer id;

    private String name;

    private String location;
}
