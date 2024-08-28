/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.service.impl;

import com.lth.pojo.ParkingSpot;
import com.lth.repository.ParkingSpotRepository;
import com.lth.service.ParkingSpotService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lth7p
 */
@Service
public class ParkingSpotServiceImpl implements ParkingSpotService{
    @Autowired
    private ParkingSpotRepository parkingSpotRepo;

    @Override
    public List<ParkingSpot> getSpotsByParkingLotId(int id) {
        return this.parkingSpotRepo.getSpotsByParkingLotId(id);
    }

    @Override
    public boolean addOrUpdateParkingSpot(ParkingSpot ps) {
        ps.setStatus("Empty");
        return this.parkingSpotRepo.addOrUpdateParkingSpot(ps);
    }

    @Override
    public ParkingSpot getParkingSpotById(int id) {
        return this.parkingSpotRepo.getParkingSpotById(id);
    }
    
}
