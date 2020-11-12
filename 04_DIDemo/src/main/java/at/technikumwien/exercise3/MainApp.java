package at.technikumwien.exercise3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:spring-config.xml")
public class MainApp implements CommandLineRunner {
	@Autowired
	private HelloWorldController hwController1;
	@Autowired
	private HelloWorldController hwController2;
	
	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		hwController1.sayHello();
		hwController1.sayHelloDE();
		
		if (hwController1 == hwController2) {
			System.out.println("SINGLETON");
		}
		else {
			System.out.println("PROTOTYPE");
		}
	}
}
