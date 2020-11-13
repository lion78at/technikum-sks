package at.technikumwien;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.java.Log;

@RestController
@RequestMapping("/resources/news")
@Log
public class NewsResource {
	@Autowired
	private NewsRepository newsRepository;
	
	// TODO add more methods
	
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
}
