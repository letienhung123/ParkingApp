/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lth.service;

import com.lth.pojo.UserDetail;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author lth7p
 */
public interface UserDetailService{
    UserDetail findByUsername(String username);
}
