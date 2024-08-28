/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.pojo;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lth7p
 */
@Entity
@Table(name = "review")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Review.findAll", query = "SELECT r FROM Review r"),
    @NamedQuery(name = "Review.findByReviewID", query = "SELECT r FROM Review r WHERE r.reviewID = :reviewID"),
    @NamedQuery(name = "Review.findByRating", query = "SELECT r FROM Review r WHERE r.rating = :rating"),
    @NamedQuery(name = "Review.findByReviewDate", query = "SELECT r FROM Review r WHERE r.reviewDate = :reviewDate")})
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ReviewID")
    private Integer reviewID;
    @Column(name = "Rating")
    private Integer rating;
    @Lob
    @Size(max = 65535)
    @Column(name = "Comment")
    private String comment;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ReviewDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reviewDate;
    @JoinColumn(name = "ParkingLotID", referencedColumnName = "ParkingLotID")
    @ManyToOne(optional = false)
    private ParkingLot parkingLotID;
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    @ManyToOne(optional = false)
    private UserDetail userID;

    public Review() {
    }

    public Review(Integer reviewID) {
        this.reviewID = reviewID;
    }

    public Review(Integer reviewID, Date reviewDate) {
        this.reviewID = reviewID;
        this.reviewDate = reviewDate;
    }

    public Integer getReviewID() {
        return reviewID;
    }

    public void setReviewID(Integer reviewID) {
        this.reviewID = reviewID;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public ParkingLot getParkingLotID() {
        return parkingLotID;
    }

    public void setParkingLotID(ParkingLot parkingLotID) {
        this.parkingLotID = parkingLotID;
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
        hash += (reviewID != null ? reviewID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Review)) {
            return false;
        }
        Review other = (Review) object;
        if ((this.reviewID == null && other.reviewID != null) || (this.reviewID != null && !this.reviewID.equals(other.reviewID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lth.pojo.Review[ reviewID=" + reviewID + " ]";
    }
    
}
