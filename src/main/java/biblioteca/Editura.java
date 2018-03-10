package biblioteca;

public class Editura {

	private int id;
	private String nume;
	private boolean filter;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Editura(int id, String nume) {
		this.id = id;
		this.nume = nume;
	}

	public Editura(String nume) {
		this.nume = nume;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public boolean isFilter() {
		return filter;
	}

	public void setFilter(boolean filter) {
		this.filter = filter;
	}

}
