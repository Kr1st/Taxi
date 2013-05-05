/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package si.fri.sparis.taxi.presenters;

import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
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
@RequestScoped
public class VoznikiCRUD implements Serializable{

    /**
     * Creates a new instance of VoznikiCRUD
     */
    @Inject
    private VoznikFacade voznikiLogika;
    @Inject UporabnikFacade uporabnikLogika;
    private List<Voznik> vozniki;
    private List<Uporabnik> uporabniki;

    public List<Uporabnik> getUporabniki() {
        uporabniki = uporabnikLogika.findByRole(2);
        return uporabniki;
    }

    public void setUporabniki(List<Uporabnik> uporabniki) {
        this.uporabniki = uporabniki;
    }

    
    public List<Voznik> getVozniki() {
        vozniki = voznikiLogika.findAll();
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
        System.out.println("Izbrisi");
    }
}
