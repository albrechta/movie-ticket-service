package com.zuehlke.movieticketservice.movie;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuehlke.movieticketservice.rating.Rating;
import com.zuehlke.movieticketservice.rating.RatingServiceAdapter;
import com.zuehlke.movieticketservice.rating.RatingServiceResponse;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieControllerComponentTest {

    @MockBean
    private MovieServiceAdapter movieServiceAdapter;

    @MockBean
    private RatingServiceAdapter ratingServiceAdapter;

    @LocalServerPort
    private int port;

    @Before
    public void setUp() throws Exception {
        RestAssured.port = port;
        
        Mockito.when(ratingServiceAdapter.getRatingsById(1)).thenAnswer(new Answer<List<RatingServiceResponse>>() {
            @Override
            public List<RatingServiceResponse> answer(InvocationOnMock invocation) throws Throwable {
                List<RatingServiceResponse> ratings = new ArrayList<>();
                ratings.add(new RatingServiceResponse("Internet Movie Database", "8.3/10"));
                ratings.add(new RatingServiceResponse("Rotten Tomatoes", "84%"));
                ratings.add(new RatingServiceResponse("Metacritic", "70/100"));
                return ratings;
            }
        });

        Mockito.when(movieServiceAdapter.getMovieById(1))
                .thenAnswer(new Answer<Optional<MovieDetail>>() {
                    @Override
                    public Optional<MovieDetail> answer(InvocationOnMock invocation) throws Throwable {
                        MovieDetail detail = new MovieDetail();
                        detail.setId(1);
                        detail.setTitle("Batman Begins");
                        detail.setPoster("https://images-na.ssl-images-amazon.com/images/M/MV5BNTM3OTc0MzM2OV5BMl5BanBnXkFtZTYwNzUwMTI3._V1_SX300.jpg");
                        detail.setPlot("After training with his mentor, Batman begins his fight to free crime-ridden Gotham City from the corruption that Scarecrow and the League of Shadows have cast upon it.");
                        detail.setYear(2005);
                        detail.setGenre("Action, Adventure");
                        return Optional.of(detail);
                    }
        });

        Mockito.when(movieServiceAdapter.getAll()).thenAnswer(new Answer<List<MovieServiceResponse>>() {
            @Override
            public List<MovieServiceResponse> answer(InvocationOnMock invocation) throws Throwable {
                String movies = "[\n" +
                        "  {\n" +
                        "    \"id\": 1,\n" +
                        "    \"imdbId\": \"tt0372784\",\n" +
                        "    \"title\": \"Batman Begins\",\n" +
                        "    \"poster\": \"https://images-na.ssl-images-amazon.com/images/M/MV5BNTM3OTc0MzM2OV5BMl5BanBnXkFtZTYwNzUwMTI3._V1_SX300.jpg\",\n" +
                        "    \"plot\": \"After training with his mentor, Batman begins his fight to free crime-ridden Gotham City from the corruption that Scarecrow and the League of Shadows have cast upon it.\",\n" +
                        "    \"year\": 2005,\n" +
                        "    \"genre\": \"Action, Adventure\"\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": 2,\n" +
                        "    \"imdbId\": \"tt1637725\",\n" +
                        "    \"title\": \"Ted\",\n" +
                        "    \"poster\": \"https://images-na.ssl-images-amazon.com/images/M/MV5BMTQ1OTU0ODcxMV5BMl5BanBnXkFtZTcwOTMxNTUwOA@@._V1_SX300.jpg\",\n" +
                        "    \"plot\": \"John Bennett, a man whose childhood wish of bringing his teddy bear to life came true, now must decide between keeping the relationship with the bear or his girlfriend, Lori.\",\n" +
                        "    \"year\": 2012,\n" +
                        "    \"genre\": \"Comedy, Fantasy\"\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": 3,\n" +
                        "    \"imdbId\": \"tt1375666\",\n" +
                        "    \"title\": \"Inception\",\n" +
                        "    \"poster\": \"https://images-na.ssl-images-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_SX300.jpg\",\n" +
                        "    \"plot\": \"A thief, who steals corporate secrets through use of dream-sharing technology, is given the inverse task of planting an idea into the mind of a CEO.\",\n" +
                        "    \"year\": 2010,\n" +
                        "    \"genre\": \"Action, Adventure, Sci-Fi\"\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": 4,\n" +
                        "    \"imdbId\": \"tt0068646\",\n" +
                        "    \"title\": \"The Godfather\",\n" +
                        "    \"poster\": \"https://images-na.ssl-images-amazon.com/images/M/MV5BZTRmNjQ1ZDYtNDgzMy00OGE0LWE4N2YtNTkzNWQ5ZDhlNGJmL2ltYWdlL2ltYWdlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg\",\n" +
                        "    \"plot\": \"The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.\",\n" +
                        "    \"year\": 1972,\n" +
                        "    \"genre\": \"Crime, Drama\"\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": 5,\n" +
                        "    \"imdbId\": \"tt0468569\",\n" +
                        "    \"title\": \"The Dark Knight\",\n" +
                        "    \"poster\": \"https://images-na.ssl-images-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_SX300.jpg\",\n" +
                        "    \"plot\": \"When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, the Dark Knight must come to terms with one of the greatest psychological tests of his ability to fight injustice.\",\n" +
                        "    \"year\": 2008,\n" +
                        "    \"genre\": \"Action, Crime, Drama\"\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": 6,\n" +
                        "    \"imdbId\": \"tt0816692\",\n" +
                        "    \"title\": \"Interstellar\",\n" +
                        "    \"poster\": \"https://images-na.ssl-images-amazon.com/images/M/MV5BMjIxNTU4MzY4MF5BMl5BanBnXkFtZTgwMzM4ODI3MjE@._V1_SX300.jpg\",\n" +
                        "    \"plot\": \"A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.\",\n" +
                        "    \"year\": 2014,\n" +
                        "    \"genre\": \"Adventure, Drama, Sci-Fi\"\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": 7,\n" +
                        "    \"imdbId\": \"tt1675434\",\n" +
                        "    \"title\": \"The Intouchables\",\n" +
                        "    \"poster\": \"https://images-na.ssl-images-amazon.com/images/M/MV5BMTYxNDA3MDQwNl5BMl5BanBnXkFtZTcwNTU4Mzc1Nw@@._V1_SX300.jpg\",\n" +
                        "    \"plot\": \"After he becomes a quadriplegic from a paragliding accident, an aristocrat hires a young man from the projects to be his caregiver.\",\n" +
                        "    \"year\": 2011,\n" +
                        "    \"genre\": \"Biography, Comedy, Drama\"\n" +
                        "  }\n" +
                        "]";

                return new ObjectMapper()
                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                        .readValue(movies, new TypeReference<List<MovieServiceResponse>>(){});
            }
        });
    }

    @Test
    public void getMovies() throws Exception {
        when()
                .get("/api/v1/movies")
        .then()
                .statusCode(200)
                .body("movies.size", equalTo(7))
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
                        "genre", equalTo("Action, Adventure"),
                        "ratings.size", equalTo(3),
                        "ratings[0].source", equalTo("Internet Movie Database"),
                        "ratings[0].value", equalTo("8.3/10"),
                        "ratings[1].source", equalTo("Rotten Tomatoes"),
                        "ratings[1].value", equalTo("84%"));
    }

//    @Configuration
//    @Import(UserOfService.class) // A @Component injected with ExampleService
//    static class Config {
//    }

}
