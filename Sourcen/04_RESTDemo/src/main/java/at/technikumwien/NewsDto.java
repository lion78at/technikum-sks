package at.technikumwien;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.LowerCaseStrategy;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)

@JsonNaming(LowerCaseStrategy.class)
public class NewsDto {
    long id;
    String title;
    String text;
    LocalDate publicationDate;
    boolean topNews;

    @JsonProperty("category")
    String categoryName;   // f.e. "Allgemein"

    @JsonProperty("authors")
    String authorsNames;   // f.e. "Martina Musterfrau, Markus Mustermann" (ordered by lastName ASC, firstName ASC)

    public static NewsDto of(News news) {
        return new NewsDto(
            news.getId(),
            news.getTitle(),
            news.getText(),
            news.getPublicationDate(),
            news.isTopNews(),
            news.getCategory().getName(),
            getAuthorsNames(news.getAuthors())
        );
    }

    private static String getAuthorsNames(List<Author> authors) {
        if (authors == null || authors.isEmpty()) {
            return null;
        }

        return authors.stream()
            .sorted(Comparator.comparing(Author::getLastName).thenComparing(Author::getFirstName))
            .map(author -> author.getFirstName() + " " + author.getLastName())
            .collect(Collectors.joining(", "));
    }
}
