package biblioteca;

import java.util.Date;

public class Carte {

	private int id;
	private String nume;
	private Autor autor;
	private Editura editura;
	private Client client = new Client();
	private boolean imprumutata;
	private boolean show;

	public Carte()  {

	}

	public Carte(String nume, Autor autor, Editura editura) {
		this.nume = nume;
		this.autor = autor;
		this.editura = editura;
	}

	public Carte(int id, String nume, Autor autor, Editura editura, String numeClient, String prenumeClient,
			String telefonClient, Date endDate, boolean imprmutata) {
		this.id = id;
		this.nume = nume;
		this.autor = autor;
		this.editura = editura;
		this.client.setNume(numeClient);
		this.client.setPrenume(prenumeClient);
		this.client.setPhone(telefonClient);
		this.client.setEndDate(endDate);
		this.imprumutata = imprmutata;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public boolean isImprumutata() {
		return imprumutata;
	}

	public void setImprumutata(boolean imprumutata) {
		this.imprumutata = imprumutata;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public Editura getEditura() {
		return editura;
	}

	public void setEditura(Editura editura) {
		this.editura = editura;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
