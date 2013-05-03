/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package si.fri.sparis.taxi.presenters;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Kristian
 */
@Named(value = "registerBean")
@RequestScoped
public class RegisterBean {

    private String ime;
    private String priimek;
    private String email;
    private String geslo1;
    private String geslo2;
    
    public RegisterBean() {
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGeslo1() {
        return geslo1;
    }

    public void setGeslo1(String geslo1) {
        this.geslo1 = geslo1;
    }

    public String getGeslo2() {
        return geslo2;
    }

    public void setGeslo2(String geslo2) {
        this.geslo2 = geslo2;
    }

    

    public String register(){
        return "success";
    }
}
