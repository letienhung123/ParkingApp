/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lth.service;

import com.lth.pojo.ParkingSpot;
import com.lth.pojo.Spot;
import java.util.List;

/**
 *
 * @author lth7p
 */
public interface ParkingSpotService {
    List<ParkingSpot> getSpotsByParkingLotId(int id);
    boolean addOrUpdateParkingSpot(ParkingSpot ps);
    ParkingSpot getParkingSpotById(int id);
    void updateSpotInUse(int id);
    void updateSpotEmpty(int id);
    int countParkingSpotByLotID(int id);
}
