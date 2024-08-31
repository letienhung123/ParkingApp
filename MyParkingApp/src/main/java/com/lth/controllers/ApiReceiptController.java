/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.controllers;

import com.lth.pojo.ReservationTemp;
import com.lth.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lth7p
 */
@RestController
@RequestMapping("/api")
public class ApiReceiptController {
    @Autowired
    private ReceiptService receiptService;
    
    @PostMapping("/pay")
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    public void addReceipt(@RequestBody ReservationTemp r){
        this.receiptService.addReceipt(r);
    }
}
