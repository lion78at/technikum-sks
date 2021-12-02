package at.technikumwien.exercise1;

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

// variant 1: uses Eureka discovery client, default RestTemplate and custom load balancing

@Controller
@Log
public class MovieController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;

    @Retryable(
        maxAttempts = 2,
        include = RuntimeException.class
    )
    public List<Movie> getMovies() {
        log.info("getMovies()");

        List<ServiceInstance> instances = discoveryClient.getInstances("MOVIESERVICE");
        if (instances == null || instances.isEmpty()) {
            throw new RuntimeException("can't get instance since list is empty");
        }
        ServiceInstance instance = instances.get(0);

        log.info("use instance with id " + instance.getInstanceId() + " for request");

        ResponseEntity<List<Movie>> response = restTemplate.exchange(
                instance.getUri().toString() + "/resources/movies",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        return response.getBody();
    }
}
