/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.controllers;

import com.lth.pojo.ParkingLot;
import com.lth.service.ParkingLotService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lth7p
 */
@RestController
@RequestMapping("/api")
public class ApiParkingLotController {
    @Autowired
    private ParkingLotService parkingLotService;
    
    @DeleteMapping("/parkinglots/{parkingLotID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "parkingLotID") int id){
        this.parkingLotService.deleteParkingLot(id);
    }
    @GetMapping("/parkinglots")
    @CrossOrigin
    public ResponseEntity<List<ParkingLot>> list(@RequestParam Map<String, String> params){
        List<ParkingLot> parkingLots = this.parkingLotService.getParkingLots(params);
        return new ResponseEntity<>(parkingLots, HttpStatus.OK);
    }
    @GetMapping("/parkinglots/{parkingLotID}")
    @CrossOrigin
    public ResponseEntity<ParkingLot> getParkingLot(@PathVariable("parkingLotID") int id){
        ParkingLot p = this.parkingLotService.getParkingLotById(id);
        return new ResponseEntity<>(p,HttpStatus.OK);
    }
    
}
