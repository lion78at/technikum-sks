package at.technikumwien.exercise1;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@German
@Profile({ "default", "de" })
public class HelloWorldServiceDEImpl implements HelloWorldService {
	@Override
	public void sayHello() {
		System.out.println("Hallo Welt!");
	}
}
