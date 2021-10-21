package at.technikumwien.exercise1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller   // @Component
public class HelloWorldController {
    @Autowired
    private HelloWorldService hwService;

    public void sayHello() {
        hwService.sayHello();
    }
}
