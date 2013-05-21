/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package si.fri.sparis.taxi.presenters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.primefaces.event.RowEditEvent;
import si.fri.sparis.taxi.entites.Uporabnik;
import si.fri.sparis.taxi.entites.Voznik;
import si.fri.sparis.taxi.facade.UporabnikFacade;
import si.fri.sparis.taxi.facade.VoznikFacade;

/**
 *
 * @author andrazhribernik
 */
@Named(value = "voznikiCRUD")
@RequestScoped
public class VoznikiCRUD implements Serializable{

    /**
     * Creates a new instance of VoznikiCRUD
     */
    @Inject
    private VoznikFacade voznikiLogika;
    @Inject 
    private UporabnikFacade uporabnikLogika;
    private List<Voznik> vozniki;
    private List<Uporabnik> uporabniki;
    private String avtomobil;
    private Uporabnik novVoznikUporabnik;
    
    
    @PostConstruct
    public void init(){
        uporabniki = uporabnikLogika.findByRole(2);
        //vozniki = voznikiLogika.findAll();
        vozniki = getAllVoznikiList();
    }

    public String getAvtomobil() {
        return avtomobil;
    }

    public void setAvtomobil(String avtomobil) {
        this.avtomobil = avtomobil;
    }

    public Uporabnik getNovVoznikUporabnik() {
        return novVoznikUporabnik;
    }

    public void setNovVoznikUporabnik(Uporabnik novVoznikUporabnik) {
        this.novVoznikUporabnik = novVoznikUporabnik;
    }
    
    public List<Uporabnik> getUporabniki() {
        return uporabniki;
    }

    public void setUporabniki(List<Uporabnik> uporabniki) {
        this.uporabniki = uporabniki;
    }

    
    public List<Voznik> getVozniki() {
        return vozniki;
    }

    public void setVozniki(List<Voznik> vozniki) {
        this.vozniki = vozniki;
    }
    
    public void onEdit(RowEditEvent event) {
        System.out.println(((Voznik)event.getObject()).getIdvoznik());
        System.out.println(((Voznik)event.getObject()).getIduporabnik().getIme());
        System.out.println(((Voznik)event.getObject()).getVozilo());
        editVoznikREST((Voznik)event.getObject());
        
        System.out.println("onEdit");
    }  
      
    public void onCancel(RowEditEvent event) {  
        System.out.println("onCancel");
    }
    
    public void izbrisi(Voznik v){
        try{
            //voznikiLogika.remove(v);
            izbrisiVoznikaRest(v);
            vozniki.remove(v);
            FacesMessage msg = new FacesMessage("Brisanje voznika","Uspešno ste izbrisali voznika");  
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        catch(Exception e){
            FacesMessage msg = new FacesMessage("Brisanje voznika","Brisanje voznika ni uspelo");  
            FacesContext.getCurrentInstance().addMessage(null, msg);
            e.printStackTrace();
        }
    }
    
    public void dodaj(){
        try{
            Voznik newVoznik = new Voznik();
            newVoznik.setIduporabnik(novVoznikUporabnik);
            newVoznik.setVozilo(avtomobil); 
            //voznikiLogika.dodajVoznika(newVoznik);
            //vozniki.add(newVoznik);
            dodajVoznikaRest(newVoznik);
            System.out.println(newVoznik.getIdvoznik());
            FacesMessage msg = new FacesMessage("Dodajanje voznika","Uspešno ste dodali novega voznika");  
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        catch(Exception e){
            FacesMessage msg = new FacesMessage("Dodajanje voznika","Dodajanje voznika ni uspelo");  
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public void dodajVoznikaRest(Voznik v){
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
        
        Client client = Client.create();
 
        WebResource webResource = client
           .resource("http://localhost:8080/Taxi/webresources/voznik");

        //Uporabnik u = new Uporabnik(15, 1, "janez", "Novak", "janez.novak@mail.com", "geslo", new Date());
        String input = gson.toJson(v);
        //String input = "{\"vloga\":1,\"ime\":\"Janez\", \"priimek\":\"Novak\", \"email\":\"rest.test@mail.si\", \"geslo\":\"gslo\",\"zadnjaprijava\" : \"2013-05-16T14:13:18.547+02:00\"}";
        System.out.println(input);
        ClientResponse response = webResource.type("application/json")
           .post(ClientResponse.class, input);
        
        if (response.getStatus() != 204) {
                throw new RuntimeException("Failed : HTTP error code : "
                     + response.getStatus());
        }
        client.destroy();
    }
    
    public List<Voznik> getAllVoznikiList(){
        Client client = Client.create();
 
        WebResource webResource = client
           .resource("http://localhost:8080/Taxi/webresources/voznik");
        
        ClientResponse response = webResource.accept("application/json")
                   .get(ClientResponse.class);
        
        if (response.getStatus() != 200) {
           throw new RuntimeException("Failed : HTTP error code : "
                 + response.getStatus());
        }

        String output = response.getEntity(String.class);
        System.out.println(output);
        GsonBuilder builder = new GsonBuilder();  
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() { 
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException 
            { 
                return new Date(json.getAsJsonPrimitive().getAsLong()); 
            } 
        }); 
        Gson gson = builder.create();
        JSONArray json;
        List<Voznik> voz = null;
        try {
            json = new JSONArray(output);
            System.out.println(json.toString());
            voz = gson.fromJson(output, new TypeToken<List<Voznik>>(){}.getType());
            System.out.println(voz.get(0));
        } catch (Exception ex) {
            Logger.getLogger(VoznikiCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        client.destroy();
        return voz;
    
    }
    
    public void editVoznikREST(Voznik v){
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
        
        Client client = Client.create();
 
        WebResource webResource = client
           .resource("http://localhost:8080/Taxi/webresources/voznik");

        //Uporabnik u = new Uporabnik(15, 1, "janez", "Novak", "janez.novak@mail.com", "geslo", new Date());
        String input = gson.toJson(v);
        //String input = "{\"vloga\":1,\"ime\":\"Janez\", \"priimek\":\"Novak\", \"email\":\"rest.test@mail.si\", \"geslo\":\"gslo\",\"zadnjaprijava\" : \"2013-05-16T14:13:18.547+02:00\"}";
        System.out.println(input);
        ClientResponse response = webResource.type("application/json")
           .put(ClientResponse.class, input);
        
        if (response.getStatus() != 204) {
                throw new RuntimeException("Failed : HTTP error code : "
                     + response.getStatus());
        }
        client.destroy();
    }
    
    public void izbrisiVoznikaRest(Voznik v){
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
        
        Client client = Client.create();
 
        WebResource webResource = client
           .resource("http://localhost:8080/Taxi/webresources/voznik/"+v.getIdvoznik());

        //Uporabnik u = new Uporabnik(15, 1, "janez", "Novak", "janez.novak@mail.com", "geslo", new Date());
        String input = gson.toJson(v);
        //String input = "{\"vloga\":1,\"ime\":\"Janez\", \"priimek\":\"Novak\", \"email\":\"rest.test@mail.si\", \"geslo\":\"gslo\",\"zadnjaprijava\" : \"2013-05-16T14:13:18.547+02:00\"}";
        System.out.println(input);
        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).delete(ClientResponse.class);
        client.destroy();
    }
}
