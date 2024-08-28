/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lth.repository;

import com.lth.pojo.UserDetail;
/**
 *
 * @author lth7p
 */
public interface UserDetailRepository {
    UserDetail findByUsername(String username);
}
