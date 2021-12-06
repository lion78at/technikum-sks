package at.technikumwien;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor   // optional since default
@Getter
@ToString
public class Reader {
	private long id;
	private String name;
	private String email;
}