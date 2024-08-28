/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.controllers;

import com.lth.service.ParkingLotService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author lth7p
 */
@Controller
@ControllerAdvice
public class IndexController {
    @Autowired
    private ParkingLotService parkingLotService;
    
    @RequestMapping("/")
    public String index(Model model,@RequestParam Map<String, String> params){
        
        model.addAttribute("parkinglots", this.parkingLotService.getParkingLots(params));
        return "index";
    }
    
}
