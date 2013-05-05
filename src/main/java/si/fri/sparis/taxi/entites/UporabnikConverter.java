/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package si.fri.sparis.taxi.entites;

import java.util.List;
import javax.ejb.Stateless;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import si.fri.sparis.taxi.facade.UporabnikFacade;
import si.fri.sparis.taxi.presenters.UporabnikCRUD;

/**
 *
 * @author andrazhribernik
 */

public class UporabnikConverter implements Converter{

    
    public Uporabnik findById(String id){
        EntityManager em  = Persistence.createEntityManagerFactory("si.fri.sparis_Taxi_war_1.0-SNAPSHOTPU").createEntityManager();
        List<Uporabnik> uporabnikResultList =
                em.createNamedQuery("Uporabnik.findByIduporabnik").
                setParameter("iduporabnik", Integer.parseInt(id)).getResultList();
        return uporabnikResultList.get(0);
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Uporabnik temp = findById(value);
        return temp;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        return value.toString();

    }
    
}
