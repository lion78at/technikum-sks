package at.technikumwien.exercise2;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.List;

// variant 2: uses enhanced Spring Cloud LoadBalancer-backed RestTemplate

@Controller
@Log
public class MovieController {
    @Autowired
    private RestTemplate restTemplate;

    public List<Movie> getMovies() {
        log.info("getMovies()");

        ResponseEntity<List<Movie>> response = restTemplate.exchange(
                "http://MOVIESERVICE/resources/movies",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        return response.getBody();
    }
}
