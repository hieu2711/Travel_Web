/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tdh.repository;

import com.tdh.pojo.Customers;

/**
 *
 * @author Admin
 */
public interface CustomerRepository {
    Customers addCustomer(Customers cus);
    Customers getCustomerByUserId(int id);
}
