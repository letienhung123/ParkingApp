/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.lth.dto.ParkingLotDTO;
import com.lth.pojo.AdminDetail;
import com.lth.pojo.ParkingLot;
import com.lth.repository.ParkingLotRepository;
import com.lth.service.ParkingLotService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lth7p
 */
@Service
public class ParkingLotServiceImpl implements ParkingLotService {

    @Autowired
    private ParkingLotRepository parkingLotRepo;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<ParkingLot> getParkingLots(Map<String, String> params) {
        List<ParkingLot> parkingLots = this.parkingLotRepo.getParkingLots(params);
        for (ParkingLot lot : parkingLots) {
            int emptySpots = this.parkingLotRepo.countEmptyParkingSpot(lot.getParkingLotID());
            lot.setEmptySpots(emptySpots);
        }
        return parkingLots;
    }

    @Override
    public int countParkingLots() {
        return this.parkingLotRepo.countParkingLots();
    }

    @Override
    public boolean addOrUpdateParkingLot(ParkingLot p) {
        if (!p.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(p.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                p.setImageURL(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(ParkingLotServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        p.setAdminID(new AdminDetail(1));
        return this.parkingLotRepo.addOrUpdateParkingLot(p);
    }

    @Override
    public ParkingLot getParkingLotById(int id) {
        return this.parkingLotRepo.getParkingLotById(id);
    }

    @Override
    public boolean deleteParkingLot(int id) {
        return this.parkingLotRepo.deleteParkingLot(id);
    }

    @Override
    public int countEmptyParkingSpot(int id) {
        return this.parkingLotRepo.countEmptyParkingSpot(id);
    }

}
