/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.service.impl;

import com.tdh.pojo.Receipts;
import com.tdh.pojo.Tours;
import com.tdh.pojo.Users;
import com.tdh.repository.AccountRepository;
import com.tdh.repository.ReceiptsRepository;
import com.tdh.repository.ToursRepository;
import com.tdh.service.ReceiptsService;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class ReceiptsServiceImpl implements ReceiptsService {

    @Autowired
    private AccountRepository userRepo;
    @Autowired
    private ReceiptsRepository receiptsRepo;
    @Autowired
    private ToursRepository toursRepo;

    @Override
    public Receipts addReceipts(Map<String, String> params) {
        Receipts r = new Receipts();
        r.setPaymentsDate(new Date());
        r.setPaymentsMethod(1);
        r.setAmountPrice(Long.parseLong(params.get("amountPrice")));
        r.setAdult(Integer.parseInt(params.get("adult")));
        r.setChild(Integer.parseInt(params.get("child")));
        Tours t = this.toursRepo.getToursById(Integer.parseInt(params.get("tourId")));
        r.setTourId(t);
        Users u = this.userRepo.getUserById(Integer.parseInt(params.get("userId")));
        r.setUserId(u);
        return this.receiptsRepo.addReceipts(r);
    }

}
