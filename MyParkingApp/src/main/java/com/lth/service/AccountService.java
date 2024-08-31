/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lth.service;

import com.lth.pojo.Account;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author lth7p
 */
public interface AccountService extends UserDetailsService{
    Account getAccountByUsername(String username);
    boolean authAccount(String username, String password);
    Account addAccount(Map<String, String> params, MultipartFile avatar);
}
