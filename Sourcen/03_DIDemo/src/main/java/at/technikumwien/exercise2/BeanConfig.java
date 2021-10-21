package at.technikumwien.exercise2;

import org.springframework.context.annotation.*;

@Configuration    // @Component
public class BeanConfig {
    @Bean
    // @Scope("prototype")
    public HelloWorldController hwController() {
        return new HelloWorldController();
    }

    @Bean
    @Primary
    @Profile({"default", "en"})
    public HelloWorldService hwServiceEN() {
        return new HelloWorldServiceENImpl();
    }

    @Bean
    @Profile({"default", "de"})
    public HelloWorldService hwServiceDE() {
        return new HelloWorldServiceDEImpl();
    }
}
