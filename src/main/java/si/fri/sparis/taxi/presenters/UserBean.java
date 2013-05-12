package si.fri.sparis.taxi.presenters;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import si.fri.sparis.taxi.entites.Uporabnik;

/**
 *
 * @author Kristian
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {

    private Uporabnik uporabnik;
    private boolean isLogged;

    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
    }

    public Uporabnik getUporabnik() {
        return uporabnik;
    }

    public void setUporabnik(Uporabnik uporabnik) {
        this.uporabnik = uporabnik;
    }

    public boolean isIsLogged() {
        return isLogged;
    }

    public void setIsLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "naIndex";
    }
}
