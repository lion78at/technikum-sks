package at.technikumwien;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "t_news")
@NamedQuery(
    name = "News.selectAll",
    query = "SELECT n FROM News n ORDER BY n.title ASC"
)
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 1000)
    private String text;

    @Column(nullable = false)
    private LocalDate publicationDate;

    @Column(nullable = false)
    private boolean topNews;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "categoryid")
    private Category category;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
        name = "t_news_author",
        joinColumns = @JoinColumn(name = "newsid"),
        inverseJoinColumns = @JoinColumn(name = "authorid")
    )
    private List<Author> authors;

    public News(String title, String text, LocalDate publicationDate, boolean topNews, Category category, List<Author> authors) {
        this(null, title, text, publicationDate, topNews, category, authors);
    }
}
