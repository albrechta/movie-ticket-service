package com.zuehlke.movieticketservice.rating;

import com.zuehlke.movieticketservice.RestClientFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RatingServiceAdapter {

    private final RatingServiceApiClient ratingServiceApiClient;

    public RatingServiceAdapter(@Qualifier("ratingServiceUrl") String url) {
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
