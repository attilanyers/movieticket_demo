package com.company.movieticket.dto;

import lombok.Data;

@Data
public class CreateCinemaRequest {

    private Integer id;

    private String name;

    private String location;
}
