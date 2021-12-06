package at.technikumwien;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.extern.java.Log;

@Service
@Log
public class ExportService {
	private static final Path PATH_EXPORT_FILE = Path.of("readers.txt");
	
	public void addEmail(String email) {
		log.info("addEmail() >> email=" + email);
		
		try {
			Files.writeString(
				PATH_EXPORT_FILE,
				email + System.lineSeparator(),
				StandardOpenOption.CREATE, StandardOpenOption.APPEND
			);
		}
		catch (IOException ioe) {
			throw new RuntimeException("can't add email: " + ioe.getMessage());
		}
	}
	
	public void removeEmail(String email) {
		log.info("removeEmail() >> email=" + email);

		try {
			Files.write(
				PATH_EXPORT_FILE,
				Files.lines(PATH_EXPORT_FILE)
					.filter(line -> !line.equalsIgnoreCase(email))
					.collect(Collectors.toList())
			);
		}
		catch (IOException ioe) {
			throw new RuntimeException("can't remove email: " + ioe.getMessage());			
		}
	}	
}