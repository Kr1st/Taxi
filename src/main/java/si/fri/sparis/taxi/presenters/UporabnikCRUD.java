/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package si.fri.sparis.taxi.presenters;

import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import si.fri.sparis.taxi.entites.Uporabnik;
import si.fri.sparis.taxi.facade.UporabnikFacade;

/**
 *
 * @author andrazhribernik
 */
@Named(value = "uporabnikCRUD")
@RequestScoped
public class UporabnikCRUD implements Serializable{

    /**
     * Creates a new instance of UporabnikCRUD
     * 
     */
    
    private String ime;
    private String priimek;
    private String email;
    private String geslo1;
    private int vloga;
    
    private static final long serialVersionUID = 1L;
    public List<Uporabnik> uporabniki;
    @Inject
    private UporabnikFacade uporabnikLogika;

    public List<Uporabnik> getUporabniki() {
        uporabniki = uporabnikLogika.findAll();
        return uporabniki;
    }

    public void setUporabniki(List<Uporabnik> uporabniki) {
        this.uporabniki = uporabniki;
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
    public void dodaj(){
        uporabnikLogika.register(new Uporabnik(null,vloga,ime,priimek,email,));
    }
}
