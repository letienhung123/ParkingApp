/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.repository.impl;

import com.lth.pojo.Vehicle;
import com.lth.repository.VehicleRepository;
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
public class VehicleRepositoryImpl implements VehicleRepository{

    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public Vehicle addVehicle(Vehicle v) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(v);
        return v;
    }
    
}
