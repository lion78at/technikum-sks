package at.technikumwien;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class StreamConfig {
    @Bean
    public Consumer<ReaderEvent> handleReaderEvent(ExportService exportService) {
        return event -> {
            Reader reader = event.getReader();

            switch (event.getEventType()) {
                case CREATED:
                    exportService.addEmail(reader.getEmail());
                    break;

                case DELETED:
                    exportService.removeEmail(reader.getEmail());
                    break;
            }
        };
    }
}
