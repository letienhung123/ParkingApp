/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.pojo;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author lth7p
 */
@Data
public class ReservationTemp {
    private Date startTime;
    private Date endTime;
    private String reservationStatus;
    private BigDecimal totalPrice;
    private int parkingSpotId;
    
}
