/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.service.impl;

import com.lth.pojo.UserDetail;
import com.lth.repository.UserDetailRepository;
import com.lth.service.UserDetailService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author lth7p
 */
@Service
public class UserDetailServiceImpl implements UserDetailService{
    @Autowired
    private UserDetailRepository userRepo;

    @Override
    public UserDetail findByUsername(String username) {
        return this.userRepo.findByUsername(username);
    }
    
    
}
