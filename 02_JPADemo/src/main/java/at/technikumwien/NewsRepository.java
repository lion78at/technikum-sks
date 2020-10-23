package at.technikumwien;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import lombok.AllArgsConstructor;

// data access object (DAO) pattern

@AllArgsConstructor
public class NewsRepository {
	private EntityManager em;
	
	public News save(News news) {
		return em.merge(news);
	}
	
	public List<News> saveAll(List<News> newsList) {
		return newsList.stream()
			.map(this::save)   // alternative: news -> save(news)
			.collect(Collectors.toList());
	}
	
	public Optional<News> findById(long id) {
		News news = em.find(News.class, id);
		return Optional.ofNullable(news);
	}
	
	public void deleteById(long id) {
		News news = findById(id).get();   // throws NoSuchElementException if news could not be found
		em.remove(news);
	}
	
	public long count() {
		// TODO implement method
		return -1;
	}
	
	public List<News> findAll() {
		return em.createNamedQuery("News.selectAll", News.class)
			.getResultList();
	}
	
	public List<News> findAllByCategoryId(long categoryId) {
		return em.createNamedQuery("News.selectAllByCategoryId", News.class)
			.setParameter("categoryid", categoryId)
			.getResultList();
	}
	
	public List<News> findAllByAuthorsId(long authorId) {
		// TODO implement method
		return null;
	}	
}
