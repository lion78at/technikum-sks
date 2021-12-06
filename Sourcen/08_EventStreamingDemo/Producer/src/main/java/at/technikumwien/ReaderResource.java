package at.technikumwien;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.java.Log;

@RestController
@RequestMapping("/resources/readers")
@Log
public class ReaderResource {
	@Autowired
	private ReaderRepository readerRepository;
	@Autowired
	private Source source;
	
	// alternative: use default resource path and intended HTTP methods (POST, DELETE) for actions
	
	@PostMapping(
		path = "/create",
		consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
		produces = MediaType.TEXT_HTML_VALUE
	)
	@Transactional   // NOTE: Kafka doesn't support XA transactions
	public String create(
		@RequestParam String name,
		@RequestParam String email
	) {
		log.info("create() >> name=" + name + ", email=" + email);
		
		boolean error = false;
		
		try {
			Reader reader = readerRepository.save(
				new Reader(name, email)
			);
			
			sendMessage(
				ReaderEvent.forCreated(reader)
			);
		}
		catch (Exception e) {
			if (!(e instanceof ConstraintViolationException || e instanceof DataIntegrityViolationException)) {
				log.severe(e.getMessage());
			}
			
			TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
			error = true;
		}
		
		return
			"<!DOCTYPE html>" +
			"<h1>Anmeldung zum Newsletter " + (error ? "<b>NICHT</b> " : "") + "erfolgreich!</h1>";
	}

	@PostMapping(
		path = "/delete",
		consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
		produces = MediaType.TEXT_HTML_VALUE
	)
	@Transactional
	public String delete(@RequestParam String email) {
		log.info("delete() >> email=" + email);
		
		boolean error = readerRepository.findByEmail(email)
			.map(
				reader -> {
					try {
						readerRepository.delete(reader);
					
						sendMessage(
							ReaderEvent.forDeleted(reader)
						);
						
						return false;						
					}
					catch (Exception e) {
						log.severe(e.getMessage());
						
						TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
						return true;
					}
				}
			)
			.orElse(true);
		
		return
			"<!DOCTYPE html>" +
			"<h1>Abmeldung vom Newsletter " + (error ? "<b>NICHT</b> " : "") + "erfolgreich!</h1>";
	}

	private void sendMessage(ReaderEvent event) {
		Message<ReaderEvent> message = MessageBuilder
			.withPayload(event)
			.build();

		source
			.output()
			.send(message);
	}
}