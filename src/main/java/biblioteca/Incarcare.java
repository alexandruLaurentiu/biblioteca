package biblioteca;

import java.util.Collections;
import java.util.List;

public class Incarcare {

	private DbConn date = new DbConn();

	public List<Carte> carti() {
		List<Carte> carti = date.getCarti();

		carti.sort((carte1, carte2) -> carte1.getFlag().compareTo(carte2.getFlag()));
		return carti;

		// Autor eminescu = new Autor("Eminescu");
		// Autor zafon = new Autor("Carlos Ruiz Zafon");
		// Autor shackespeare = new Autor("William Shackespeare");
		//
		// Editura humanitas = new Editura("Humanitas");
		// Editura oxford = new Editura("Oxford");
		//
		// Carte carte1 = new Carte(1, "Poezii", eminescu, humanitas, "", "",
		// "", null, false);
		// Carte carte2 = new Carte(2, "Umbra vantului", zafon, humanitas, "",
		// "", "", null, false);
		// Carte carte3 = new Carte(3, "Jocul Ingerului", zafon, oxford, "", "",
		// "", null, false);
		// Carte carte4 = new Carte(4, "Romeo si Julieta", shackespeare,
		// humanitas, "", "", "", null, false);
		// Carte carte5 = new Carte(5, "Hamlet", shackespeare, humanitas, "",
		// "", "", null, false);
		// //
		// return Arrays.asList(carte1, carte2, carte3, carte4, carte5);
		//
		// return Arrays.asList();
	}

}
