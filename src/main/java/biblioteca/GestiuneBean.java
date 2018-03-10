package biblioteca;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

@Named
@ViewScoped
public class GestiuneBean implements Serializable {

	private DbConn connectionToDb = new DbConn();

	private Map<String, Function<String, Predicate<Carte>>> categoryPredicates = new HashMap<>();

	private List<Carte> carti = new ArrayList<>();
	private List<Carte> cartiFiltrate = new ArrayList<>();

	List<Autor> autori = new ArrayList<>();
	List<Editura> edituri = new ArrayList<>();

	private Map<String, List<String>> selectedFilters = new HashMap<>();

	private Incarcare loader = new Incarcare();

	private String searchField;

	@PostConstruct
	public void init() {
		carti = loader.carti();
		initAutori();
		initEdituri();
		resetareFiltre();
	}

	public void filtrare() {
		categoryPredicates.put("authors", GestiuneBean::getAuthorPredicate);
		categoryPredicates.put("editions", GestiuneBean::getEditionPredicate);

		selectedFilters.put("editions", filtreEdituri().stream().map(Editura::getNume).collect(Collectors.toList()));
		selectedFilters.put("authors", filtreAutori().stream().map(Autor::getNume).collect(Collectors.toList()));

		Predicate<Carte> filterPredicate = selectedFilters.entrySet().stream()
				.map((e) -> toPredicate(e.getValue(), e.getKey()).reduce(Predicate::or)).map(o -> o.orElse((b) -> true))
				.reduce(Predicate::and).orElse((b) -> true);

		cartiFiltrate = carti.stream().filter(filterPredicate).collect(Collectors.toList());
		searchField = "";
	}

	public void resetareFiltre() {
		autori.stream().forEach(autor -> autor.setFilter(false));
		edituri.stream().forEach(editura -> editura.setFilter(false));
		cartiFiltrate = carti;
		searchField = "";
	}

	public void imprumuta(Carte carte) {
		carte.setImprumutata(true);
		connectionToDb.editCarte(carte);
	}

	private List<Autor> filtreAutori() {
		return autori.stream().filter(a -> a.isFilter()).collect(Collectors.toList());
	}

	private List<Editura> filtreEdituri() {
		return edituri.stream().filter(c -> c.isFilter()).collect(Collectors.toList());
	}

	private void initAutori() {
		List<Autor> all = carti.stream().map(carte -> carte.getAutor()).collect(Collectors.toList());
		Set<String> numeAutori = all.stream().map(autor -> autor.getNume()).collect(Collectors.toSet());
		autori = numeAutori.stream().map(nume -> new Autor(nume)).collect(Collectors.toList());
	}

	private void initEdituri() {
		List<Editura> all = carti.stream().map(carte -> carte.getEditura()).collect(Collectors.toList());
		Set<String> numeEdituri = all.stream().map(editura -> editura.getNume()).collect(Collectors.toSet());
		edituri = numeEdituri.stream().map(nume -> new Editura(nume)).collect(Collectors.toList());
	}

	private Stream<Predicate<Carte>> toPredicate(List<String> options, String edit) {
		return options.stream().map((v) -> getPredicate(edit, v));
	}

	private Predicate<Carte> getPredicate(String category, String value) {
		return categoryPredicates.get(category).apply(value);
	}

	private static Predicate<Carte> getAuthorPredicate(String authorName) {
		return (b) -> b.getAutor().getNume().equals(authorName);
	}

	private static Predicate<Carte> getEditionPredicate(String edit) {
		return (b) -> b.getEditura().getNume().equals(edit);
	}

	public void returnareCarte(Carte carte) {
		carte.setImprumutata(false);
		carte.getClient().resetClient();
		connectionToDb.editCarte(carte);
	}

	public void freeSearch() {
		cartiFiltrate = carti.stream()
				.filter(carte -> carte.getNume().toLowerCase().contains(searchField.toLowerCase()))
				.collect(Collectors.toList());
	}

	public List<Carte> getCarti() {
		return carti;
	}

	public void setCarti(List<Carte> carti) {
		this.carti = carti;
	}

	public List<Autor> getAutori() {
		return autori;
	}

	public List<Editura> getEdituri() {
		return edituri;
	}

	public List<Carte> getCartiFiltrate() {
		return cartiFiltrate;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

	public String getSearchField() {
		return searchField;
	}

}
