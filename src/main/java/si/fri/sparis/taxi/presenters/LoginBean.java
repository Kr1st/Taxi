/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package si.fri.sparis.taxi.presenters;

import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import si.fri.sparis.taxi.entites.Uporabnik;
import si.fri.sparis.taxi.facade.UporabnikFacade;

/**
 *
 * @author Kristian
 */
@Named(value = "loginBean")
@RequestScoped
public class LoginBean {

    private String email;
    private String geslo;
    
    @Inject
    private UserBean user;
    
    @Inject 
    private UporabnikFacade uf;
    

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGeslo() {
        return geslo;
    }

    public void setGeslo(String geslo) {
        this.geslo = geslo;
    }

    public String login() {
        
        Uporabnik uporabnik = uf.login(this.email, this.geslo);
        if (uporabnik != null) {
            user.setUporabnik(uporabnik);
            user.setIsLogged(true);
            System.out.println("prijava je uspela!!!");
            return "success";
        } else {
            System.out.println("prijava ni uspela!!!");
            return "failure";
        }
    }
}
