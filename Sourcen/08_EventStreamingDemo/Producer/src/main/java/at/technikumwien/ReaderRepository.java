package at.technikumwien;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReaderRepository extends JpaRepository<Reader, Long> {
	Optional<Reader> findByEmail(String email);
}
