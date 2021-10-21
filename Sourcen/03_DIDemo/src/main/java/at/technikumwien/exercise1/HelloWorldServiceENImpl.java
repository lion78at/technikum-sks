package at.technikumwien.exercise1;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service   // @Component
@Primary
@Profile({"default", "en"})
public class HelloWorldServiceENImpl implements HelloWorldService {
    @Override
    public void sayHello() {
        System.out.println("Hello World!");
    }
}
