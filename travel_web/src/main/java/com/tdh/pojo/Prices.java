/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "prices")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prices.findAll", query = "SELECT p FROM Prices p"),
    @NamedQuery(name = "Prices.findById", query = "SELECT p FROM Prices p WHERE p.id = :id"),
    @NamedQuery(name = "Prices.findByPriceAdult", query = "SELECT p FROM Prices p WHERE p.priceAdult = :priceAdult"),
    @NamedQuery(name = "Prices.findByPriceChild", query = "SELECT p FROM Prices p WHERE p.priceChild = :priceChild"),
    @NamedQuery(name = "Prices.findByNamePrice", query = "SELECT p FROM Prices p WHERE p.namePrice = :namePrice")})
public class Prices implements Serializable {

    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price_adult")
    private long priceAdult;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price_child")
    private long priceChild;
    @Basic(optional = false)
    @NotNull
    @Column(name = "namePrice")
    private String namePrice;
    @Column(name = "isDelete")
    private Boolean isDelete;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "priceId")
    private Set<Tours> toursSet;

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getNamePrice() {
        return namePrice;
    }

    public void setNamePrice(String namePrice) {
        this.namePrice = namePrice;
    }


    @XmlTransient
    public Set<Tours> getToursSet() {
        return toursSet;
    }

    public void setToursSet(Set<Tours> toursSet) {
        this.toursSet = toursSet;
    }

    public Prices() {
    }

    public Prices(Integer id) {
        this.id = id;
    }

    public Prices(Integer id, long priceAdult, long priceChild) {
        this.id = id;
        this.priceAdult = priceAdult;
        this.priceChild = priceChild;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getPriceAdult() {
        return priceAdult;
    }

    public void setPriceAdult(long priceAdult) {
        this.priceAdult = priceAdult;
    }

    public long getPriceChild() {
        return priceChild;
    }

    public void setPriceChild(long priceChild) {
        this.priceChild = priceChild;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prices)) {
            return false;
        }
        Prices other = (Prices) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tdh.pojo.Prices[ id=" + id + " ]";
    }

}
