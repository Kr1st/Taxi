/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package si.fri.sparis.taxi.presenters;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.primefaces.event.RowEditEvent;
import si.fri.sparis.taxi.entites.Uporabnik;
import si.fri.sparis.taxi.facade.UporabnikFacade;

/**
 *
 * @author andrazhribernik
 */
@Named(value = "uporabnikCRUD")
@SessionScoped
public class UporabnikCRUD implements Serializable{

    /**
     * Creates a new instance of UporabnikCRUD
     * 
     */
    
    @Inject
    private UporabnikFacade uporabnikLogika;
    
    private String ime;
    private String priimek;
    private String email;
    private String geslo1;
    private String vloga;
    
    private Uporabnik editedUser;
    
    private List<Uporabnik> uporabniki;
    private List<Uporabnik> uporabnikiVloga;
    private boolean firstTime = true;
    
    @PostConstruct
    public void init(){
        uporabniki = uporabnikLogika.findAll();
    }
    
    public List<Uporabnik> getUporabniki() {
        /*
        if(firstTime){
            uporabniki = uporabnikLogika.findAll();
            firstTime = false;
        }
        * */
        return uporabniki;
    }

    public Uporabnik getEditedUser() {
        return editedUser;
    }

    public void setEditedUser(Uporabnik editedUser) {
        System.out.println("Edited user "+editedUser.getIme());
        this.editedUser = editedUser;
    }
    
    public void setUporabniki(List<Uporabnik> uporabniki) {
        this.uporabniki = uporabniki;
    }
    public String getIme() {
        System.out.println(ime);
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

    public String getVloga() {
        return vloga;
    }

    public void setVloga(String vloga) {
        this.vloga = vloga;
    }
    
    public void dodaj(){
        System.out.println("zacetek metode dodaj");
        Uporabnik uporabnik = new Uporabnik();
        System.out.println("oo kreiranju");
        uporabnik.setIme(this.ime);
        System.out.println("po setIme");
        System.out.println(uporabnik.getIme());
        uporabnik.setPriimek(this.priimek);
        uporabnik.setEmail(this.email);
        uporabnik.setGeslo(this.geslo1);
        uporabnik.setVloga(Integer.parseInt(this.vloga));
        //uporabnik.setVloga(1);
        System.out.println("vloga");
        uporabnik.setZadnjaprijava(new Date());
        System.out.println("datum");
        System.out.println("Pred dodajanjem");
              
        
        if(!uporabnikLogika.register(uporabnik)){
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Napaka pri registraciji", "Uporabnik s takšnim naslovom že obstaja"));
            FacesMessage msg = new FacesMessage("Napaka","Uporabnik s tem email naslovom ze obstaja ");  
  
            FacesContext.getCurrentInstance().addMessage(null, msg);
            System.out.println("Ni dodal");
        }
        else{
            //uporabnikLogika.create(uporabnik);
            uporabniki.add(uporabnik);
            FacesMessage msg = new FacesMessage("Uporabnik dodan: ", uporabnik.toString());  
  
            FacesContext.getCurrentInstance().addMessage(null, msg);
            System.out.println("Dodal");
        }
    }
    
    public void uredi(Uporabnik u){
        System.out.println("update");
        System.out.println(u.getIme());
        setIme(u.getIme());
        setPriimek(u.getPriimek());
        setEmail(u.getEmail());
        setGeslo1(u.getGeslo());
        setVloga(Integer.toString(u.getVloga()));
        System.out.println(ime);
    }
    
    public void onEdit(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Uporabnik urejen", ((Uporabnik) event.getObject()).toString());  
        uporabnikLogika.updateUporabnik((Uporabnik) event.getObject());
        System.out.println(((Uporabnik) event.getObject()).getIme());
        System.out.println(ime);
        /*
        if(!ime.equals(""))
            ((Uporabnik) event.getObject()).setIme(ime);
        if(!priimek.equals(""))
            ((Uporabnik) event.getObject()).setPriimek(priimek);
        if(!email.equals(""))
            ((Uporabnik) event.getObject()).setEmail(email);
        if(!vloga.equals(""))
            ((Uporabnik) event.getObject()).setVloga(Integer.parseInt(vloga));
        
        uporabnikLogika.edit((Uporabnik) event.getObject());
        */
        
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
      
    public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("", ((Uporabnik) event.getObject()).toString());  
  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
    
    public void izbrisi(Uporabnik u){
        uporabniki.remove(u);
        uporabnikLogika.remove(u);
        FacesMessage msg = new FacesMessage("Uporabnik izbrisan: ", u.toString());  
  
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public List<Uporabnik> getUporabnikiVloga(){
        uporabnikiVloga = uporabnikLogika.findByRole(2);
        return uporabnikiVloga;
    }
    
    public void setUporabnikiVloga(List<Uporabnik> u){
        uporabnikiVloga = u;
    }
    
    public void onCellEdit(RowEditEvent event){
        System.out.println("onCellEdit");
        Uporabnik utemp = (Uporabnik) event.getObject();
        System.out.println(utemp.getIduporabnik());
        setIme(utemp.getIme());
    
    }
}
