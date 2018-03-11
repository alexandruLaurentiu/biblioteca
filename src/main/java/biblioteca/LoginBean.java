package biblioteca;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@ViewScoped
public class LoginBean implements Serializable
{

    private String              username;
    private String              password;

    private static final String homePage  = "/biblioteca/biblioteca.xhtml";
    private static final String loginPage = "/biblioteca/login.xhtml";

    public String loginStep()
    {
        if (username.equals("bibliotecar") && password.equals("parola"))
        {
            HttpSession session = Util.getSession();
            session.setAttribute("username", username);

            return "biblioteca";
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage("loginForm:username",
                            new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid Login!", "Nume sau parolă greșită!"));

            username = "";
            password = "";
            // invalidate session, and redirect to other pages

            // message = "Invalid Login. Please Try Again!";
            return "login";
        }
    }

    public void login()
    {
        if (loginStep().equals("biblioteca"))
        {
            try
            {
                FacesContext.getCurrentInstance().getExternalContext().redirect(homePage);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public String logoutStep()
    {
        HttpSession session = Util.getSession();
        session.invalidate();
        return "login";
    }

    public void logout()
    {
        if (logoutStep().equals("login"))
        {
            try
            {
                FacesContext.getCurrentInstance().getExternalContext().redirect(loginPage);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

}
