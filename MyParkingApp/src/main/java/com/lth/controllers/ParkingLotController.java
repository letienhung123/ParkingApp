/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.controllers;

import com.lth.pojo.ParkingLot;
import com.lth.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author lth7p
 */
@Controller
public class ParkingLotController {
    @Autowired
    private ParkingLotService parkingLotService;
    
    @GetMapping("/parkinglots")
    public String list(Model model){
        model.addAttribute("parkinglot", new ParkingLot());
        return "parkinglots";
    }
    @PostMapping("/parkinglots")
    public String add(@ModelAttribute(value = "parkinglot")ParkingLot parkingLot){ //hứng đối tượng từ client
        if(parkingLotService.addOrUpdateParkingLot(parkingLot)==true)
            return "redirect:/";
        return "parkinglots";
    }
    @GetMapping("/parkinglots/{parkingLotID}")
    public String update(Model model,@PathVariable(value = "parkingLotID") int id){
        model.addAttribute("parkinglot", this.parkingLotService.getParkingLotById(id));
        return "parkinglots";
    }
}
