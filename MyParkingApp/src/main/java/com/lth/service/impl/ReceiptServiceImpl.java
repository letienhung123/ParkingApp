/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.service.impl;

import com.lth.pojo.ReservationTemp;
import com.lth.repository.ReceiptRepository;
import com.lth.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lth7p
 */
@Service
public class ReceiptServiceImpl implements ReceiptService{
    @Autowired
    private ReceiptRepository receiptRepo;

    @Override
    public boolean addReceipt(ReservationTemp r) {
        return this.receiptRepo.addReceipt(r);
    }
    
}
