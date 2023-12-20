/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import net.bytebuddy.implementation.bind.annotation.Empty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "tours")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tours.findAll", query = "SELECT t FROM Tours t"),
    @NamedQuery(name = "Tours.findById", query = "SELECT t FROM Tours t WHERE t.id = :id"),
    @NamedQuery(name = "Tours.findByName", query = "SELECT t FROM Tours t WHERE t.name = :name"),
    @NamedQuery(name = "Tours.findByDescription", query = "SELECT t FROM Tours t WHERE t.description = :description"),
    @NamedQuery(name = "Tours.findByImage", query = "SELECT t FROM Tours t WHERE t.image = :image"),
    @NamedQuery(name = "Tours.findByDeparture", query = "SELECT t FROM Tours t WHERE t.departure = :departure"),
    @NamedQuery(name = "Tours.findByDestination", query = "SELECT t FROM Tours t WHERE t.destination = :destination"),
    @NamedQuery(name = "Tours.findByTimeStart", query = "SELECT t FROM Tours t WHERE t.timeStart = :timeStart"),
    @NamedQuery(name = "Tours.findByVehicle", query = "SELECT t FROM Tours t WHERE t.vehicle = :vehicle"),
    @NamedQuery(name = "Tours.findByAccommodation", query = "SELECT t FROM Tours t WHERE t.accommodation = :accommodation"),
    @NamedQuery(name = "Tours.findByTime", query = "SELECT t FROM Tours t WHERE t.time = :time")})
public class Tours implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Size(min = 5, max = 50, message = "{tours.name.lenErrMsg}")
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "image")
    private String image;
    @Basic(optional = false)
    @Column(name = "departure")
    @NotBlank(message = "{tours.blank}")
    @NotEmpty(message = "{tours.departure.notNullMsg}")   
    @NotNull()
    private String departure;
    @Basic(optional = false)
    @NotBlank(message = "{tours.blank}")
    @NotEmpty(message = "{tours.destination.notNullMsg}")   
    @NotNull()
    @Column(name = "destination")
    private String destination;
    @Basic(optional = false)
    @NotNull(message = "{tours.timeStart.notNullMsg}")
    @Column(name = "time_start")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date timeStart;
    @Basic(optional = false)
    @NotNull()
    @NotBlank(message = "{tours.blank}")
    @NotEmpty(message = "{tours.vehicle.notNullMsg}")   
    @Column(name = "vehicle")
    private String vehicle;
    @Basic(optional = false)
    @NotBlank(message = "{tours.blank}")
    @NotEmpty(message = "{tours.accommodation.notNullMsg}")   
    @NotNull()
    @Column(name = "accommodation")
    private String accommodation;
    @Basic(optional = false)
    @NotBlank(message = "{tours.blank}")
    @NotEmpty(message = "{tours.time.notNullMsg}")      
    @NotNull()
    @Column(name = "time")
    private String time;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tourId")
    private Set<Receipts> receiptsSet;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tourId")
    private Set<Comments> commentsSet;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tourId")
    private Set<Rating> ratingSet;
    @JoinColumn(name = "price_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Prices priceId;
    @JoinColumn(name = "tour_cate", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TourCategory tourCate;

    public Prices getPriceId() {
        return priceId;
    }

    public void setPriceId(Prices priceId) {
        this.priceId = priceId;
    }
     @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "toursId")
    private Set<TourImages> tourImagesSet;
     @Transient
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
    public Tours() {
    }

    public Tours(Integer id) {
        this.id = id;
    }

    public Tours(Integer id, String name, String image, String departure, String destination, Date timeStart, String vehicle, String accommodation, String time) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.departure = departure;
        this.destination = destination;
        this.timeStart = timeStart;
        this.vehicle = vehicle;
        this.accommodation = accommodation;
        this.time = time;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(String accommodation) {
        this.accommodation = accommodation;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @XmlTransient
    public Set<Receipts> getReceiptsSet() {
        return receiptsSet;
    }

    public void setReceiptsSet(Set<Receipts> receiptsSet) {
        this.receiptsSet = receiptsSet;
    }

    @XmlTransient
    public Set<Comments> getCommentsSet() {
        return commentsSet;
    }

    public void setCommentsSet(Set<Comments> commentsSet) {
        this.commentsSet = commentsSet;
    }
    
    @XmlTransient
    public Set<Rating> getRatingSet() {
        return ratingSet;
    }

    public void setRatingSet(Set<Rating> ratingSet) {
        this.ratingSet = ratingSet;
    }
    


    public TourCategory getTourCate() {
        return tourCate;
    }

    public void setTourCate(TourCategory tourCate) {
        this.tourCate = tourCate;
    }

    @XmlTransient
    public Set<TourImages> getTourImagesSet() {
        return tourImagesSet;
    }

    public void setTourImagesSet(Set<TourImages> tourImagesSet) {
        this.tourImagesSet = tourImagesSet;
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
        if (!(object instanceof Tours)) {
            return false;
        }
        Tours other = (Tours) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tdh.pojo.Tours[ id=" + id + " ]";
    }
    
}
