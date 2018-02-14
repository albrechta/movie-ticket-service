package com.zuehlke.movieticketservice.movie;

import lombok.Data;

@Data
public class Movie {
    private long id;
    private String title;
    private String poster;
}
