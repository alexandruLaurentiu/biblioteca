package biblioteca;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class AdministrareBean implements Serializable
{

    private DbConn      database = new DbConn();
    private Incarcare   loader   = new Incarcare();

    private List<Carte> carti    = new ArrayList<>();

    private Carte       carte;
    private String      numeCarte;
    private String      autorCarte;
    private String      edituraCarte;

    @PostConstruct
    public void init()
    {
        carti = loader.carti();
    }

    public void addCarte()
    {
        if (numeCarte.equals("") || autorCarte.equals("") || edituraCarte.equals(""))
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Error"));
        }
        else
        {
            carte = new Carte(numeCarte, new Autor(autorCarte), new Editura(edituraCarte));
            database.addCarte(carte);
            carti = loader.carti();
            numeCarte = "";
            autorCarte = "";
            edituraCarte = "";
        }
    }

    public void resetFields()
    {
        numeCarte = "";
        autorCarte = "";
        edituraCarte = "";
    }

    public void deleteCarte(Carte carte)
    {
        database.deleteCarte(carte);
        carti = loader.carti();
    }

    public void editCarte(Carte carte)
    {
        database.editCarte(carte);
        carti = loader.carti();
    }

    public List<Carte> getCarti()
    {
        return carti;
    }

    public Carte getCarte()
    {
        return carte;
    }

    public String getNumeCarte()
    {
        return numeCarte;
    }

    public String getAutorCarte()
    {
        return autorCarte;
    }

    public String getEdituraCarte()
    {
        return edituraCarte;
    }

    public void setNumeCarte(String numeCarte)
    {
        this.numeCarte = numeCarte;
    }

    public void setAutorCarte(String autorCarte)
    {
        this.autorCarte = autorCarte;
    }

    public void setEdituraCarte(String edituraCarte)
    {
        this.edituraCarte = edituraCarte;
    }

}
