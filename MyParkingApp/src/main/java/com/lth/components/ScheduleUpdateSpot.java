/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.components;

import com.lth.repository.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author lth7p
 */
@Component
public class ScheduleUpdateSpot {
    @Autowired
    private ParkingSpotRepository spotRepo;
    
//    @Scheduled(fixedRate = 3000)
//    public void updateStatus() {
//        spotRepo.updateParkingSpotStatus();
//    }
}
