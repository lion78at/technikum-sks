package at.technikumwien;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class DBInitializer {
	@Autowired
	private NewsRepository newsRepository;
	
	@Transactional
	@EventListener(ApplicationReadyEvent.class)
	public void handleApplicationReady() {
		News news = newsRepository.save(
			new News(
				"Hello World!",
				"Herzlich willkommen am Planeten Erde.",
				LocalDate.of(2019, 1, 1),
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
				LocalDate.of(2019, 1, 2),
				true,
				new Category(category.getId(), category.getName()),
				null
			)
		);
	}
}
