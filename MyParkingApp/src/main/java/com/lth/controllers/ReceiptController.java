/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.controllers;

import com.lth.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author lth7p
 */
@Controller
public class ReceiptController {
    @Autowired
    private ReceiptService receiptService;
    
    @RequestMapping("/parkinglots/{id}/receipt")
    public String getReceipt(Model model,@PathVariable("id") int id){
        model.addAttribute("getReceipt", this.receiptService.getAllReservationsByParkingLotID(id));
        return "receipt";
    }
}
