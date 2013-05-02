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
@Table(name = "DRIVERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Drivers.findAll", query = "SELECT d FROM Drivers d"),
    @NamedQuery(name = "Drivers.findByIdDrivers", query = "SELECT d FROM Drivers d WHERE d.idDrivers = :idDrivers"),
    @NamedQuery(name = "Drivers.findByName", query = "SELECT d FROM Drivers d WHERE d.name = :name"),
    @NamedQuery(name = "Drivers.findBySurname", query = "SELECT d FROM Drivers d WHERE d.surname = :surname")})
public class Drivers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDrivers")
    private Integer idDrivers;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "surname")
    private String surname;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDrivers")
    private List<Orders> ordersList;

    public Drivers() {
    }

    public Drivers(Integer idDrivers) {
        this.idDrivers = idDrivers;
    }

    public Drivers(Integer idDrivers, String name, String surname) {
        this.idDrivers = idDrivers;
        this.name = name;
        this.surname = surname;
    }

    public Integer getIdDrivers() {
        return idDrivers;
    }

    public void setIdDrivers(Integer idDrivers) {
        this.idDrivers = idDrivers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @XmlTransient
    @JsonIgnore
    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDrivers != null ? idDrivers.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Drivers)) {
            return false;
        }
        Drivers other = (Drivers) object;
        if ((this.idDrivers == null && other.idDrivers != null) || (this.idDrivers != null && !this.idDrivers.equals(other.idDrivers))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "si.fri.sparis.taxi.entites.Drivers[ idDrivers=" + idDrivers + " ]";
    }
    
}
