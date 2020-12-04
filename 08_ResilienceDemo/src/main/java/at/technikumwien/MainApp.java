package at.technikumwien;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

// see http://localhost:8080/hystrix/monitor?stream=http://localhost:8080/actuator/hystrix.stream

@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard
public class MainApp {
	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}
}
