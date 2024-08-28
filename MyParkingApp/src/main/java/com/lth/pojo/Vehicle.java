/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lth7p
 */
@Entity
@Table(name = "vehicle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vehicle.findAll", query = "SELECT v FROM Vehicle v"),
    @NamedQuery(name = "Vehicle.findByVehicleID", query = "SELECT v FROM Vehicle v WHERE v.vehicleID = :vehicleID"),
    @NamedQuery(name = "Vehicle.findByMake", query = "SELECT v FROM Vehicle v WHERE v.make = :make"),
    @NamedQuery(name = "Vehicle.findByModel", query = "SELECT v FROM Vehicle v WHERE v.model = :model"),
    @NamedQuery(name = "Vehicle.findByYear", query = "SELECT v FROM Vehicle v WHERE v.year = :year"),
    @NamedQuery(name = "Vehicle.findByLicensePlate", query = "SELECT v FROM Vehicle v WHERE v.licensePlate = :licensePlate"),
    @NamedQuery(name = "Vehicle.findByColor", query = "SELECT v FROM Vehicle v WHERE v.color = :color")})
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "VehicleID")
    private Integer vehicleID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Make")
    private String make;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Model")
    private String model;
    @Column(name = "Year")
    private Integer year;
    @Size(max = 20)
    @Column(name = "LicensePlate")
    private String licensePlate;
    @Size(max = 20)
    @Column(name = "Color")
    private String color;
    @Lob
    @Size(max = 65535)
    @Column(name = "AdditionalInfo")
    private String additionalInfo;
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    @ManyToOne(optional = false)
    private UserDetail userID;

    public Vehicle() {
    }

    public Vehicle(Integer vehicleID) {
        this.vehicleID = vehicleID;
    }

    public Vehicle(Integer vehicleID, String make, String model) {
        this.vehicleID = vehicleID;
        this.make = make;
        this.model = model;
    }

    public Integer getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(Integer vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public UserDetail getUserID() {
        return userID;
    }

    public void setUserID(UserDetail userID) {
        this.userID = userID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vehicleID != null ? vehicleID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehicle)) {
            return false;
        }
        Vehicle other = (Vehicle) object;
        if ((this.vehicleID == null && other.vehicleID != null) || (this.vehicleID != null && !this.vehicleID.equals(other.vehicleID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lth.pojo.Vehicle[ vehicleID=" + vehicleID + " ]";
    }
    
}
