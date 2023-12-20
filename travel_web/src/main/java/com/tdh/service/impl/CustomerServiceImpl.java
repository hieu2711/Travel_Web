/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.service.impl;

import com.tdh.pojo.Customers;
import com.tdh.repository.CustomerRepository;
import com.tdh.service.CustomerService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository cusRepo;
    @Override
    public Customers addCustomer(Map<String, String> params) {
        Customers c = new Customers();
        c.setName(params.get("name"));
        c.setIdentification(Integer.parseInt(params.get("identification")));
        c.setEmail(params.get("email"));
        c.setPhonenumber(Integer.parseInt(params.get("phoneNumber")));
        c.setAddress(params.get("address"));
        c.setSex(Integer.parseInt(params.get("sex")));
        this.cusRepo.addCustomer(c);
        return c;
    }

    @Override
    public Customers getCustomerByUserId(int id) {
       return this.cusRepo.getCustomerByUserId(id);
    }

}
