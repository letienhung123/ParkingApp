/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.repository.impl;

import com.lth.pojo.Payment;
import com.lth.pojo.Reservation;
import com.lth.pojo.ReservationTemp;
import com.lth.repository.AccountRepository;
import com.lth.repository.ParkingSpotRepository;
import com.lth.repository.ReceiptRepository;
import com.lth.repository.UserDetailRepository;
import java.util.Date;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
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
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addReceipt(ReservationTemp r) {
        Session s = this.factory.getObject().getCurrentSession();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        try {
            Reservation reservation = new Reservation();
            reservation.setUserID(this.userRepo.findByUsername(authentication.getName()));
            reservation.setStartTime(r.getStartTime());
            reservation.setEndTime(r.getEndTime());
            reservation.setTotalPrice(r.getTotalPrice());
            reservation.setReservationStatus(r.getReservationStatus());
            reservation.setSpotID(this.spotRepo.getParkingSpotById(r.getParkingSpotId()));
            s.save(reservation);

            if (reservation.getReservationStatus().equals("Confirmed")) {
                Payment payment = new Payment();
                payment.setPaymentDate(new Date());
                payment.setReservationID(reservation);
                payment.setPaymentStatus("successful");
                s.save(payment);
            }
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
