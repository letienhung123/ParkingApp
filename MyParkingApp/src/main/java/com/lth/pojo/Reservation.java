/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lth7p
 */
@Entity
@Table(name = "reservation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reservation.findAll", query = "SELECT r FROM Reservation r"),
    @NamedQuery(name = "Reservation.findByReservationID", query = "SELECT r FROM Reservation r WHERE r.reservationID = :reservationID"),
    @NamedQuery(name = "Reservation.findByStartTime", query = "SELECT r FROM Reservation r WHERE r.startTime = :startTime"),
    @NamedQuery(name = "Reservation.findByEndTime", query = "SELECT r FROM Reservation r WHERE r.endTime = :endTime"),
    @NamedQuery(name = "Reservation.findByReservationStatus", query = "SELECT r FROM Reservation r WHERE r.reservationStatus = :reservationStatus"),
    @NamedQuery(name = "Reservation.findByTotalPrice", query = "SELECT r FROM Reservation r WHERE r.totalPrice = :totalPrice")})

    @NamedQuery(name = "Reservation.findByParkingLotID", query = "SELECT r FROM Reservation r WHERE r.spotID.parkingLotID.parkingLotID = :parkingLotID")

public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ReservationID")
    private Integer reservationID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "StartTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EndTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "ReservationStatus")
    private String reservationStatus;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "TotalPrice")
    private BigDecimal totalPrice;
    @JoinColumn(name = "SpotID", referencedColumnName = "SpotID")
    @ManyToOne(optional = false)
    @JsonIgnore
    private ParkingSpot spotID;
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    @ManyToOne(optional = false)
    @JsonIgnore
    private UserDetail userID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reservationID")
    @JsonIgnore
    private Set<Payment> paymentSet;
    
    @Transient
    private String parkingLotName;
    @Transient
    private String fulName;
    @Transient
    private String plate;
    @Transient
    private String phoneNum;
    @Transient
    private String location;

    public Reservation() {
    }

    public Reservation(Integer reservationID) {
        this.reservationID = reservationID;
    }

    public Reservation(Integer reservationID, Date startTime, Date endTime, String reservationStatus, BigDecimal totalPrice) {
        this.reservationID = reservationID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reservationStatus = reservationStatus;
        this.totalPrice = totalPrice;
    }

    public Integer getReservationID() {
        return reservationID;
    }

    public void setReservationID(Integer reservationID) {
        this.reservationID = reservationID;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ParkingSpot getSpotID() {
        return spotID;
    }

    public void setSpotID(ParkingSpot spotID) {
        this.spotID = spotID;
    }

    public UserDetail getUserID() {
        return userID;
    }

    public void setUserID(UserDetail userID) {
        this.userID = userID;
    }

    @XmlTransient
    public Set<Payment> getPaymentSet() {
        return paymentSet;
    }

    public void setPaymentSet(Set<Payment> paymentSet) {
        this.paymentSet = paymentSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservationID != null ? reservationID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) object;
        if ((this.reservationID == null && other.reservationID != null) || (this.reservationID != null && !this.reservationID.equals(other.reservationID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lth.pojo.Reservation[ reservationID=" + reservationID + " ]";
    }

    /**
     * @return the parkingLotName
     */
    public String getParkingLotName() {
        return parkingLotName;
    }

    /**
     * @param parkingLotName the parkingLotName to set
     */
    public void setParkingLotName(String parkingLotName) {
        this.parkingLotName = parkingLotName;
    }

    /**
     * @return the fulName
     */
    public String getFulName() {
        return fulName;
    }

    /**
     * @param fulName the fulName to set
     */
    public void setFulName(String fulName) {
        this.fulName = fulName;
    }


    /**
     * @return the phoneNum
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * @param phoneNum the phoneNum to set
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    /**
     * @return the plate
     */
    public String getPlate() {
        return plate;
    }

    /**
     * @param plate the plate to set
     */
    public void setPlate(String plate) {
        this.plate = plate;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

}
