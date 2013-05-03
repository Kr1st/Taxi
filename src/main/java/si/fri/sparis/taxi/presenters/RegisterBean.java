/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package si.fri.sparis.taxi.presenters;

import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import si.fri.sparis.taxi.entites.Uporabnik;
import si.fri.sparis.taxi.facade.UporabnikFacade;

/**
 *
 * @author Kristian
 */
@Named(value = "registerBean")
@RequestScoped
public class RegisterBean {

    @Inject
    private UporabnikFacade uf;
    
    
    private String ime;
    private String priimek;
    private String email;
    private String geslo1;
    private String geslo2;
    
    public RegisterBean() {
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGeslo1() {
        return geslo1;
    }

    public void setGeslo1(String geslo1) {
        this.geslo1 = geslo1;
    }

    public String getGeslo2() {
        return geslo2;
    }

    public void setGeslo2(String geslo2) {
        this.geslo2 = geslo2;
    }

    
    public String register(){
        Uporabnik uporabnik = new Uporabnik();
        
        uporabnik.setIme(this.ime);
        uporabnik.setPriimek(this.priimek);
        uporabnik.setEmail(this.email);
        uporabnik.setGeslo(this.geslo1);
        uporabnik.setVloga(1);
        uporabnik.setZadnjaprijava(new Date());
        
        
        //uf.create(uporabnik);
        //return "success";
        
        if(uf.register(uporabnik)){
            return "success";
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Napaka pri registraciji", "Uporabnik s takšnim naslovom že obstaja"));
            return "failure";
            
        }
    }
}
