package at.technikumwien;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class Reader {
	private Long id;
	private String name;
	private String email;
}
