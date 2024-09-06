/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.controllers;

import com.lth.components.DateUtils;
import com.lth.pojo.ParkingLot;
import com.lth.pojo.ParkingSpot;
import com.lth.pojo.Reservation;
import com.lth.service.ParkingLotService;
import com.lth.service.ParkingSpotService;
import com.lth.service.ReceiptService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author lth7p
 */
@Controller
public class ParkingSpotController {

    @Autowired
    private ParkingSpotService parkingSpotService;
    @Autowired
    private ParkingLotService parkingLotService;
    @Autowired
    private ReceiptService receiptService;

    @RequestMapping("/parkinglots/{id}/parkingspots")
    public String getParkingSpots(Model model, @PathVariable("id") int id) {
        Date now = new Date();

        model.addAttribute("parkingspots", this.parkingSpotService.getSpotsByParkingLotId(id));
        model.addAttribute("selectedParkingLot", this.parkingLotService.getParkingLotById(id));
        model.addAttribute("reservationsByLotID", this.receiptService.getWaitingReservations(id));
        model.addAttribute("now", now);

        List<String> timeRemainingMessages = new ArrayList<>();
        for (Reservation r : this.receiptService.getWaitingReservations(id)) {
            String message = DateUtils.getTimeRemainingMessage(now, r.getStartTime(), r.getEndTime());
            timeRemainingMessages.add(message);
        }

        model.addAttribute("timeRemainingMessages", timeRemainingMessages);
        return "parkingspots";
    }

    @GetMapping("/parkinglots/{parkingLotID}/spotcreating")
    public String list(Model model, @PathVariable("parkingLotID") int id) {
        model.addAttribute("parkingspot", new ParkingSpot());
        model.addAttribute("idcurr", id);
        return "spotcreating";
    }

    @PostMapping("/parkinglots/{parkingLotID}/spotcreating")
    public String addParkingSpot(@PathVariable("parkingLotID") int id,
            @RequestParam("level") int level,
            @RequestParam("spotNumber") int num) {
        int a =0;

        ParkingLot parkingLot = parkingLotService.getParkingLotById(id);
        for (int i = 1; i <= level; i++) {
            for (int j = 1; j <= num; j++) {
                ParkingSpot ps = new ParkingSpot();
                ps.setLevel(i);
                ps.setSpotNumber(j);
                ps.setParkingLotID(parkingLot);
                parkingSpotService.addOrUpdateParkingSpot(ps);
                a +=1;
            }
            parkingLot.setTotalSpots(a);
        }     
        return "redirect:/";
    }

}
