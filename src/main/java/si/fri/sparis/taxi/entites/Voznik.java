/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package si.fri.sparis.taxi.entites;

import java.io.Serializable;
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
@Table(name = "VOZNIK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Voznik.findAll", query = "SELECT v FROM Voznik v"),
    @NamedQuery(name = "Voznik.findByIdvoznik", query = "SELECT v FROM Voznik v WHERE v.idvoznik = :idvoznik"),
    @NamedQuery(name = "Voznik.findByIme", query = "SELECT v FROM Voznik v WHERE v.ime = :ime"),
    @NamedQuery(name = "Voznik.findByPriimek", query = "SELECT v FROM Voznik v WHERE v.priimek = :priimek")})
public class Voznik implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDVOZNIK")
    private Integer idvoznik;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idvoznik")
    private List<Narocilo> narociloList;

    public Voznik() {
    }

    public Voznik(Integer idvoznik) {
        this.idvoznik = idvoznik;
    }

    public Voznik(Integer idvoznik, String ime, String priimek) {
        this.idvoznik = idvoznik;
        this.ime = ime;
        this.priimek = priimek;
    }

    public Integer getIdvoznik() {
        return idvoznik;
    }

    public void setIdvoznik(Integer idvoznik) {
        this.idvoznik = idvoznik;
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
        hash += (idvoznik != null ? idvoznik.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Voznik)) {
            return false;
        }
        Voznik other = (Voznik) object;
        if ((this.idvoznik == null && other.idvoznik != null) || (this.idvoznik != null && !this.idvoznik.equals(other.idvoznik))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "si.fri.sparis.taxi.entites.Voznik[ idvoznik=" + idvoznik + " ]";
    }
    
}
