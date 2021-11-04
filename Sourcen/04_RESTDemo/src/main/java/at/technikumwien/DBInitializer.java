package at.technikumwien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@Component
public class DBInitializer {
    @Autowired
    private NewsRepository newsRepository;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void handleApplicationReady() {
        System.out.println("initialize database ...");

        News news = newsRepository.save(
            new News(
                "Hello World!",
                "Herzlich willkommen am Planeten Erde.",
                LocalDate.of(2021, 1, 1),
                false,
                new Category("Allgemein"),
                List.of(
                    new Author(Sex.MALE, "Markus", "Mustermann"),
                    new Author(Sex.FEMALE, "Martina", "Musterfrau")
                )
            )
        );

        Category category = news.getCategory();

        newsRepository.save(
            new News(
                "News-Portal online!",
                "Unser neues News-Portal ist online.",
                LocalDate.of(2021, 1, 2),
                true,
                new Category(category.getId(), category.getName()),
                null
            )
        );
    }
}
