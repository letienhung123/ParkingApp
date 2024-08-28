/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lth.repository;

import com.lth.pojo.ParkingLot;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lth7p
 */
public interface ParkingLotRepository {
    List<ParkingLot> getParkingLots (Map<String, String> params);
    int countParkingLots();
    boolean addOrUpdateParkingLot(ParkingLot p);
    ParkingLot getParkingLotById (int id);
    boolean deleteParkingLot(int id);
    int countEmptyParkingSpot(int id);
}
