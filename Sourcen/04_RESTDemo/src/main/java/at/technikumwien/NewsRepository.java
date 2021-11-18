package at.technikumwien;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long> {
    @Override
    @EntityGraph("News.fetchCategoryAuthors")
    Optional<News> findById(Long id);

    @Override
    @EntityGraph("News.fetchCategoryAuthors")
    List<News> findAll();

    @EntityGraph("News.fetchCategoryAuthors")
    List<News> findAllByCategoryId(long categoryId);

    @EntityGraph("News.fetchCategoryAuthors")
    List<News> findAllByAuthorsId(long authorId);

    @EntityGraph("News.fetchCategoryAuthors")
    List<News> findAllByTitleIgnoreCaseContaining(String searchTerm);
}
