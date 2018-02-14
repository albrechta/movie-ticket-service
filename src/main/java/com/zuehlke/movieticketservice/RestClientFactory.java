package com.zuehlke.movieticketservice;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.HystrixCommand;
import feign.Logger;
import feign.Request;
import feign.hystrix.FallbackFactory;
import feign.hystrix.HystrixFeign;
import feign.jackson.JacksonDecoder;
import feign.slf4j.Slf4jLogger;

public class RestClientFactory {
    public static <T> T createClient(String url, Class<T> clazz) {
        ObjectMapper mapper = createMapper();

        return HystrixFeign.builder()
                .decoder(new JacksonDecoder(mapper))
                .logger(new Slf4jLogger(RestClientFactory.class))
                .logLevel(Logger.Level.FULL)
                .target(clazz, url);
    }

    public static <T> T createClient(String url, Class<T> clazz, FallbackFactory<? extends T> fallback) {
        ObjectMapper mapper = createMapper();

        return HystrixFeign.builder()
                .decoder(new JacksonDecoder(mapper))
                .logger(new Slf4jLogger(RestClientFactory.class))
                .logLevel(Logger.Level.FULL)
                .target(clazz, url, fallback);
    }

    private static ObjectMapper createMapper() {
        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    }

}
