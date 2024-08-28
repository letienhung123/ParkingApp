/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.pojo;

import java.io.Serializable;
import java.util.Set;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lth7p
 */
@Entity
@Table(name = "admin_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdminDetail.findAll", query = "SELECT a FROM AdminDetail a"),
    @NamedQuery(name = "AdminDetail.findByAdminID", query = "SELECT a FROM AdminDetail a WHERE a.adminID = :adminID")})
public class AdminDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "AdminID")
    private Integer adminID;
    @JoinColumn(name = "AccountID", referencedColumnName = "AccountID")
    @ManyToOne(optional = false)
    private Account accountID;
    @OneToMany(mappedBy = "adminID")
    private Set<ParkingLot> parkingLotSet;

    public AdminDetail() {
    }

    public AdminDetail(Integer adminID) {
        this.adminID = adminID;
    }

    public Integer getAdminID() {
        return adminID;
    }

    public void setAdminID(Integer adminID) {
        this.adminID = adminID;
    }

    public Account getAccountID() {
        return accountID;
    }

    public void setAccountID(Account accountID) {
        this.accountID = accountID;
    }

    @XmlTransient
    public Set<ParkingLot> getParkingLotSet() {
        return parkingLotSet;
    }

    public void setParkingLotSet(Set<ParkingLot> parkingLotSet) {
        this.parkingLotSet = parkingLotSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adminID != null ? adminID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdminDetail)) {
            return false;
        }
        AdminDetail other = (AdminDetail) object;
        if ((this.adminID == null && other.adminID != null) || (this.adminID != null && !this.adminID.equals(other.adminID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lth.pojo.AdminDetail[ adminID=" + adminID + " ]";
    }
    
}
