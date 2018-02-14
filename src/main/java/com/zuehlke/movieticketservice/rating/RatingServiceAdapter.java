package com.zuehlke.movieticketservice.rating;

import com.zuehlke.movieticketservice.RestClientFactory;
import feign.hystrix.FallbackFactory;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RatingServiceAdapter {

    private final RatingServiceApiClient ratingServiceApiClient;

    public RatingServiceAdapter(String url) {
        FallbackFactory<RatingServiceApiClient> fallback = new FallbackFactory<RatingServiceApiClient>() {
            @Override
            public RatingServiceApiClient create(Throwable throwable) {
                return new RatingServiceApiClient() {
                    @Override
                    public List<RatingServiceResponse> getRatingsById(long id) {
                        return Collections.emptyList();
                    }
                };
            }
        };
        ratingServiceApiClient = RestClientFactory.createClient(url, RatingServiceApiClient.class, fallback);
    }

    public List<Rating> getRatingsById(long id) {
        return ratingServiceApiClient
                .getRatingsById(id)
                .stream()
                .map(rating -> new Rating(rating.getSource(), rating.getValue()))
                .collect(Collectors.toList());
    }

}
