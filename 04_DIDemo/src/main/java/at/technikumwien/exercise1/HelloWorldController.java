package at.technikumwien.exercise1;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)   // optional since default
public class HelloWorldController {
	// step 2a
	@Autowired
	private HelloWorldService hwService;
	private HelloWorldService hwServiceDE;

	// step 1
	public HelloWorldController() {
		System.out.println("HelloWorldController()");
	}
	
	// step 2b
	@Autowired
	@German
	public void setHelloWorldServiceDE(HelloWorldService hwServiceDE) {
		System.out.println("setHelloWorldServiceDE()");
		this.hwServiceDE = hwServiceDE;
	}
	
	// step 3
	@PostConstruct
	public void init() {
		System.out.println("init()");
	}
	
	@PreDestroy
	public void done() {
		System.out.println("done()");
	}
	
	public void sayHello() {
		hwService.sayHello();
	}
	
	public void sayHelloDE() {
		hwServiceDE.sayHello();
	}
}
