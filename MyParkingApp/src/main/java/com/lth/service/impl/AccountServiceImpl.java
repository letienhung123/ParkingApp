/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.service.impl;

import com.lth.pojo.Account;
import com.lth.repository.AccountRepository;
import com.lth.service.AccountService;
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
@Service("userDetailService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account c = this.accountRepo.getAccountByUsername(username);
        if (c == null) {
            throw new UsernameNotFoundException("Invalid");
        }
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(c.getRole()));
        return new org.springframework.security.core.userdetails.User(
                c.getUsername(), c.getPassword(), authorities);
    }

}
