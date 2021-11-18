package at.technikumwien;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@Transactional    // rollbacks transaction after each test method
public class NewsRepositoryTest {
    @Autowired
    private NewsRepository newsRepository;

    @Test
    public void testFindAllByTitleIgnoreCaseContaining() {
        List<News> newsList = newsRepository.findAllByTitleIgnoreCaseContaining("world");

        assertEquals(1, newsList.size());
    }
}
