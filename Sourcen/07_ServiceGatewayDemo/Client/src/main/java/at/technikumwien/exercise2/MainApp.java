package at.technikumwien.exercise2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class MainApp {
    @Autowired
    private MovieController movieController;

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }

    @Scheduled(fixedDelay = 3000)
    public void run() {
        System.out.println();

        try {
            movieController.getMovies()
                .forEach(System.out::println);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
