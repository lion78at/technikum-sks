package at.technikumwien;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainApp {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewsPU");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();

		NewsRepository newsRepository = new NewsRepository(em);
		
		// ========================================
		
		News news = new News(
			"Hello Mars!",
			"Am Planeten Mars wurde Leben entdeckt.",
			LocalDate.now(),
			true,
			new Category("Wissenschaft"),
			List.of(new Author(Sex.MALE, "Maxi", "Musterkind"))
		);
		news = newsRepository.save(news);
		System.out.println(news);
		
		// ========================================

		Optional<News> optNews = newsRepository.findById(1);
		optNews.ifPresentOrElse(
			System.out::println,   // alternative: news -> System.out.println(news)
			() -> System.out.println("No news found!")
		);
		
		// ========================================
		
		newsRepository.deleteById(news.getId());
		
		// ========================================
		
		newsRepository.findAll()
			.forEach(System.out::println);   // alternative: news -> System.out.println(news)
		
		// ========================================
		
		newsRepository.findAllByCategoryId(1)
			.forEach(System.out::println);   // alternative: news -> System.out.println(news)
		
		// ========================================		
		
		newsRepository.findAllByAuthorsId(1)
			.forEach(System.out::println);   // alternative: news -> System.out.println(news)
		
		// ========================================
		
		em.getTransaction().commit();
		
		em.close();
	}
}
