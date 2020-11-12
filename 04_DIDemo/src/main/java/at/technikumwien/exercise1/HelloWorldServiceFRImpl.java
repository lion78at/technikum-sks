package at.technikumwien.exercise1;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Qualifier("french")
@Profile({ "default", "fr" })
public class HelloWorldServiceFRImpl implements HelloWorldService {
	@Override
	public void sayHello() {
		System.out.println("Bonjour le monde!");
	}
}
