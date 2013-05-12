/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package si.fri.sparis.taxi.presenters;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jms.JMSException;
import si.fri.sparis.taxi.entites.Narocilo;
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
    private Narocilo narocilo;

    public SprejmiNarociloBean() {
    }

    @PostConstruct
    public void initialize() throws JMSException {
        this.narocilo = nf.dobiNarocilo();
    }

    public Narocilo getNarocilo() {
        return narocilo;
    }

    public void setNarocilo(Narocilo narocilo) {
        this.narocilo = narocilo;
    }

    public String zavrni() throws JMSException {
        if (this.narocilo != null) {
            nf.narociPonovno(this.narocilo);

            return "zavrni";
        } else {
            return "napaka";
        }

    }

    public String sprejmi() {


        if (this.narocilo != null) {
            this.narocilo.setIdvoznik(vf.findByIdUporabnik(this.ub.getUporabnik()));
            this.narocilo.setStatus(2);

            nf.create(this.narocilo);
            return "sprejmi";
        } else {
            return "napaka";
        }


    }
}
