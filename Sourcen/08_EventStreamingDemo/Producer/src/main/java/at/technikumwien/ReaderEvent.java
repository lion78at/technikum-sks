package at.technikumwien;

import java.time.Instant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReaderEvent {
	public enum EventType {
		CREATED, DELETED
	}
	
	long timestamp;
	EventType eventType;
	Reader reader;
	
	public static ReaderEvent forCreated(Reader reader) {
		return new ReaderEvent(
			Instant.now().toEpochMilli(),
			EventType.CREATED,
			reader
		);
	}
	
	public static ReaderEvent forDeleted(Reader reader) {
		return new ReaderEvent(
			Instant.now().toEpochMilli(),
			EventType.DELETED,
			reader
		);
	}
}
