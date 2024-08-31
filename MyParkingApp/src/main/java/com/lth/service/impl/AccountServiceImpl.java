/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.lth.pojo.Account;
import com.lth.repository.AccountRepository;
import com.lth.service.AccountService;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author lth7p
 */
@Service("userDetailService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private BCryptPasswordEncoder passEncoder;
     @Autowired
     private Cloudinary cloudinary;


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

    @Override
    public Account getAccountByUsername(String username) {
        return this.accountRepo.getAccountByUsername(username);
    }

    @Override
    public boolean authAccount(String username, String password) {
        return this.accountRepo.authAccount(username, password);
    }

    @Override
    public Account addAccount(Map<String, String> params, MultipartFile avatar) {
        Account a = new Account();
        a.setFirstName(params.get("firstName"));
        a.setLastName(params.get("lastName"));
        a.setPhoneNumber(params.getOrDefault("phone", "9999999999"));
        a.setEmail(params.getOrDefault("email", "a@gmail.com"));
        a.setUsername(params.get("username"));
        a.setPassword(this.passEncoder.encode(params.get("password")));
        a.setRole("ROLE_USER");
        if (!avatar.isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(avatar.getBytes(), 
                        ObjectUtils.asMap("resource_type", "auto"));
                a.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(AccountServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        this.accountRepo.addAccount(a);
        return a;
    }

}
