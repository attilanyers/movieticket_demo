package com.company.movieticket.dto.list_movie;

import lombok.Data;

@Data
public class ListMovieResponse {

    private Integer id;

    private String title;

    private String director;

    private String genre;

    private Integer duration;

    private Short length;
}
