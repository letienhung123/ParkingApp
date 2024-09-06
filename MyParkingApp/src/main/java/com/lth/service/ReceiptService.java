/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lth.service;

import com.lth.pojo.Reservation;
import com.lth.pojo.ReservationTemp;
import java.util.List;


/**
 *
 * @author lth7p
 */
public interface ReceiptService {
    void addReceipt(ReservationTemp r);
    List<Reservation> getReservationsByLotID(int id);
    List<Reservation> getReservationsByUserID(int id);
    List<Reservation> getWaitingReservations(int id);
    void updateReservation(int id);
}
