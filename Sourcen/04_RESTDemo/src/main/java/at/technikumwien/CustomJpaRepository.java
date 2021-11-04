package at.technikumwien;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

public class CustomJpaRepository<T, ID> extends SimpleJpaRepository<T, ID> {
    private final EntityManager em;

    public CustomJpaRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager em) {
        super(entityInformation, em);
        this.em = em;
    }

    @Override
    @Transactional
    public <S extends T> S save(S entity) {
        return em.merge(entity);
    }
}
