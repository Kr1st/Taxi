/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package si.fri.sparis.taxi.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import si.fri.sparis.taxi.entites.Uporabnik;

/**
 *
 * @author Kristian
 */
@Stateless
public class UporabnikFacade extends AbstractFacade<Uporabnik> {

    @PersistenceContext(unitName = "si.fri.sparis_Taxi_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UporabnikFacade() {
        super(Uporabnik.class);
    }

    public boolean register(Uporabnik uporabnik){
         List<Uporabnik> uporabnikResultList =
                em.createNamedQuery("Uporabnik.findByEmail").
                setParameter("email", uporabnik.getEmail()).getResultList();
         
         
         
         if(!uporabnikResultList.isEmpty())
             return false;
         
         
         em.persist(uporabnik);
         
         return true;
         
    }
    
    
    public Uporabnik login(String email, String geslo) {
        List<Uporabnik> uporabnikResultList =
                em.createNamedQuery("Uporabnik.findByEmail").
                setParameter("email", email).getResultList();

        Uporabnik uporabnik;

        if (!uporabnikResultList.isEmpty()) {
            uporabnik = uporabnikResultList.get(0);
            if (uporabnik.getGeslo().equals(geslo)) {
                return uporabnik;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
    
    public List<Uporabnik> findByRole(int vloga) {
        List<Uporabnik> uporabnikResultList =
                em.createNamedQuery("Uporabnik.findByVloga").
                setParameter("vloga", vloga).getResultList();
        return uporabnikResultList;
    }
    
    public List<Uporabnik> findById(String id){
        List<Uporabnik> uporabnikResultList =
                em.createNamedQuery("Uporabnik.findByIduporabnik").
                setParameter("iduporabnik", Integer.parseInt(id)).getResultList();
        return uporabnikResultList;
    }
    
    public void updateUporabnik(Uporabnik oldU){
        em.merge(oldU);
    }
    
    
    
}
