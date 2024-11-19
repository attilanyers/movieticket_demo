package com.company.movieticket.dto;

import lombok.Data;

@Data
public class GetMovieResponse {

    private Integer id;

    private String title;

    private String director;

    private String genre;

    private Integer duration;
}