package com.zuehlke.movieticketservice.movie;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieControllerComponentTest {

    @LocalServerPort
    private int port;

    @Before
    public void setUp() throws Exception {
        RestAssured.port = port;
    }

    @Test
    public void getMovies() throws Exception {
        when()
                .get("/api/v1/movies")
        .then()
                .statusCode(200)
                .body("movies.size", equalTo(3))
                .body("[0].id", equalTo(1),
                        "[0].title", equalTo("Batman Begins"),
                        "[0].poster", equalTo("https://images-na.ssl-images-amazon.com/images/M/MV5BNTM3OTc0MzM2OV5BMl5BanBnXkFtZTYwNzUwMTI3._V1_SX300.jpg"),
                        "[1].id", equalTo(2),
                        "[1].title", equalTo("Ted"),
                        "[1].poster", equalTo("https://images-na.ssl-images-amazon.com/images/M/MV5BMTQ1OTU0ODcxMV5BMl5BanBnXkFtZTcwOTMxNTUwOA@@._V1_SX300.jpg"),
                        "[2].id", equalTo(3),
                        "[2].title", equalTo("Inception"),
                        "[2].poster", equalTo("https://images-na.ssl-images-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_SX300.jpg")
                        );
    }

    @Test
    public void getMovieById() throws Exception {
        when()
                .get("/api/v1/movies/{id}", 1)
        .then()
                .statusCode(200)
                .body("id", equalTo(1),
                 "title", equalTo("Batman Begins"),
                        "plot", equalTo("After training with his mentor, Batman begins his fight to free crime-ridden Gotham City from the corruption that Scarecrow and the League of Shadows have cast upon it."),
                        "year", equalTo(2005),
                        "genre", equalTo("Action"),
                        "ratings.size", equalTo(2),
                        "ratings[0].source", equalTo("Internet Movie Database"),
                        "ratings[0].value", equalTo("8.3/10"),
                        "ratings[1].source", equalTo("Rotten Tomatoes"),
                        "ratings[1].value", equalTo("84%"));
    }

}
