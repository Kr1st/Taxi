/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package si.fri.sparis.taxi.facade;

import java.util.Date;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import si.fri.sparis.taxi.entites.Narocilo;
import si.fri.sparis.taxi.entites.Uporabnik;
import si.fri.sparis.taxi.resources.Logging;


/**
 *
 * @author Kristian
 */
@Stateless
public class NarociloFacade extends AbstractFacade<Narocilo> {
    @PersistenceContext(unitName = "si.fri.sparis_Taxi_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Inject
    private JMSSenderFacade senderFacade;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NarociloFacade() {
        super(Narocilo.class);
    }
    
    @Logging
    public void naroci(String naslov, Uporabnik uporabnik) throws JMSException{
        Narocilo narocilo = new Narocilo();
        narocilo.setLokacija(naslov);
        narocilo.setIduporabnik(uporabnik);
        narocilo.setDatum(new Date());
        narocilo.setStatus(1);
        
        
        ObjectMessage msg = senderFacade.getSession().createObjectMessage();
        
        //MapMessage msg = senderFacade.getSession().createMapMessage();
        //msg.setObject("narocilo", narocilo);
        msg.setObject(narocilo);
        
        
        senderFacade.send(msg);
        
    
    }
    
}
