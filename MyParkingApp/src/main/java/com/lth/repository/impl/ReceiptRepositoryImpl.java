/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.repository.impl;

import com.lth.pojo.ParkingLot;
import com.lth.pojo.ParkingSpot;
import com.lth.pojo.Payment;
import com.lth.pojo.Reservation;
import com.lth.pojo.ReservationTemp;
import com.lth.repository.ParkingSpotRepository;
import com.lth.repository.ReceiptRepository;
import com.lth.repository.UserDetailRepository;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lth7p
 */
@Repository
@Transactional
public class ReceiptRepositoryImpl implements ReceiptRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private UserDetailRepository userRepo;
    @Autowired
    private ParkingSpotRepository spotRepo;

    @Override
    public void addReceipt(ReservationTemp r) {
        Session s = this.factory.getObject().getCurrentSession();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Reservation re = new Reservation();
        re.setUserID(this.userRepo.findByUsername(authentication.getName()));
        re.setStartTime(r.getStartTime());
        re.setEndTime(r.getEndTime());
        re.setSpotID(this.spotRepo.getParkingSpotById(r.getParkingSpotId()));
        re.setReservationStatus(r.getReservationStatus());
        re.setTotalPrice(r.getTotalPrice());

        s.save(re);

//        if (re.getReservationStatus().equals("Confirmed")) {
            Payment p = new Payment();
            p.setReservationID(re);
            p.setPaymentDate(new Date());
            p.setPaymentMethod("E-wallets");
            p.setPaymentStatus("successful");
            p.setAmount(re.getTotalPrice());
            s.save(p);

            ParkingSpot ps = this.spotRepo.getParkingSpotById(r.getParkingSpotId());
            ps.setStatus("Booked");
            this.spotRepo.addOrUpdateParkingSpot(ps);
//        }

    }

    @Override
    public List<Reservation> getReservationsByLotID(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        List<Reservation> reservations = null;

        String hql = "SELECT r FROM Reservation r WHERE r.spotID.parkingLotID.parkingLotID = :parkingLotID";
        Query<Reservation> query = s.createQuery(hql, Reservation.class);
        query.setParameter("parkingLotID", id);

        reservations = query.list();
        return reservations;
    }

    @Override
    public List<Reservation> getReservationsByUserID(int id) {
        List<Reservation> reservations = null;
        Session s = this.factory.getObject().getCurrentSession();

        String hql = "SELECT r FROM Reservation r WHERE r.userID.accountID.accountID = :accountID "
                + "ORDER BY r.reservationID DESC";
        Query<Reservation> query = s.createQuery(hql, Reservation.class);
        query.setParameter("accountID", id);
        reservations = query.list();

        for (Reservation reservation : reservations) {
            if (reservation.getSpotID() != null && reservation.getSpotID().getParkingLotID() != null) {
                ParkingLot parkingLot = reservation.getSpotID().getParkingLotID();
                if (parkingLot != null) {
                    reservation.setParkingLotName(parkingLot.getName());
                }
            }
        }

        return reservations;
    }

    @Override
    public List<Reservation> getWaitingReservations(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        List<Reservation> reservations = null;

        String hql = "SELECT r FROM Reservation r WHERE r.reservationStatus = :status" +
                " AND r.spotID.parkingLotID.parkingLotID = :parkingLotID";
        Query<Reservation> query = s.createQuery(hql, Reservation.class);
        query.setParameter("status", "Waiting");
        query.setParameter("parkingLotID", id);

        reservations = query.list();
        
        return reservations;
    }

    @Override
    public void updateReservation(int id) {
//        Session s = this.factory.getObject().getCurrentSession();
//        Reservation r = s.get(Reservation.class, id);
//        r.setReservationStatus("Confirmed");
    Session s = this.factory.getObject().getCurrentSession();

    String hql = "SELECT r FROM Reservation r WHERE r.reservationStatus = :status AND r.userID.accountID.accountID = :accountID";
    Query<Reservation> query = s.createQuery(hql, Reservation.class);
    query.setParameter("status", "Waiting");
    query.setParameter("accountID", id);

    List<Reservation> reservations = query.list();

    for (Reservation r : reservations) {
        r.setReservationStatus("Confirmed");
        s.update(r);
    }
    }

}
