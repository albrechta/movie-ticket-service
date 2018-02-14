package com.zuehlke.movieticketservice.movie;

import com.zuehlke.movieticketservice.rating.Rating;
import com.zuehlke.movieticketservice.rating.RatingServiceAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/movies")
public class MovieController {

    private final MovieServiceAdapter movieAdapter;
    private final RatingServiceAdapter ratingAdapter;

    public MovieController(MovieServiceAdapter movieAdapter, RatingServiceAdapter ratingAdapter) {
        this.movieAdapter = movieAdapter;
        this.ratingAdapter = ratingAdapter;
    }

    @GetMapping("")
    public List<Movie> getMovies() {
        return movieAdapter.getAll();
    }

    @GetMapping("{id}")
    public MovieDetail getMovieById(@PathVariable("id") long id) {
        Optional<MovieDetail> movieDetail = movieAdapter.getMovieById(id);
        List<Rating> ratings = ratingAdapter.getRatingsById(id);
        return movieDetail
                .map(detail -> { detail.setRatings(ratings); return detail; })
                .orElseThrow(() -> new MovieNotFoundException(String.format("Movie with id=%d not found", id)));
    }

}
