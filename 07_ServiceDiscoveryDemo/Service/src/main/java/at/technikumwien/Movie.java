package at.technikumwien;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "t_movie")
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Column(nullable = false, length = 1000)
	private String description;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10)
	private Genre genre;
	
	@Column(nullable = false)
	private int length;
	
	@Column(nullable = false)
	private int releaseYear;

	public Movie(String title, String description, Genre genre, int length, int releaseYear) {
		this(null, title, description, genre, length, releaseYear);
	}
}