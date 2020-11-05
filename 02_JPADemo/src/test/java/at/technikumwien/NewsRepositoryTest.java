package at.technikumwien;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NewsRepositoryTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private NewsRepository newsRepository;
	
	@BeforeAll
	public static void setUpBeforeClass() {
		emf = Persistence.createEntityManagerFactory("NewsPU");
	}
	
	@BeforeEach
	public void setUp() {
		em = emf.createEntityManager();
		newsRepository = new NewsRepository(em);
		
		em.getTransaction().begin();
	}
	
	@AfterEach
	public void tearDown() {
		em.getTransaction().rollback();
		
		em.close();
	}

	@Test
	public void testSave() {
		News news = new News(
			"Hello Mars!",
			"Am Planeten Mars wurde Leben entdeckt.",
			LocalDate.now(),
			true,
			new Category("Wissenschaft"),
			List.of(new Author(Sex.MALE, "Maxi", "Musterkind"))
		);
		
		news = newsRepository.save(news);
		
		assertNotNull(news.getId());
	}
	
	@Test
	public void findAll() {
		List<News> newsList = newsRepository.findAll();
		
		assertEquals(2, newsList.size());
	}
	
	// TODO add more tests here ;-)
}
