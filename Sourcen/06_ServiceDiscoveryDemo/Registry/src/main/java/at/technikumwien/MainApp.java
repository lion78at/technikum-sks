package at.technikumwien;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

// see http://localhost:8761
// see http://localhost:8761/eureka/apps
// see http://localhost:8761/eureka/apps/movieservice

@SpringBootApplication
@EnableEurekaServer
public class MainApp {
    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }
}
