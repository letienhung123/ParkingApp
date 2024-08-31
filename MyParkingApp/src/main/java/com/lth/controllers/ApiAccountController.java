/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.controllers;

import com.lth.components.JwtService;
import com.lth.pojo.Account;
import com.lth.service.AccountService;
import java.security.Principal;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author lth7p
 */
@RestController
@RequestMapping("/api")
public class ApiAccountController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AccountService accService;
    
    @PostMapping("/login/")
    @CrossOrigin
    public ResponseEntity<String> login(@RequestBody Account acc) {
        if (this.accService.authAccount(acc.getUsername(), acc.getPassword()) == true) {
            String token = this.jwtService.generateTokenLogin(acc.getUsername());
            
            return new ResponseEntity<>(token, HttpStatus.OK);
        }

        return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
    }
    @PostMapping(path = "/accounts", 
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, 
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin
    public ResponseEntity<Account> addAccount(@RequestParam Map<String, String> params, @RequestPart MultipartFile avatar) {
        Account acc = this.accService.addAccount(params, avatar);
        return new ResponseEntity<>(acc, HttpStatus.CREATED);
    }
    
    @GetMapping(path = "/current-user", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Account> details(Principal user) {
        Account acc = this.accService.getAccountByUsername(user.getName());
        return new ResponseEntity<>(acc, HttpStatus.OK);
    }
}
