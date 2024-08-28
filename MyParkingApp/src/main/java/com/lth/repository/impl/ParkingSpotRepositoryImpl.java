/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.repository.impl;

import com.lth.pojo.ParkingLot;
import com.lth.pojo.ParkingSpot;
import com.lth.repository.ParkingSpotRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lth7p
 */
@Repository
@Transactional
public class ParkingSpotRepositoryImpl implements ParkingSpotRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<ParkingSpot> getSpotsByParkingLotId(int id) {
        Session s = this.factory.getObject().getCurrentSession();
    ParkingLot parkingLot = s.get(ParkingLot.class, id);

    if (parkingLot != null) {
        List<ParkingSpot> spots = new ArrayList<>(parkingLot.getParkingSpotSet());
        spots.sort(Comparator.comparingInt(ParkingSpot::getSpotID));
        return spots;
    }
    return new ArrayList<>();
    }

    @Override
    public boolean addOrUpdateParkingSpot(ParkingSpot ps) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (ps.getSpotID()== null) {
                s.save(ps);
            } else {
                s.update(ps);
            }
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public ParkingSpot getParkingSpotById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(ParkingSpot.class, id);
    }

}
