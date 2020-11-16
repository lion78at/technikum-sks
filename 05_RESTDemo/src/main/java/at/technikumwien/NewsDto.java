package at.technikumwien;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

// data transfer object pattern

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NewsDto {
	long id;
	String title;
	String text;
	LocalDate publicationDate;
	boolean topNews;
	@JsonProperty("category")
	String categoryName;
	@JsonProperty("authors")
	String authorsNames;   // f.e. Martina Musterfrau, Markus Mustermann
	
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
			.map(author -> author.getFirstName() + " " + author.getLastName())   // Stream<Person> => Stream<String>
			.collect(Collectors.joining(", "));
	}
}
