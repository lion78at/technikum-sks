package at.technikumwien.exercise2;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

@Configuration
public class BeanConfig {
	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)   // optional since default
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
	@German
	@Profile({"default", "de"})
	public HelloWorldService hwServiceDE() {
		return new HelloWorldServiceDEImpl();
	}
	
	@Bean
	@Qualifier("french")
	@Profile({"default", "fr"})
	public HelloWorldService hwServiceFR() {
		return new HelloWorldServiceFRImpl();
	}
}
