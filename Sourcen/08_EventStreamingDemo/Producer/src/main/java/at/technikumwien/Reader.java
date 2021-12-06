package at.technikumwien;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// a reader is a subscriber to the newsletter, but this term wasn't used to avoid erroneous associations regarding messaging

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "t_reader")
public class Reader {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 5, max = 50)
	private String name;
	
	@NotNull
	@NotEmpty
	@Email
	@Column(unique = true)
	private String email;

	public Reader(String name, String email) {
		this(null, name, email);
	}
}
