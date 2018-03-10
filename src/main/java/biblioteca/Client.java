package biblioteca;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 * @author Alexandru
 *
 */

public class Client {

	private int id;
	private String nume;
	private String prenume;
	private String phone;
	private Date startDate = new Date();
	private Date endDate;

	public long getTimpRamas() {
		return Days.daysBetween(new DateTime(startDate).withTimeAtStartOfDay(),
				new DateTime(endDate).withTimeAtStartOfDay()).getDays();
	}

	public Date minDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime();
	}

	public Date borrowPeriod() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.add(Calendar.DATE, 15);
		return calendar.getTime();
	}

	public void validationClient() {
		if (nume.equals("")) {
			FacesContext.getCurrentInstance().addMessage("numeClient", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Numele este obligatoriu.", "Numele este obligatoriu."));
		}
		if (prenume.equals("")) {
			FacesContext.getCurrentInstance().addMessage("prenumeClient", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Prenumele este obligatoriu.", "Prenumele este obligatoriu."));
		}
		if (endDate.equals("")) {
			FacesContext.getCurrentInstance().addMessage("numeClient", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Trebuie specificata perioada de imprumut.", "Trebuie specificata perioada de imprumut."));
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getPrenume() {
		return prenume;
	}

	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void resetClient() {
		nume = "";
		prenume = "";
		phone = "";
		endDate = null;
	}

}
