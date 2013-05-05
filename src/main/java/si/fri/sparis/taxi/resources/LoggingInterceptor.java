/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package si.fri.sparis.taxi.resources;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import si.fri.sparis.taxi.entites.Uporabnik;

/**
 *
 * @author Kristian
 */
@Interceptor
@Logging
public class LoggingInterceptor {

    @AroundInvoke
    public Object log(InvocationContext context)
            throws
            Exception {
        
        Object[] parameters  = context.getParameters();
        String naslov =(String) parameters[0];
        Uporabnik uporabnik  = (Uporabnik) parameters[1];
        String imePriimek = uporabnik.getIme() + " " + uporabnik.getPriimek();
        
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Naro\u010dilo oddano, datum: {0}, naslov: {1}, uporabnik:{2}", new Object[]{(new Date()).toString(), naslov, imePriimek});
        return context.proceed();
    }
}
