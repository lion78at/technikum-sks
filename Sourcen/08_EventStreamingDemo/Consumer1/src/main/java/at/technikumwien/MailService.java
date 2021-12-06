package at.technikumwien;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.annotation.StreamRetryTemplate;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

import lombok.extern.java.Log;

@Service
@Log
public class MailService {
    @StreamListener(Sink.INPUT)
    public void handleReaderEvent(ReaderEvent event) {
        log.info("handleReaderEvent() >> event=" + event);

        Reader reader = event.getReader();

        switch (event.getEventType()) {
            case CREATED:
                log.info("send welcome mail to " + reader.getEmail());
                // TODO add real mail delivery
                break;

            case DELETED:
                log.info("send goodbye mail to " + reader.getEmail());
                // TODO add real mail delivery
                break;
        }
    }
}
