/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lth.repository;

import com.lth.pojo.Account;

/**
 *
 * @author lth7p
 */
public interface AccountRepository {
    Account getAccountByUsername(String username);
    boolean authAccount(String username, String password);
    Account addAccount(Account u);
    Account getAccountByReservationID(Integer reservationID);
}
