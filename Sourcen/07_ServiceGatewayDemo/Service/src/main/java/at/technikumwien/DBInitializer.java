package at.technikumwien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBInitializer {
    @Autowired
    private MovieRepository movieRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void handleApplicationReady() {
        if (movieRepository.count() == 0) {
            movieRepository.saveAll(List.of(
                new Movie("Der Soldat James Ryan", "Aus der Sicht einer Einheit amerikanischer Soldaten beginnt der Film am Tag der historischen D-Day-Invasion des 2. Weltkrieges mit der Landung am Strand. Von hier aus startet die Einheit zu einem gefährlichen Sonderauftrag: Captain John Miller muss mit seinen Männern hinter die feindlichen Linien dringen, um den Gefreiten James Ryan zu finden, dessen drei Brüder auf dem Schlachtfeld gestorben sind.", Genre.DRAMA, 169, 1998),
                new Movie("Ein gutes Jahr", "Broker Max ist an der Börse bekannt für seine Kaltblütigkeit, wenn's ums Geschäft geht. Als er von einem Onkel ein Weingut erbt, macht er sich sofort auf den Weg dorthin um es so schnell wie möglich zu verkaufen. Vor Ort muss Max jedoch einsehen, dass das nicht so leicht ist, wenn man einen sturen Weinbauern, eine Dorfschönheit und eine Frau gegen sich hat, die behauptet des Onkels Cousine zu sein.", Genre.LOVE, 118, 2006),
                new Movie("Pulp Fiction", "Vincent Vega und Jules Winnfield holen für ihren Boss Marsellus Wallace eine schwarze Aktentasche aus einer Wohnung ab. Drei Jungs, die ihnen dabei im Weg stehen, lassen ihr Leben. Die Killer machen sich mit einem vierten Jungen als Geisel auf den Weg ins Hauptquartier.", Genre.ACTION, 154, 1994),
                new Movie("Sex and the City - Der Film", "Die erfolgreiche Autorin und Stilikone Carrie Bradshaw ist wieder da, und ihre Kommentare sind bissig wie eh und je, wenn sie ihre New Yorker Geschichten weiterspinnt. Natürlich geht es wieder um Sex, Liebe und modebesessene Single-Frauen. Vier Jahre nach Ende der Serie sind Carrie, Samantha, Charlotte und Miranda wieder in Manhattan auf der Jagd nach dem passenden Mann, Kleidungsstück und vulgären Spruch.", Genre.COMEDY, 145, 2008),
                new Movie("Sieben", "Der besonnene Detective Summerset ist ein Kriminologe der 'alten Schule'. Zusammen mit seinem neuen Kollegen, dem Heißsporn Mills, wird er auf den ungewöhnlichsten und erschreckendsten Fall seiner Laufbahn angesetzt. Ein unbekannter Serienkiller versucht scheinbar, die Stadt von allen Sünden zu befreien.", Genre.THRILLER, 127, 1995)
        ));
        }
    }
}
