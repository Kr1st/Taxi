/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package si.fri.sparis.taxi.advertising;

import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import si.fri.sparis.taxi.entites.Uporabnik;
import si.fri.sparis.taxi.facade.UporabnikFacade;

@Singleton
@Startup
public class AssinhronoPosiljanjeMaila {
    private final String username = "sparis.taxi@gmail.com";
    private final String password = "sparisgeslo";
    private Session seja;
    
    @Inject
    UporabnikFacade uporabnikLogika;
    
    
    @PostConstruct
    public void init(){
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        seja = Session.getInstance(props,
        new javax.mail.Authenticator() {
              protected PasswordAuthentication getPasswordAuthentication() {
                      return new PasswordAuthentication(username, password);
              }
        });
    }
    
    @Asynchronous
    public void posljiEmail(Uporabnik u){
        try {
 
            Message message = new MimeMessage(seja);
            message.setFrom(new InternetAddress("from-email@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(u.getEmail()));
            message.setSubject("SPARIS ovescevalec");
            message.setText("Pozdravljeni,"
                    + "\n\n Ze dolgo niste obiskali nase strani!");

            Transport.send(message);

            System.out.println("Done sending email");

        } catch (Exception e) {
            System.out.println("Napaka pri posiljanju maila "+u.getEmail());
            System.out.println(e.getMessage());
        }	
    }
    
    
    @Schedule(hour = "*",minute = "*",second = "*/30")
    public void obvescanjeUporabnikov(){
        List<Uporabnik> uporabniki = uporabnikLogika.findByRole(1);
        for (int i=0; i<uporabniki.size();i++){
            if(uporabniki.get(i).getZadnjaprijava().before(new Date(new Date().getTime() - 5 * 60 * 1000))){
                System.out.println("Uporabnik se ze dolgo ni prijavil "+uporabniki.get(i).getEmail());
                //posljiEmail(uporabniki.get(i));
            }
            else{
                System.out.println("Uporabnik je bil prijavlen, ne dolgo nazaj "+uporabniki.get(i).getEmail());
            }
            
        }
    }
    
}
