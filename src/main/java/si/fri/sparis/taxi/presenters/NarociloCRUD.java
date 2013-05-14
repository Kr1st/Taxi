/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package si.fri.sparis.taxi.presenters;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;
import si.fri.sparis.taxi.entites.Narocilo;
import si.fri.sparis.taxi.entites.Uporabnik;
import si.fri.sparis.taxi.entites.Voznik;
import si.fri.sparis.taxi.facade.NarociloFacade;
import si.fri.sparis.taxi.facade.UporabnikFacade;
import si.fri.sparis.taxi.facade.VoznikFacade;

/**
 *
 * @author andrazhribernik
 */
@Named
@RequestScoped
public class NarociloCRUD implements Serializable{
    @Inject
    private NarociloFacade narociloLogika;
    @Inject 
    private UporabnikFacade uporabnikLogika;
    @Inject
    private VoznikFacade voznikLogika;
    private List <Narocilo> narocila;
    private List <Voznik> vozniki;
    private List <Uporabnik> uporabniki;
    private Uporabnik izbranUporabnik;
    private Voznik izbranVoznik;
    private String lokacija;
    private int status;
    private double cena;
    
    @PostConstruct
    public void init(){
        
        narocila = narociloLogika.findAll();
        List <Uporabnik> voznikiTemp = uporabnikLogika.findByRole(2);
        vozniki = new ArrayList<Voznik>();
        for (int i=0; i<voznikiTemp.size();i++){
            vozniki.add(voznikLogika.findByIdUporabnik(voznikiTemp.get(i)));
        } 
        uporabniki = uporabnikLogika.findByRole(1);
        
    }
    
    public List<Narocilo> getNarocila() {
        return narocila;
    }

    public void setNarocila(List<Narocilo> narocila) {
        this.narocila = narocila;
    }

    public List<Voznik> getVozniki() {
        return vozniki;
    }

    public void setVozniki(List<Voznik> vozniki) {
        this.vozniki = vozniki;
    }

    public List<Uporabnik> getUporabniki() {
        return uporabniki;
    }

    public void setUporabniki(List<Uporabnik> uporabniki) {
        this.uporabniki = uporabniki;
    }

    public Uporabnik getIzbranUporabnik() {
        return izbranUporabnik;
    }

    public void setIzbranUporabnik(Uporabnik izbranUporabnik) {
        this.izbranUporabnik = izbranUporabnik;
    }

    public Voznik getIzbranVoznik() {
        return izbranVoznik;
    }

    public void setIzbranVoznik(Voznik izbranVoznik) {
        this.izbranVoznik = izbranVoznik;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }
    
    public void dodaj(){
        try{
            System.out.println("dodaj narocilo");
            Narocilo newNarocilo = new Narocilo();
            newNarocilo.setIduporabnik(izbranUporabnik);
            newNarocilo.setIdvoznik(izbranVoznik);
            newNarocilo.setLokacija(lokacija);
            newNarocilo.setStatus(status);
            newNarocilo.setCena(cena);
            System.out.println(cena+" "+status+" "+lokacija+" "+izbranVoznik.getIduporabnik().getEmail());

            narociloLogika.dodaj(newNarocilo);
            FacesMessage msg = new FacesMessage("Dodajanje narocila","Uspešno ste dodali novo narocilo");  
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        catch(Exception e){
            e.printStackTrace();
            FacesMessage msg = new FacesMessage("Dodajanje narocila","Dodajanje narocila ni uspelo");  
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public void onEdit(RowEditEvent event) {      
        
        System.out.println("onEdit");
        narociloLogika.edit((Narocilo)event.getObject());
    }  
      
    public void onCancel(RowEditEvent event) {  
        System.out.println("onCancel");
    }
    
    public void delete(Narocilo n){
        narociloLogika.remove(n);
        narocila.remove(n);
    }
    
}
