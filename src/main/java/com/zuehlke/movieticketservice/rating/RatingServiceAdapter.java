package com.zuehlke.movieticketservice.rating;

import com.zuehlke.movieticketservice.RestClientFactory;

import java.util.List;
import java.util.stream.Collectors;

public class RatingServiceAdapter {

    private final RatingServiceApiClient ratingServiceApiClient;

    public RatingServiceAdapter(String url) {
        ratingServiceApiClient = RestClientFactory.createClient(url, RatingServiceApiClient.class);
    }

    public List<Rating> getRatingsById(long id) {
        return ratingServiceApiClient
                .getRatingsById(id)
                .stream()
                .map(rating -> new Rating(rating.getSource(), rating.getValue()))
                .collect(Collectors.toList());
    }

}
