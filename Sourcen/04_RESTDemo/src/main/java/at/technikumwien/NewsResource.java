package at.technikumwien;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        news.setId(null);   // better safe than sorry
        news = newsRepository.save(news);

        URI location = WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder.methodOn(getClass()).retrieve(news.getId())
        ).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public News retrieve(@PathVariable long id) {
        log.info("retrieve() >> id=" + id);

        return newsRepository.findById(id)
            .orElseThrow(
                () -> new EmptyResultDataAccessException("can't find news with id " + id, 1)
            );
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody News news) {
        log.info("update() >> id=" + id + ", news=" + news);

        news.setId(id);   // better safe than sorry
        newsRepository.save(news);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        log.info("delete() >> id=" + id);

        newsRepository.deleteById(id);   // throw EmptyResultDataAccessException if news could not be found
    }

    @GetMapping
    public List<News> retrieveAll(
        @RequestParam("categoryid") Optional<Long> optCategoryId,
        @RequestParam("authorid") Optional<Long> optAuthorId,
        @RequestParam("searchterm") Optional<String> optSearchTerm
    ) {
        log.info("retrieveAll() >> categoryId=" + optCategoryId + ", authorId=" + optAuthorId + ", searchTerm=" + optSearchTerm);

        int numberOfSetParameters = (int) Stream.of(optCategoryId, optAuthorId, optSearchTerm)
            .filter(Optional::isPresent)
            .count();

        switch (numberOfSetParameters) {
            case 0:
                return newsRepository.findAll();

            case 1:
                if (optCategoryId.isPresent()) {
                    return newsRepository.findAllByCategoryId(optCategoryId.get());
                }
                else if (optAuthorId.isPresent()) {
                    return newsRepository.findAllByAuthorsId(optAuthorId.get());
                }
                else if (optSearchTerm.isPresent()) {
                    return newsRepository.findAllByTitleIgnoreCaseContaining(optSearchTerm.get());
                }

            default:
                throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @GetMapping("/dto")
    public List<NewsDto> retrieveAllAsDto() {
        log.info("retrieveAllAsDto()");

        return newsRepository.findAll()
            .stream()
            .map(NewsDto::of)
            .collect(Collectors.toList());
    }
}
