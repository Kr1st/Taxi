/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package si.fri.sparis.taxi.presenters;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.event.RowEditEvent;
import si.fri.sparis.taxi.entites.Uporabnik;
import si.fri.sparis.taxi.entites.Voznik;
import si.fri.sparis.taxi.facade.UporabnikFacade;
import si.fri.sparis.taxi.facade.VoznikFacade;

/**
 *
 * @author andrazhribernik
 */
@Named(value = "voznikiCRUD")
@SessionScoped
public class VoznikiCRUD implements Serializable{

    /**
     * Creates a new instance of VoznikiCRUD
     */
    @Inject
    private VoznikFacade voznikiLogika;
    @Inject UporabnikFacade uporabnikLogika;
    private List<Voznik> vozniki;
    private List<Uporabnik> uporabniki;
    private String avtomobil;
    private Uporabnik novVoznikUporabnik;
    
    
    @PostConstruct
    public void init(){
        uporabniki = uporabnikLogika.findByRole(2);
        vozniki = voznikiLogika.findAll();
    }

    public String getAvtomobil() {
        return avtomobil;
    }

    public void setAvtomobil(String avtomobil) {
        this.avtomobil = avtomobil;
    }

    public Uporabnik getNovVoznikUporabnik() {
        return novVoznikUporabnik;
    }

    public void setNovVoznikUporabnik(Uporabnik novVoznikUporabnik) {
        this.novVoznikUporabnik = novVoznikUporabnik;
    }
    
    public List<Uporabnik> getUporabniki() {
        return uporabniki;
    }

    public void setUporabniki(List<Uporabnik> uporabniki) {
        this.uporabniki = uporabniki;
    }

    
    public List<Voznik> getVozniki() {
        return vozniki;
    }

    public void setVozniki(List<Voznik> vozniki) {
        this.vozniki = vozniki;
    }
    
    public void onEdit(RowEditEvent event) {
        System.out.println(((Voznik)event.getObject()).getIdvoznik());
        System.out.println(((Voznik)event.getObject()).getVozilo());
        
        
        System.out.println("onEdit");
    }  
      
    public void onCancel(RowEditEvent event) {  
        System.out.println("onCancel");
    }
    
    public void izbrisi(Voznik v){
        try{
            voznikiLogika.remove(v);
            vozniki.remove(v);
            FacesMessage msg = new FacesMessage("Brisanje voznika","Uspešno ste izbrisali voznika");  
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        catch(Exception e){
            FacesMessage msg = new FacesMessage("Brisanje voznika","Brisanje voznika ni uspelo");  
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public void dodaj(){
        try{
            Voznik newVoznik = new Voznik();
            newVoznik.setIduporabnik(novVoznikUporabnik);
            newVoznik.setVozilo(avtomobil); 
            voznikiLogika.dodajVoznika(newVoznik);
            vozniki.add(newVoznik);
            System.out.println(newVoznik.getIdvoznik());
            FacesMessage msg = new FacesMessage("Dodajanje voznika","Uspešno ste dodali novega voznika");  
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        catch(Exception e){
            FacesMessage msg = new FacesMessage("Dodajanje voznika","Dodajanje voznika ni uspelo");  
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}
