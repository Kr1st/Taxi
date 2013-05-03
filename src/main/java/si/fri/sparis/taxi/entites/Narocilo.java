/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package si.fri.sparis.taxi.entites;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kristian
 */
@Entity
@Table(name = "NAROCILO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Narocilo.findAll", query = "SELECT n FROM Narocilo n"),
    @NamedQuery(name = "Narocilo.findByIdnarocilo", query = "SELECT n FROM Narocilo n WHERE n.idnarocilo = :idnarocilo"),
    @NamedQuery(name = "Narocilo.findByDatum", query = "SELECT n FROM Narocilo n WHERE n.datum = :datum"),
    @NamedQuery(name = "Narocilo.findByStatus", query = "SELECT n FROM Narocilo n WHERE n.status = :status"),
    @NamedQuery(name = "Narocilo.findByCena", query = "SELECT n FROM Narocilo n WHERE n.cena = :cena")})
public class Narocilo implements Serializable {
    @Size(max = 50)
    @Column(name = "LOKACIJA")
    private String lokacija;
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDNAROCILO")
    private Integer idnarocilo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATUM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STATUS")
    private int status;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CENA")
    private Double cena;
    @JoinColumn(name = "IDVOZNIK", referencedColumnName = "IDVOZNIK")
    @ManyToOne(optional = false)
    private Voznik idvoznik;
    @JoinColumn(name = "IDUPORABNIK", referencedColumnName = "IDUPORABNIK")
    @ManyToOne(optional = false)
    private Uporabnik iduporabnik;

    public Narocilo() {
    }

    public Narocilo(Integer idnarocilo) {
        this.idnarocilo = idnarocilo;
    }

    public Narocilo(Integer idnarocilo, Date datum, int status) {
        this.idnarocilo = idnarocilo;
        this.datum = datum;
        this.status = status;
    }

    public Integer getIdnarocilo() {
        return idnarocilo;
    }

    public void setIdnarocilo(Integer idnarocilo) {
        this.idnarocilo = idnarocilo;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public Voznik getIdvoznik() {
        return idvoznik;
    }

    public void setIdvoznik(Voznik idvoznik) {
        this.idvoznik = idvoznik;
    }

    public Uporabnik getIduporabnik() {
        return iduporabnik;
    }

    public void setIduporabnik(Uporabnik iduporabnik) {
        this.iduporabnik = iduporabnik;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnarocilo != null ? idnarocilo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Narocilo)) {
            return false;
        }
        Narocilo other = (Narocilo) object;
        if ((this.idnarocilo == null && other.idnarocilo != null) || (this.idnarocilo != null && !this.idnarocilo.equals(other.idnarocilo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "si.fri.sparis.taxi.entites.Narocilo[ idnarocilo=" + idnarocilo + " ]";
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }
    
}
