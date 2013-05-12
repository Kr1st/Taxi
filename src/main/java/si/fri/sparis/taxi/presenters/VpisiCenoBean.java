/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package si.fri.sparis.taxi.presenters;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import si.fri.sparis.taxi.entites.Narocilo;
import si.fri.sparis.taxi.entites.Voznik;
import si.fri.sparis.taxi.facade.NarociloFacade;
import si.fri.sparis.taxi.facade.VoznikFacade;

/**
 *
 * @author Kristian
 */
@Named(value = "vpisiCenoBean")
@RequestScoped
public class VpisiCenoBean {

    @Inject
    private UserBean ub;
    
    @Inject
    NarociloFacade nf;
    
    @Inject
    VoznikFacade vf;
    
    
    private List<Narocilo> narocila;
    private Narocilo izbranoNarocilo;
    
    /**
     * Creates a new instance of VpisiCenoBean
     */
    public VpisiCenoBean() {
    }
    
    @PostConstruct
    public void initialize(){
        Voznik voznik = vf.findByIdUporabnik(ub.getUporabnik());
        this.narocila = nf.vpisiCenoNarocilo(voznik);
        
    }

    public List<Narocilo> getNarocila() {
        return narocila;
    }


    public Narocilo getIzbranoNarocilo() {
        return izbranoNarocilo;
    }

    public void setIzbranoNarocilo(Narocilo izbranoNarocilo) {
        this.izbranoNarocilo = izbranoNarocilo;
    }
    
    public String vpisiCeno(){
        System.out.println(this.izbranoNarocilo.getDatum());
        
        return "edit";
        
    
    }
    
    
    
}
