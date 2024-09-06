/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.controllers;

import com.lth.pojo.ParkingSpot;

import com.lth.service.ParkingSpotService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lth7p
 */
@RestController
@RequestMapping("/api")
public class ApiParkingSpotController {

    @Autowired
    private ParkingSpotService parkingSpotService;

    @GetMapping("/parkinglots/{parkingLotID}/parkingspots")
    @CrossOrigin
    public ResponseEntity<List<ParkingSpot>> listParkingSpots(@PathVariable("parkingLotID") int id) {
        List<ParkingSpot> parkingSports = this.parkingSpotService.getSpotsByParkingLotId(id);
        return new ResponseEntity<>(parkingSports, HttpStatus.OK);
    }
    
    @GetMapping("/parkingspot/update-empty/{spotID}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    public void changeStateEmpty(@PathVariable(value = "spotID") int id){
        this.parkingSpotService.updateSpotEmpty(id);
    }
    @GetMapping("/parkingspot/update-inuse/{spotID}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    public void changeStateInUse(@PathVariable(value = "spotID") int id){
        this.parkingSpotService.updateSpotInUse(id);
    }
}
