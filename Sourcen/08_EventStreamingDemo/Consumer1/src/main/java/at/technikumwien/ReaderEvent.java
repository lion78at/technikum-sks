package at.technikumwien;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor   // optional since default
@Getter
@ToString
public class ReaderEvent {
	public enum EventType {
		CREATED, DELETED
	}
	
	private long timestamp;
	private EventType eventType;
	private Reader reader;
}
