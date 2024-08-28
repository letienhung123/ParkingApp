/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.pojo;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lth7p
 */
@Entity
@Table(name = "parking_spot")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParkingSpot.findAll", query = "SELECT p FROM ParkingSpot p"),
    @NamedQuery(name = "ParkingSpot.findBySpotID", query = "SELECT p FROM ParkingSpot p WHERE p.spotID = :spotID"),
    @NamedQuery(name = "ParkingSpot.findByStatus", query = "SELECT p FROM ParkingSpot p WHERE p.status = :status"),
    @NamedQuery(name = "ParkingSpot.findByLevel", query = "SELECT p FROM ParkingSpot p WHERE p.level = :level"),
    @NamedQuery(name = "ParkingSpot.findBySpotNumber", query = "SELECT p FROM ParkingSpot p WHERE p.spotNumber = :spotNumber")})
public class ParkingSpot implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SpotID")
    private Integer spotID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "Status")
    private String status;
    @Column(name = "Level")
    private int level;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SpotNumber")
    private int spotNumber;
    @JoinColumn(name = "ParkingLotID", referencedColumnName = "ParkingLotID")
    @ManyToOne(optional = false)
    private ParkingLot parkingLotID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "spotID")
    private Set<Reservation> reservationSet;

    public ParkingSpot() {
    }

    public ParkingSpot(Integer spotID) {
        this.spotID = spotID;
    }

    public ParkingSpot(Integer spotID, String status, int spotNumber) {
        this.spotID = spotID;
        this.status = status;
        this.spotNumber = spotNumber;
    }

    public Integer getSpotID() {
        return spotID;
    }

    public void setSpotID(Integer spotID) {
        this.spotID = spotID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(int spotNumber) {
        this.spotNumber = spotNumber;
    }

    public ParkingLot getParkingLotID() {
        return parkingLotID;
    }

    public void setParkingLotID(ParkingLot parkingLotID) {
        this.parkingLotID = parkingLotID;
    }

    @XmlTransient
    public Set<Reservation> getReservationSet() {
        return reservationSet;
    }

    public void setReservationSet(Set<Reservation> reservationSet) {
        this.reservationSet = reservationSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (spotID != null ? spotID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParkingSpot)) {
            return false;
        }
        ParkingSpot other = (ParkingSpot) object;
        if ((this.spotID == null && other.spotID != null) || (this.spotID != null && !this.spotID.equals(other.spotID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lth.pojo.ParkingSpot[ spotID=" + spotID + " ]";
    }
    
}
