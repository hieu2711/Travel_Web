/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "receipts")
@NamedQueries({
    @NamedQuery(name = "Receipts.findAll", query = "SELECT r FROM Receipts r"),
    @NamedQuery(name = "Receipts.findById", query = "SELECT r FROM Receipts r WHERE r.id = :id"),
    @NamedQuery(name = "Receipts.findByPaymentsDate", query = "SELECT r FROM Receipts r WHERE r.paymentsDate = :paymentsDate"),
    @NamedQuery(name = "Receipts.findByAdult", query = "SELECT r FROM Receipts r WHERE r.adult = :adult"),
    @NamedQuery(name = "Receipts.findByChild", query = "SELECT r FROM Receipts r WHERE r.child = :child"),
    @NamedQuery(name = "Receipts.findByPaymentsMethod", query = "SELECT r FROM Receipts r WHERE r.paymentsMethod = :paymentsMethod"),
    @NamedQuery(name = "Receipts.findByStaffsId", query = "SELECT r FROM Receipts r WHERE r.staffsId = :staffsId"),
    @NamedQuery(name = "Receipts.findByAmountPrice", query = "SELECT r FROM Receipts r WHERE r.amountPrice = :amountPrice")})
public class Receipts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "payments_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentsDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "payments_method")
    private int paymentsMethod;
    @Basic(optional = false)
    @NotNull
    @Column(name = "staffs_id")
    private int staffsId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount_price")
    private long amountPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "adult")
    private long adult;
    @Basic(optional = false)
    @NotNull
    @Column(name = "child")
    private long child;
    @JoinColumn(name = "tour_id", referencedColumnName = "id")
    @JsonIgnore
    @ManyToOne(optional = false)
    private Tours tourId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users userId;

    public Receipts() {
    }

    public Receipts(Integer id) {
        this.id = id;
    }

    public Receipts(Integer id, Date paymentsDate, int paymentsMethod, int staffsId, long amountPrice) {
        this.id = id;
        this.paymentsDate = paymentsDate;
        this.paymentsMethod = paymentsMethod;
        this.staffsId = staffsId;
        this.amountPrice = amountPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getAdult() {
        return adult;
    }

    public void setAdult(long adult) {
        this.adult = adult;
    }

    public long getChild() {
        return child;
    }

    public void setChild(long child) {
        this.child = child;
    }

    public Date getPaymentsDate() {
        return paymentsDate;
    }

    public void setPaymentsDate(Date paymentsDate) {
        this.paymentsDate = paymentsDate;
    }

    public int getPaymentsMethod() {
        return paymentsMethod;
    }

    public void setPaymentsMethod(int paymentsMethod) {
        this.paymentsMethod = paymentsMethod;
    }

    public int getStaffsId() {
        return staffsId;
    }

    public void setStaffsId(int staffsId) {
        this.staffsId = staffsId;
    }

    public long getAmountPrice() {
        return amountPrice;
    }

    public void setAmountPrice(long amountPrice) {
        this.amountPrice = amountPrice;
    }

    public Tours getTourId() {
        return tourId;
    }

    public void setTourId(Tours tourId) {
        this.tourId = tourId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
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
        if (!(object instanceof Receipts)) {
            return false;
        }
        Receipts other = (Receipts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tdh.pojo.Receipts[ int1=" + id + " ]";
    }
    
}
