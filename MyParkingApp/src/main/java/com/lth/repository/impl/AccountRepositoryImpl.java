/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.repository.impl;

import com.lth.pojo.Account;
import com.lth.repository.AccountRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lth7p
 */
@Repository
@Transactional
public class AccountRepositoryImpl implements AccountRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @Override
    public Account getAccountByUsername(String username) {
        Session session = this.factory.getObject().getCurrentSession();
        Query q = session.createQuery("FROM Account WHERE username = :un");
        q.setParameter("un", username);

        return (Account) q.getSingleResult();
    }
    @Override
    public boolean authAccount(String username, String password) {
        Account  u = this.getAccountByUsername(username);   
        return this.passEncoder.matches(password, u.getPassword());
    }
    
    @Override
    public Account addAccount(Account u) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(u);
        
        return u;
    }
}
