/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package si.fri.sparis.taxi.entites;

import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author andrazhribernik
 */
public class VoznikConverter implements Converter{
    
    public Voznik findById(String id){
        EntityManager em  = Persistence.createEntityManagerFactory("si.fri.sparis_Taxi_war_1.0-SNAPSHOTPU").createEntityManager();
        List<Voznik> uporabnikResultList =
                em.createNamedQuery("Voznik.findByIdvoznik").
                setParameter("idvoznik", Integer.parseInt(id)).getResultList();
        return uporabnikResultList.get(0);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return findById(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Voznik)value).getIdvoznik().toString();
    }
    
}
