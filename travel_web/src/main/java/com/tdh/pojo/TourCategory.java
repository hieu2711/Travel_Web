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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "tour_category")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TourCategory.findAll", query = "SELECT t FROM TourCategory t"),
    @NamedQuery(name = "TourCategory.findById", query = "SELECT t FROM TourCategory t WHERE t.id = :id"),
    @NamedQuery(name = "TourCategory.findByCategoryName", query = "SELECT t FROM TourCategory t WHERE t.categoryName = :categoryName")})
public class TourCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @NotBlank(message = "{tours.blank}")
    @NotEmpty(message = "{tours.image.notNullMsg}")  
    @Column(name = "category_name")
    private String categoryName;
    @Column(name = "isDelete")
    private Boolean isDelete;

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tourCate")
    private Set<Tours> toursSet;

    public TourCategory() {
    }

    public TourCategory(Integer id) {
        this.id = id;
    }

    public TourCategory(Integer id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @XmlTransient
    public Set<Tours> getToursSet() {
        return toursSet;
    }

    public void setToursSet(Set<Tours> toursSet) {
        this.toursSet = toursSet;
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
        if (!(object instanceof TourCategory)) {
            return false;
        }
        TourCategory other = (TourCategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tdh.pojo.TourCategory[ id=" + id + " ]";
    }
    
}
