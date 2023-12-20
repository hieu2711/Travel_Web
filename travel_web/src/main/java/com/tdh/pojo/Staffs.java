/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.pojo;

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
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "staffs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Staffs.findAll", query = "SELECT s FROM Staffs s"),
    @NamedQuery(name = "Staffs.findById", query = "SELECT s FROM Staffs s WHERE s.id = :id"),
    @NamedQuery(name = "Staffs.findByName", query = "SELECT s FROM Staffs s WHERE s.name = :name"),
    @NamedQuery(name = "Staffs.findByPhone", query = "SELECT s FROM Staffs s WHERE s.phone = :phone"),
    @NamedQuery(name = "Staffs.findByEmail", query = "SELECT s FROM Staffs s WHERE s.email = :email"),
    @NamedQuery(name = "Staffs.findByBirthday", query = "SELECT s FROM Staffs s WHERE s.birthday = :birthday"),
    @NamedQuery(name = "Staffs.findByFirst", query = "SELECT s FROM Staffs s WHERE s.first = :first"),
    @NamedQuery(name = "Staffs.findBySex", query = "SELECT s FROM Staffs s WHERE s.sex = :sex"),
    @NamedQuery(name = "Staffs.findByIdentification", query = "SELECT s FROM Staffs s WHERE s.identification = :identification"),
    @NamedQuery(name = "Staffs.findByAddress", query = "SELECT s FROM Staffs s WHERE s.address = :address")})
public class Staffs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @NotBlank(message = "{tours.blank}")
    @NotEmpty(message = "{tours.vehicle.notNullMsg}")   
    @Column(name = "name")
    private String name;
    @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Số điện thoại không hợp lệ")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "phone")
    private String phone;
    @Pattern(regexp="[a /-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Email không hợp lệ")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull(message = "{tours.timeStart.notNullMsg}")
    @Column(name = "birthday")
    @Temporal(javax.persistence.TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    @Basic(optional = false)
    @NotNull(message = "{tours.timeStart.notNullMsg}")
    @Column(name = "first")
    @Temporal(javax.persistence.TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date first;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sex")
    private int sex;
    @Basic(optional = false)
    @NotNull(message = "{tours.timeStart.notNullMsg}")
    @Column(name = "identification")
    private int identification;
    @Size(max = 45)
    @Column(name = "address")
    private String address;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users userId;
     @Transient
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
    public Staffs() {
    }

    public Staffs(Integer id) {
        this.id = id;
    }

    public Staffs(Integer id, String name, String phone, String email,Date birthday,Date first,int sex, int identification) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.birthday = birthday;
        this.sex = sex;
        this.first = first;
        this.identification = identification;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getFirst() {
        return first;
    }

    public void setFirst(Date first) {
        this.first = first;
    }
    
    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdentification() {
        return identification;
    }

    public void setIdentification(int identification) {
        this.identification = identification;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        if (!(object instanceof Staffs)) {
            return false;
        }
        Staffs other = (Staffs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tdh.pojo.Staffs[ id=" + id + " ]";
    }
    
}
