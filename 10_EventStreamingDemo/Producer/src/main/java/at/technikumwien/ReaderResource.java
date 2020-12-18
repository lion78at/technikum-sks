package at.technikumwien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.http.MediaType;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resources/readers")
public class ReaderResource {
	@Autowired
	private ReaderRepository readerRepository;
	@Autowired
	private Source source;
	
	@Transactional
	@PostMapping(
		path = "/create",
		consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
		produces = MediaType.TEXT_HTML_VALUE
	)
	public String create(@RequestParam String name, @RequestParam String email) {
		boolean error = false;
		
		try {
			// save in database
			Reader reader = readerRepository.save(
				new Reader(name, email)
			);
			
			// send message to Kafka
			Message<Reader> message = MessageBuilder
				.withPayload(reader)
				.build();
			
			source
				.output()
				.send(message);
		}
		catch (Exception e) {
			TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
			error = true;
		}
		
		return "<h1>Anmeldung zum Newsletter " + (error ? "NICHT " : "") + "erfolgreich!</h1>";
	}
}
