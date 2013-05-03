/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package si.fri.sparis.taxi.presenters;

import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import si.fri.sparis.taxi.entites.Uporabnik;
import si.fri.sparis.taxi.facade.UporabnikFacade;

/**
 *
 * @author andrazhribernik
 */
@Named(value = "uporabnikCRUD")
@RequestScoped
public class UporabnikCRUD implements Serializable{

    /**
     * Creates a new instance of UporabnikCRUD
     */
    private static final long serialVersionUID = 1L;
    public List<Uporabnik> uporabniki;
    @Inject
    private UporabnikFacade uporabnikLogika;

    public List<Uporabnik> getUporabniki() {
        uporabniki = uporabnikLogika.findAll();
        System.out.println("Dobil sem uporabnike");
        return uporabniki;
    }

    public void setUporabniki(List<Uporabnik> uporabniki) {
        this.uporabniki = uporabniki;
    }
    
}
