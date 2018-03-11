package biblioteca;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbConn {

	private static final String toateCartile = "select * from carti";

	private String url = "jdbc:mysql://localhost:3306/biblioteca";
	private String username = "root";
	private String password = "admin";

	private Connection conn;
	private Statement stmt;

	private Statement interogateDB() {
		try {
			// Get a connection
			conn = DriverManager.getConnection(url, username, password);
			stmt = conn.createStatement();
		} catch (Exception except) {
			except.printStackTrace();
		}

		return stmt;
	}

	public List<Carte> getCarti() {
		List<Carte> result = new ArrayList<>();
		try {
			ResultSet rs = interogateDB().executeQuery(toateCartile);
			while (rs.next()) {
				Carte carte = new Carte(rs.getInt("id"), rs.getString("nume"), new Autor(rs.getString("autor")),
						new Editura(rs.getString("editura")),
						rs.getString("numeClient").equals("null") ? "" : rs.getString("numeClient"),
						rs.getString("prenumeClient").equals("null") ? "" : rs.getString("prenumeClient"),
						rs.getString("telefonClient").equals("null") ? "" : rs.getString("telefonClient"),
						rs.getDate("endDate"), rs.getBoolean("imprumutata"));
				result.add(carte);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("List with all books: ");
		result.stream().map(carte -> carte.getNume()).forEach(System.out::println);
		return result;
	}

	public void addCarte(Carte carte) {
		try {
			interogateDB().executeUpdate("insert into carti values (NULL, '" + carte.getNume() + "', '"
					+ carte.getAutor().getNume() + "', '" + carte.getEditura().getNume() + "', '"
					+ carte.getClient().getNume() + "', '" + carte.getClient().getPrenume() + "', '"
					+ carte.getClient().getPhone() + "', " + carte.getClient().getEndDate() + ", false);");
			System.out.println("Successfully added book " + carte.getNume());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editCarte(Carte carte) {
		try {
			if (carte.getClient().getEndDate() == null) {
				interogateDB().executeUpdate("update carti set nume='" + carte.getNume() + "', autor='"
						+ carte.getAutor().getNume() + "', editura='" + carte.getEditura().getNume() + "', numeClient='"
						+ carte.getClient().getNume() + "', prenumeClient='" + carte.getClient().getPrenume()
						+ "', telefonClient='" + carte.getClient().getPhone() + "', endDate=NULL, imprumutata="
						+ carte.isImprumutata() + " where id=" + carte.getId());
				System.out.println("Successfully updated book " + carte.getNume());
			} else {
				interogateDB().executeUpdate("update carti set nume='" + carte.getNume() + "', autor='"
						+ carte.getAutor().getNume() + "', editura='" + carte.getEditura().getNume() + "', numeClient='"
						+ carte.getClient().getNume() + "', prenumeClient='" + carte.getClient().getPrenume()
						+ "', telefonClient='" + carte.getClient().getPhone() + "', endDate='"
						+ new Date(carte.getClient().getEndDate().getTime()) + "', imprumutata=" + carte.isImprumutata()
						+ " where id=" + carte.getId());
				System.out.println("Successfully updated book " + carte.getNume());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteCarte(Carte carte) {
		try {
			interogateDB().executeUpdate("delete from carti where id=" + carte.getId());
			System.out.println("Successfully deleted book " + carte.getNume());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
