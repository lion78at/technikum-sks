package at.technikumwien;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainApp {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewsPU");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Category category = new Category("Bildung");
		em.persist(category);
		
		em.getTransaction().commit();
		
		em.close();
	}
}
