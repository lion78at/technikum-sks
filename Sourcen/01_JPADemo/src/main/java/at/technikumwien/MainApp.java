package at.technikumwien;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewsPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        News news = new News(
            "Hello Mars!",
            "Am Planeten Mars wurde Leben entdeckt.",
            LocalDate.now(),
            true,
            new Category("Allgemein"),
            List.of(
                new Author(Sex.MALE, "Maxi", "Musterkind")
            )
        );
        em.merge(news);

        news = em.find(News.class, 1L);
        if (news != null) {
            System.out.println("found news: " + news);
        }
        // em.clear();
        news.setTitle("Hallo Mars !!!");

        List<News> newsList = em.createNamedQuery("News.selectAll", News.class)
            .getResultList();
        newsList.forEach(System.out::println);

        em.remove(news);

        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
