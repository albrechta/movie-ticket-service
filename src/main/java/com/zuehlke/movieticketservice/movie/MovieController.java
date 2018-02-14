package com.zuehlke.movieticketservice.movie;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/movies")
public class MovieController {

    private static final List<Movie> MOVIES = new ArrayList<>();
    static {
        Movie m1 = new Movie();
        m1.setId(1);
        m1.setTitle("Batman Begins");
        m1.setPoster("https://images-na.ssl-images-amazon.com/images/M/MV5BNTM3OTc0MzM2OV5BMl5BanBnXkFtZTYwNzUwMTI3._V1_SX300.jpg");
        MOVIES.add(m1);

        Movie m2 = new Movie();
        m2.setId(2);
        m2.setTitle("Ted");
        m2.setPoster("https://images-na.ssl-images-amazon.com/images/M/MV5BMTQ1OTU0ODcxMV5BMl5BanBnXkFtZTcwOTMxNTUwOA@@._V1_SX300.jpg");
        MOVIES.add(m2);

        Movie m3 = new Movie();
        m3.setId(3);
        m3.setTitle("Inception");
        m3.setPoster("https://images-na.ssl-images-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_SX300.jpg");
        MOVIES.add(m3);
    }

    private static MovieDetail MOVIE_DETAIL;
    static {
        MOVIE_DETAIL = new MovieDetail();
        MOVIE_DETAIL.setId(1);
        MOVIE_DETAIL.setTitle("Batman Begins");
        MOVIE_DETAIL.setPoster("https://images-na.ssl-images-amazon.com/images/M/MV5BNTM3OTc0MzM2OV5BMl5BanBnXkFtZTYwNzUwMTI3._V1_SX300.jpg");
        MOVIE_DETAIL.setPlot("After training with his mentor, Batman begins his fight to free crime-ridden Gotham City from the corruption that Scarecrow and the League of Shadows have cast upon it.");
        MOVIE_DETAIL.setYear(2005);
        MOVIE_DETAIL.setGenre("Action");
        MOVIE_DETAIL.setRatings(new ArrayList<>());

        Rating r1 = new Rating();
        r1.setSource("Internet Movie Database");
        r1.setValue("8.3/10");
        MOVIE_DETAIL.getRatings().add(r1);

        Rating r2 = new Rating();
        r2.setValue("84%");
        r2.setSource("Rotten Tomatoes");
        MOVIE_DETAIL.getRatings().add(r2);
    }

    @GetMapping("")
    public List<Movie> getMovies() {
        return MOVIES;
    }

    @GetMapping("{id}")
    public MovieDetail getMovieById(@PathVariable("id") long id) {
        return MOVIE_DETAIL;
    }

}
