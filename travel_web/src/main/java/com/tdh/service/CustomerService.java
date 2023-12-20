/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tdh.service;

import com.tdh.pojo.Customers;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface CustomerService {
      Customers addCustomer(Map<String, String> params);
      Customers getCustomerByUserId(int id);
}
