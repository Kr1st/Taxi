/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package si.fri.sparis.taxi.entites;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Kristian
 */
@Entity
@Table(name = "UPORABNIK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Uporabnik.findAll", query = "SELECT u FROM Uporabnik u"),
    @NamedQuery(name = "Uporabnik.findByIduporabnik", query = "SELECT u FROM Uporabnik u WHERE u.iduporabnik = :iduporabnik"),
    @NamedQuery(name = "Uporabnik.findByVloga", query = "SELECT u FROM Uporabnik u WHERE u.vloga = :vloga"),
    @NamedQuery(name = "Uporabnik.findByIme", query = "SELECT u FROM Uporabnik u WHERE u.ime = :ime"),
    @NamedQuery(name = "Uporabnik.findByPriimek", query = "SELECT u FROM Uporabnik u WHERE u.priimek = :priimek"),
    @NamedQuery(name = "Uporabnik.findByEmail", query = "SELECT u FROM Uporabnik u WHERE u.email = :email"),
    @NamedQuery(name = "Uporabnik.findByGeslo", query = "SELECT u FROM Uporabnik u WHERE u.geslo = :geslo"),
    @NamedQuery(name = "Uporabnik.findByZadnjaprijava", query = "SELECT u FROM Uporabnik u WHERE u.zadnjaprijava = :zadnjaprijava")})
public class Uporabnik implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDUPORABNIK")
    private Integer iduporabnik;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VLOGA")
    private int vloga;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "IME")
    private String ime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "PRIIMEK")
    private String priimek;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "GESLO")
    private String geslo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ZADNJAPRIJAVA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date zadnjaprijava;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iduporabnik")
    private List<Voznik> voznikList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iduporabnik")
    private List<Narocilo> narociloList;

    public Uporabnik() {
    }

    public Uporabnik(Integer iduporabnik) {
        this.iduporabnik = iduporabnik;
    }

    public Uporabnik(Integer iduporabnik, int vloga, String ime, String priimek, String email, String geslo, Date zadnjaprijava) {
        this.iduporabnik = iduporabnik;
        this.vloga = vloga;
        this.ime = ime;
        this.priimek = priimek;
        this.email = email;
        this.geslo = geslo;
        this.zadnjaprijava = zadnjaprijava;
    }

    public Integer getIduporabnik() {
        return iduporabnik;
    }

    public void setIduporabnik(Integer iduporabnik) {
        this.iduporabnik = iduporabnik;
    }

    public int getVloga() {
        return vloga;
    }

    public void setVloga(int vloga) {
        this.vloga = vloga;
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

    public String getGeslo() {
        return geslo;
    }

    public void setGeslo(String geslo) {
        this.geslo = geslo;
    }

    public Date getZadnjaprijava() {
        return zadnjaprijava;
    }

    public void setZadnjaprijava(Date zadnjaprijava) {
        this.zadnjaprijava = zadnjaprijava;
    }

    @XmlTransient
    @JsonIgnore
    public List<Voznik> getVoznikList() {
        return voznikList;
    }

    public void setVoznikList(List<Voznik> voznikList) {
        this.voznikList = voznikList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Narocilo> getNarociloList() {
        return narociloList;
    }

    public void setNarociloList(List<Narocilo> narociloList) {
        this.narociloList = narociloList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iduporabnik != null ? iduporabnik.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uporabnik)) {
            return false;
        }
        Uporabnik other = (Uporabnik) object;
        if ((this.iduporabnik == null && other.iduporabnik != null) || (this.iduporabnik != null && !this.iduporabnik.equals(other.iduporabnik))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "si.fri.sparis.taxi.entites.Uporabnik[ iduporabnik=" + iduporabnik + " ]";
    }
    
}
