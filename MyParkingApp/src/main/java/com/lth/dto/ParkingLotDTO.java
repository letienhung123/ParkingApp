/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.dto;

import com.lth.pojo.ParkingLot;
import lombok.Data;

/**
 *
 * @author lth7p
 */
@Data
public class ParkingLotDTO {
    private ParkingLot parkingLot;
    private long emptySpots;

    public ParkingLotDTO(ParkingLot lot, long emptySpots) {
        this.parkingLot = lot;
        this.emptySpots = emptySpots;
    }
}
