/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package si.fri.sparis.taxi.presenters;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jms.JMSException;
import si.fri.sparis.taxi.facade.NarociloFacade;


/**
 *
 * @author Kristian
 */
@Named(value = "ustvariNarociloBean")
@RequestScoped
public class UstvariNarociloBean {

    @Inject
    private UserBean ub;
    
    @Inject
    List<String> suggestions;
    
    @Inject
    NarociloFacade nf;
    
    private String naslov;

    /**
     * Creates a new instance of UstvariNarocilo
     */
    public UstvariNarociloBean() {
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public List<String> completeNaslov(String query) {


        List<String> results = new ArrayList<String>();

        

        for (String suggestion : suggestions) {
            if (suggestion.startsWith(query)) {
                results.add(suggestion);
            }
        }

        return results;
    }
    
    
    public String naroci(){
        try{
            nf.naroci(this.naslov, ub.getUporabnik());
        }
        catch(JMSException ex){
            return "failure";
        }
        
        
        
    
        return "success";
    
    }
    
}
