/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package si.fri.sparis.taxi.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import si.fri.sparis.taxi.entites.Uporabnik;
import si.fri.sparis.taxi.entites.Voznik;

/**
 *
 * @author Kristian
 */
@Stateless
public class VoznikFacade extends AbstractFacade<Voznik> {
    @PersistenceContext(unitName = "si.fri.sparis_Taxi_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VoznikFacade() {
        super(Voznik.class);
    }
    

    public Voznik findByIdUporabnik(Uporabnik uporabnik){
       List<Voznik> vozniki =  em.createNamedQuery("Voznik.findVoznikByIdUporabnik").setParameter("iduporabnik", uporabnik).getResultList();
    
       if(vozniki.size() > 0)
           return vozniki.get(0);
       else
           return null;
    
    }
    public void dodajVoznika(Voznik v){
        em.persist(v);

    }
    
}
