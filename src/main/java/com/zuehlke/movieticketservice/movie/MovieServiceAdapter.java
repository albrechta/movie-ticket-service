package com.zuehlke.movieticketservice.movie;

import com.zuehlke.movieticketservice.RestClientFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MovieServiceAdapter {

    private final MovieServiceApiClient moviesApiClient;

    public MovieServiceAdapter(String url) {
        moviesApiClient = RestClientFactory.createClient(url, MovieServiceApiClient.class);
    }

    public List<Movie> getAll() {
        return moviesApiClient
                .getMovies()
                .stream()
                .map(movieServiceResponse -> new Movie(movieServiceResponse.getId(), movieServiceResponse.getTitle(), movieServiceResponse.getPoster()))
                .collect(Collectors.toList());
    }

    public Optional<MovieDetail> getMovieById(long id) {
        try {
            MovieServiceResponse response = moviesApiClient.getMovieById(id);
            MovieDetail movieDetail = new MovieDetail();
            movieDetail.setId(response.getId());
            movieDetail.setTitle(response.getTitle());
            movieDetail.setGenre(response.getGenre());
            movieDetail.setPoster(response.getPoster());
            movieDetail.setYear(response.getYear());
            movieDetail.setPlot(response.getPlot());
            return Optional.of(movieDetail);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
