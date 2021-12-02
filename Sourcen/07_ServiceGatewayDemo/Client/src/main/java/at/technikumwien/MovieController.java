package at.technikumwien;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@Log
public class MovieController {
    @Autowired
    private RestTemplate restTemplate;

    public List<Movie> getMovies() {
        log.info("getMovies()");

        ResponseEntity<List<Movie>> response = restTemplate.exchange(
            "http://localhost:5555/api/movie/resources/movies",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<>() {}
        );

        return response.getBody();
    }
}
