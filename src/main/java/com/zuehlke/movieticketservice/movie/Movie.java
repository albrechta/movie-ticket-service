package com.zuehlke.movieticketservice.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    private long id;
    private String title;
    private String poster;
}
