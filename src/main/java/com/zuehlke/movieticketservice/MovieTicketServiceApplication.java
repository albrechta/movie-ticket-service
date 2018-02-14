package com.zuehlke.movieticketservice;

import com.zuehlke.movieticketservice.movie.MovieServiceAdapter;
import com.zuehlke.movieticketservice.rating.RatingServiceAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MovieTicketServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieTicketServiceApplication.class, args);
	}

	@Value("${endpoint.movie-service}")
	private String movieServiceUrl;

	@Value("${endpoint.movie-rating-service}")
	private String movieRatingServiceUrl;

	@Bean
	public MovieServiceAdapter getMovieService() {
		return new MovieServiceAdapter(movieServiceUrl);
	}

	@Bean
	public RatingServiceAdapter getRatingService() {
		return new RatingServiceAdapter(movieRatingServiceUrl);
	}

}
