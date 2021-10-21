package at.technikumwien.exercise2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

public class HelloWorldController {
    @Autowired
    private HelloWorldService hwService;

    public void sayHello() {
        hwService.sayHello();
    }
}
