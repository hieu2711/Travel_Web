/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tdh.repository;

import com.tdh.pojo.Customers;
import com.tdh.pojo.Users;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface AccountRepository {

    List<Users> getUser();

    Users getUserById(int id);

    boolean addOrUpdateUser(Users c);

    boolean deleteUser(int id);
    
    Users getUserByUsername(String username);
    
    Users addUser(Users user);
    Customers addCustomer(Customers cus);
    boolean authUser(String username, String password);
    Boolean existsByUsername(String username);
}
