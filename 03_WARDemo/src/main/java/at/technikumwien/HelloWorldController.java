package at.technikumwien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class HelloWorldController {
	@Autowired
	private ObjectMapper om;
	
	@GetMapping(path = "/helloworld", produces = MediaType.TEXT_PLAIN_VALUE)
	public String sayHelloAsText() {
		return "Hello World!";
	}
	
	@GetMapping(path = "/helloworld", produces = MediaType.TEXT_HTML_VALUE)
	public String sayHelloAsHtml() {
		return "<html><body><h1>Hello World!</h1></body></html>";
	}
	
	@GetMapping(path = "/helloworld", produces = MediaType.APPLICATION_JSON_VALUE)
	public String sayHelloAsJson() throws Exception {
		ObjectNode objectNode = om.createObjectNode().put("message", "Hello World!");
		return om.writeValueAsString(objectNode);
	}	
}
