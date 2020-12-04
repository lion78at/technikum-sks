package at.technikumwien;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import lombok.extern.java.Log;

@RestController
@RequestMapping("/resources/echo")
@Log
public class EchoResource {
	private enum State {
		OK, TIMEOUT, EXCEPTION
	};
	
	private State state = State.OK;
	
	@HystrixCommand(
		commandProperties = {
			@HystrixProperty(
				name = "execution.isolation.thread.timeoutInMilliseconds",
				value = "1000"
			)
		},
		fallbackMethod = "echoFallback"
	)
	@GetMapping("/{text}")
	public EchoResult echo(@PathVariable String text) throws Exception {
		log.info("echo() >> text=" + text);
		
		// sets next state and simulates problem (timeout, exception)
		switch (state) {
			case OK:
				state = State.TIMEOUT;
				break;
			
			case TIMEOUT:
				state = State.EXCEPTION;
				Thread.sleep(2000);
				break;
				
			case EXCEPTION:
				state = State.OK;
				throw new RuntimeException("dummy");
		}
		
		HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create("http://echo.lion.enterprises?text=" + URLEncoder.encode(text, StandardCharsets.UTF_8)))
			.GET()
			.build();
		
		HttpClient client = HttpClient.newHttpClient();
		
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		
		return new EchoResult(response.body(), "echo");
	}
	
	@SuppressWarnings("unused")
	private EchoResult echoFallback(String text) {
		log.info("echoFallback() >> text=" + text);
		
		return new EchoResult(text, "echoFallback");
	}
}
