package at.technikumwien;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.java.Log;

// see http://localhost:8080/resources/news

@RestController
@RequestMapping("/resources/news")
@CrossOrigin
@Log
public class NewsResource {
	@Autowired
	private NewsRepository newsRepository;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody News news) {
		log.info("create() >> news=" + news);
		
		news.setId(null);
		news = newsRepository.save(news);
		
		URI location = WebMvcLinkBuilder.linkTo(
			WebMvcLinkBuilder.methodOn(getClass()).retrieve(news.getId())
		).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/{id}")
	public /* ResponseEntity<News> */ News retrieve(@PathVariable long id) {
		log.info("retrieve() >> id=" + id);
		
//		return newsRepository.findById(id)
//			.map(news -> ResponseEntity.ok(news))
//			.orElse(ResponseEntity.notFound().build());
		
		return newsRepository.findById(id)
			.orElseThrow(() -> new EmptyResultDataAccessException("can't find news with id " + id, 1));
	}
	
	@PutMapping("/{id}")
	public void update(@PathVariable long id, @RequestBody News news) {
		log.info("update() >> id=" + id + ", news=" + news);
		
		news.setId(id);
		newsRepository.save(news);
	}	
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		log.info("delete() >> id=" + id);
		
		newsRepository.deleteById(id);   // throws EmptyResultDataAccessException if news could not be found
	}	
	
	@GetMapping
	public List<News> retrieveAll(@RequestParam("categoryid") Optional<Long> optCategoryId) {
		log.info("retrieveAll() >> optCategoryId=" + optCategoryId);
		
//		return optCategoryId
//			.map(categoryId -> newsRepository.findAllByCategoryId(categoryId))
//			.orElse(newsRepository.findAll());
		
		if (optCategoryId.isPresent()) {
			return newsRepository.findAllByCategoryId(optCategoryId.get());
		}
		else {
			return newsRepository.findAll();
		}
	}
	
	@GetMapping("/dto")
	public List<NewsDto> retrieveAllAsDto() {
		log.info("retrieveAllAsDto()");
		
		return newsRepository.findAll(
			Sort.by(
				Order.desc("topNews"),
				Order.desc("publicationDate")
			)
		).stream()
			.map(news -> NewsDto.of(news))   // Stream<News> => Stream<NewsDto>
			.collect(Collectors.toList());
	}	
}
