/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.service.impl;

import com.lth.pojo.Account;
import com.lth.pojo.Reservation;
import com.lth.pojo.ReservationTemp;
import com.lth.pojo.UserDetail;
import com.lth.repository.AccountRepository;

import com.lth.repository.ReceiptRepository;
import com.lth.repository.UserDetailRepository;

import com.lth.service.ReceiptService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

/**
 *
 * @author lth7p
 */
@Service
public class ReceiptServiceImpl implements ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepo;
    @Autowired
    private AccountRepository accRepo;
    @Autowired
    private UserDetailRepository userRepo;

    @Override
    public void addReceipt(ReservationTemp r) {
        this.receiptRepo.addReceipt(r);
    }

    @Override
    public List<Reservation> getReservationsByLotID(int id) {
        return this.receiptRepo.getReservationsByLotID(id);
    }

    @Override
    public List<Reservation> getReservationsByUserID(int id) {
        return this.receiptRepo.getReservationsByUserID(id);
    }

    @Override
    public List<Reservation> getWaitingReservations(int id) {
        List<Reservation> reservations = this.receiptRepo.getWaitingReservations(id);
        for (Reservation r : reservations){
            Account a = this.accRepo.getAccountByReservationID(r.getReservationID());
            UserDetail u = this.userRepo.findByUsername(a.getUsername());
    
            r.setFulName(a.getFirstName()+" "+a.getLastName());
            r.setPlate(u.getLicensePlate());
            r.setPhoneNum(a.getPhoneNumber());   
            r.setLocation(String.valueOf(r.getSpotID().getLevel())+"-"+String.valueOf(r.getSpotID().getSpotNumber()));
        }
        
        
        return reservations;
    }

    @Override
    public void updateReservation(int id) {
        this.receiptRepo.updateReservation(id);
    }

}
