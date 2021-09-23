package at.technikumwien;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class News {
    private Long id;
    private String title;
    private String text;
    private LocalDate publicationDate;
    private boolean topNews;
    private Category category;
    private List<Author> authors;

    public News(String title, String text, LocalDate publicationDate, boolean topNews, Category category, List<Author> authors) {
        this(null, title, text, publicationDate, topNews, category, authors);
    }
}
