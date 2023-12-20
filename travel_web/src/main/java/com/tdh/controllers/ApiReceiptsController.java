/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.controllers;

import com.tdh.pojo.Customers;
import com.tdh.pojo.Receipts;
import com.tdh.service.CustomerService;
import com.tdh.service.ReceiptsService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api")
public class ApiReceiptsController {
    @Autowired
    private CustomerService cusService;
    @Autowired
    private ReceiptsService receiptsService;
    
    @GetMapping("/customer/{userId}")
    @CrossOrigin
    public ResponseEntity<Customers> getCustomerByUserId(@PathVariable int userId) {
        Customers customer = cusService.getCustomerByUserId(userId);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/addReceipts")
    @CrossOrigin
    public ResponseEntity<Receipts> addUser(@RequestParam Map<String, String> params) {
        Receipts r = this.receiptsService.addReceipts(params);
        return new ResponseEntity<>(r, HttpStatus.CREATED);
    }
}
