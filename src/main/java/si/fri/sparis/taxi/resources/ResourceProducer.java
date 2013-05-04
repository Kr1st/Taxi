/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package si.fri.sparis.taxi.resources;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;

/**
 *
 * @author Kristian
 */
public class ResourceProducer {

    @Produces
    public List<String> getSuggestions(@New ArrayList<String> suggestions) {
        suggestions.add("Bavarski dvor");
        suggestions.add("Hotel Lev");
        suggestions.add("Kompanjeros");
        suggestions.add("Merčnikova ulica 1a");
        suggestions.add("Tivoli");
        suggestions.add("Center");
        
        return suggestions;
    }
}
