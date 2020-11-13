package at.technikumwien;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class CustomJpaRepository<T, ID> extends SimpleJpaRepository<T, ID> {
	private final EntityManager em;
	
	public CustomJpaRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager em) {
		super(entityInformation, em);
		this.em = em;
	}

	@Override
	public <S extends T> S save(S entity) {
		return em.merge(entity);
	}
}
