package at.technikumwien;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

// see http://localhost:8888/helloworldservice/default
// see http://localhost:8888/helloworldservice/de

@SpringBootApplication
@EnableConfigServer
public class MainApp {
	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}
}
