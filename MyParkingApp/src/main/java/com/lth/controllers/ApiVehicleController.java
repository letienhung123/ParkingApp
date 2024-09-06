/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.controllers;

import com.lth.pojo.Vehicle;
import com.lth.service.VehicleService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lth7p
 */
@RestController
@RequestMapping("/api")
public class ApiVehicleController {
    
    @Autowired
    private VehicleService vehicleService;
    
    @PostMapping("/vehicles")
    @CrossOrigin
    public ResponseEntity<Vehicle> addVehicle(@RequestParam Map<String, String> params){
        Vehicle v = this.vehicleService.addVehicle(params);
        return new ResponseEntity<>(v,HttpStatus.CREATED);
    }
}
