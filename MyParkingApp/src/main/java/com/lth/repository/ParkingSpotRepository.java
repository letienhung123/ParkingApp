/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lth.repository;

import com.lth.pojo.ParkingSpot;
import java.util.List;

/**
 *
 * @author lth7p
 */
public interface ParkingSpotRepository {
    List<ParkingSpot> getSpotsByParkingLotId(int id);
    boolean addOrUpdateParkingSpot(ParkingSpot ps);
    ParkingSpot getParkingSpotById(int id);
}
