/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.service.impl;

import com.lth.pojo.Vehicle;
import com.lth.repository.UserDetailRepository;
import com.lth.repository.VehicleRepository;
import com.lth.service.VehicleService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author lth7p
 */
@Service
public class VehicleServiceImpl implements VehicleService{
    @Autowired
    private VehicleRepository vehicleRepo;
    @Autowired
    private UserDetailRepository userRepo;

    @Override
    public Vehicle addVehicle(Map<String, String> params) {
        
        Vehicle v = new Vehicle();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        v.setUserID(this.userRepo.findByUsername(authentication.getName()));
        v.setMake(params.get("make"));
        v.setModel(params.get("model"));
        v.setYear(Integer.parseInt(params.get("year")));
        v.setLicensePlate(params.get("licensePlate"));
        v.setColor(params.get("color"));
        
        this.vehicleRepo.addVehicle(v);
        
        return v;
    }
    
}
