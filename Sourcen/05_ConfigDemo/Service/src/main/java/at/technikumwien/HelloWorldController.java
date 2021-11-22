package at.technikumwien;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@Controller
public class HelloWorldController {
    @Value("${message.public}")
    private String message;
    @Value("${message.specialguests}")
    private String messageToSpecialGuests;

    public void sayHello() {
        System.out.println(message);
    }

    public void sayHelloToSpecialGuests() {
        System.out.println(messageToSpecialGuests);
    }
}
