package at.technikumwien.exercise1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import lombok.extern.java.Log;

@Controller
@Log
public class MovieController {
	private static final String SERVICE_ID = "MOVIESERVICE";
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private DiscoveryClient discoveryClient;
	
	public List<Movie> getMovies() {
		log.info("getMovies()");
		
		List<ServiceInstance> instances = discoveryClient.getInstances(SERVICE_ID);
		ServiceInstance instance = instances.get(0);
		
		log.info("use instance of " + SERVICE_ID + " with id " + instance.getInstanceId() + " for request");
		
		ResponseEntity<List<Movie>> response = restTemplate.exchange(
			instance.getUri().toString() + "/resources/movies",
			HttpMethod.GET,
			null,
			new ParameterizedTypeReference<List<Movie>>() {}
		);
			
		return response.getBody();
	}
}