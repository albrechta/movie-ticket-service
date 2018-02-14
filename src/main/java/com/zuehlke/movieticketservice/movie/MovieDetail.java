package com.zuehlke.movieticketservice.movie;

import lombok.Data;

import java.util.List;

@Data
public class MovieDetail {

    private long id;
    private String title;
    private String poster;
    private String plot;
    private int year;
    private String genre;
    private List<Rating> ratings;


//                "plot": "After training with his mentor, Batman begins his fight to free crime-ridden Gotham City from the corruption that Scarecrow and the League of Shadows have cast upon it.",
//                "year": 2005,
//                "genre": "Action",
//                "ratings": [
//            {
//                "source": "Internet Movie Database",
//                    "value": "8.3/10"
//            },
//            {
//                "source": "Rotten Tomatoes",
//                    "value": "84%"
//            }
//  ]
}
