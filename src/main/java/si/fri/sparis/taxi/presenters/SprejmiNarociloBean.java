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
import javax.jms.JMSException;
import si.fri.sparis.taxi.entites.Narocilo;
import si.fri.sparis.taxi.entites.Voznik;
import si.fri.sparis.taxi.facade.NarociloFacade;
import si.fri.sparis.taxi.facade.VoznikFacade;

/**
 *
 * @author Kristian
 */
@Named(value = "sprejmiNarociloBean")
@RequestScoped
public class SprejmiNarociloBean {

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
    public SprejmiNarociloBean() {
    }
    
    @PostConstruct
    public void initialize(){
        this.narocila = nf.dobiVsaNarocila();
        
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

    public String sprejmi() {

        System.out.println(this.izbranoNarocilo);
        if(this.izbranoNarocilo != null){
            this.izbranoNarocilo.setStatus(2);
            Voznik voznik = vf.findByIdUporabnik(ub.getUporabnik());
            this.izbranoNarocilo.setIdvoznik(voznik);
            nf.edit(this.izbranoNarocilo);
            return "sprejmi";
        }
        else
            return "napaka";


    }
}
