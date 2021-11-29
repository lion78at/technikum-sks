package at.technikumwien.exercise1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    private Long id;
    private String title;
    private String description;
    private Genre genre;
    private int length;
    private int releaseYear;
}
