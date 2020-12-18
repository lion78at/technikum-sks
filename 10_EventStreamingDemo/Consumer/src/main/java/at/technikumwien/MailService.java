package at.technikumwien;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

import lombok.extern.java.Log;

@Component
@Log
public class MailService {
	@StreamListener(Sink.INPUT)
	public void handleReader(Reader reader) {
		log.info("handleReader() >> reader=" + reader);
		
		log.info("send welcome mail to reader");
	}
}
