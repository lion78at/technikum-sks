package at.technikumwien;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class NewsResourceTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testRetrieve() {
        ResponseEntity<News> response = restTemplate.getForEntity(
            "http://localhost:{port}/resources/news/1",
            News.class,
            port
        );
        News news = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, news.getId());
    }
}
