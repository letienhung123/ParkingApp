/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.repository.impl;

import com.lth.pojo.ParkingLot;
import com.lth.pojo.ParkingSpot;
import com.lth.pojo.Reservation;
import com.lth.pojo.Spot;
import com.lth.repository.ParkingSpotRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
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
            if (ps.getSpotID() == null) {
                ps.setStatus("Empty");
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

    @Override
    public List<ParkingSpot> findAllParkingSpots() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM ParkingSpot", ParkingSpot.class);
        return q.getResultList();
    }

    @Override
    public void updateSpotInUse(int id) {
        ParkingSpot sp = this.getParkingSpotById(id);
        sp.setStatus("In Use");
        this.addOrUpdateParkingSpot(sp);
    }

    @Override
    public void updateSpotEmpty(int id) {
        ParkingSpot sp = this.getParkingSpotById(id);
        sp.setStatus("Empty");
        this.addOrUpdateParkingSpot(sp);
    }

    @Override
    public int countParkingSpotByLotID(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(*) FROM ParkingSpot p WHERE p.parkingLotID.parkingLotID = :parkingLotID");
        q.setParameter("parkingLotID", id);
        return Integer.parseInt(q.getSingleResult().toString());
    }

}
