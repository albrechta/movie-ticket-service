package com.zuehlke.movieticketservice.rating;

import feign.Param;
import feign.RequestLine;

import java.util.List;

public interface RatingServiceApiClient {
    @RequestLine("GET /api/v1/ratings/{id}")
    List<RatingServiceResponse> getRatingsById(@Param("id") long id);
}
