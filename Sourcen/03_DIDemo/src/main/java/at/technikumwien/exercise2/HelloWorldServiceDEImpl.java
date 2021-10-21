package at.technikumwien.exercise2;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

public class HelloWorldServiceDEImpl implements HelloWorldService {
    @Override
    public void sayHello() {
        System.out.println("Hallo Welt!");
    }
}
