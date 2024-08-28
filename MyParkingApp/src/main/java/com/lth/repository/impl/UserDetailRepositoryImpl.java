/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.repository.impl;

import com.lth.pojo.UserDetail;
import com.lth.repository.UserDetailRepository;
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
public class UserDetailRepositoryImpl implements UserDetailRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public UserDetail findByUsername(String username) {
        Session session = this.factory.getObject().getCurrentSession();
        Query q = session.createQuery("SELECT u FROM UserDetail u WHERE u.accountID.username = :un");
        q.setParameter("un", username);

        return (UserDetail) q.getSingleResult();
    }
    
}
