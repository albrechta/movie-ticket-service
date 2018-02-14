package com.zuehlke.movieticketservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MovieTicketServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieTicketServiceApplication.class, args);
	}

	@Bean("movieServiceUrl")
	public String getMovieServiceUrl() {
		return "https://movie-service.herokuapp.com/";
	}

	@Bean("ratingServiceUrl")
	public String getRatingServiceUrl() {
		return "https://movie-rating-service.herokuapp.com";
	}


}
