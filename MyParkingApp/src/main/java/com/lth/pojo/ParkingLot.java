/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author lth7p
 */
@Entity
@Table(name = "parking_lot")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParkingLot.findAll", query = "SELECT p FROM ParkingLot p"),
    @NamedQuery(name = "ParkingLot.findByParkingLotID", query = "SELECT p FROM ParkingLot p WHERE p.parkingLotID = :parkingLotID"),
    @NamedQuery(name = "ParkingLot.findByName", query = "SELECT p FROM ParkingLot p WHERE p.name = :name"),
    @NamedQuery(name = "ParkingLot.findByAddress", query = "SELECT p FROM ParkingLot p WHERE p.address = :address"),
    @NamedQuery(name = "ParkingLot.findByTotalSpots", query = "SELECT p FROM ParkingLot p WHERE p.totalSpots = :totalSpots"),
    @NamedQuery(name = "ParkingLot.findByPricePerHour", query = "SELECT p FROM ParkingLot p WHERE p.pricePerHour = :pricePerHour"),
    @NamedQuery(name = "ParkingLot.findByImageURL", query = "SELECT p FROM ParkingLot p WHERE p.imageURL = :imageURL"),
    @NamedQuery(name = "ParkingLot.findByLatitude", query = "SELECT p FROM ParkingLot p WHERE p.latitude = :latitude"),
    @NamedQuery(name = "ParkingLot.findByLongitude", query = "SELECT p FROM ParkingLot p WHERE p.longitude = :longitude")})
public class ParkingLot implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ParkingLotID")
    private Integer parkingLotID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Address")
    private String address;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TotalSpots")
    private int totalSpots;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "PricePerHour")
    private BigDecimal pricePerHour;
    @Lob
    @Size(max = 65535)
    @Column(name = "Facilities")
    private String facilities;
    @Size(max = 255)
    @Column(name = "ImageURL")
    private String imageURL;
    @Column(name = "Latitude")
    private BigDecimal latitude;
    @Column(name = "Longitude")
    private BigDecimal longitude;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parkingLotID")
    @JsonIgnore
    private Set<ParkingSpot> parkingSpotSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parkingLotID")
    @JsonIgnore
    private Set<Review> reviewSet;
    @JoinColumn(name = "AdminID", referencedColumnName = "AdminID")
    @ManyToOne
    @JsonIgnore
    private AdminDetail adminID;
    
    @Transient
    private MultipartFile file;
    
    @Transient
    private int emptySpots;

    public ParkingLot() {
    }

    public ParkingLot(Integer parkingLotID) {
        this.parkingLotID = parkingLotID;
    }

    public ParkingLot(Integer parkingLotID, String name, String address, int totalSpots, BigDecimal pricePerHour) {
        this.parkingLotID = parkingLotID;
        this.name = name;
        this.address = address;
        this.totalSpots = totalSpots;
        this.pricePerHour = pricePerHour;
    }

    public Integer getParkingLotID() {
        return parkingLotID;
    }

    public void setParkingLotID(Integer parkingLotID) {
        this.parkingLotID = parkingLotID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotalSpots() {
        return totalSpots;
    }

    public void setTotalSpots(int totalSpots) {
        this.totalSpots = totalSpots;
    }

    public BigDecimal getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(BigDecimal pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @XmlTransient
    public Set<ParkingSpot> getParkingSpotSet() {
        return parkingSpotSet;
    }

    public void setParkingSpotSet(Set<ParkingSpot> parkingSpotSet) {
        this.parkingSpotSet = parkingSpotSet;
    }

    @XmlTransient
    public Set<Review> getReviewSet() {
        return reviewSet;
    }

    public void setReviewSet(Set<Review> reviewSet) {
        this.reviewSet = reviewSet;
    }

    public AdminDetail getAdminID() {
        return adminID;
    }

    public void setAdminID(AdminDetail adminID) {
        this.adminID = adminID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parkingLotID != null ? parkingLotID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParkingLot)) {
            return false;
        }
        ParkingLot other = (ParkingLot) object;
        if ((this.parkingLotID == null && other.parkingLotID != null) || (this.parkingLotID != null && !this.parkingLotID.equals(other.parkingLotID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lth.pojo.ParkingLot[ parkingLotID=" + parkingLotID + " ]";
    }

    /**
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }

    /**
     * @return the emptySpots
     */
    public int getEmptySpots() {
        return emptySpots;
    }

    /**
     * @param emptySpots the emptySpots to set
     */
    public void setEmptySpots(int emptySpots) {
        this.emptySpots = emptySpots;
    }
    
}
