/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.repository.impl;

import com.lth.pojo.AdminDetail;
import com.lth.repository.AdminRepository;
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
public class AdminRepositoryImpl implements AdminRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory; 
    
    @Override
    public AdminDetail addAdmin(AdminDetail a) {
        Session session = this.factory.getObject().getCurrentSession();
        session.save(a);
        return a;
    }
    
}
