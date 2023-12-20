/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.service.impl;

import com.tdh.pojo.Rating;
import com.tdh.pojo.Users;
import com.tdh.repository.AccountRepository;
import com.tdh.repository.RatingRepository;
import com.tdh.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class RatingServiceImpl implements RatingService{
    @Autowired
    private RatingRepository ratingRepo;
    @Autowired
    private AccountRepository userRepo;
    @Override
    public double getRatingAverage(int productId) {
      return this.ratingRepo.getRatingAverage(productId);
    }

    @Override
    public Rating addRating(Rating c) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users u = this.userRepo.getUserByUsername(authentication.getName());
        c.setUserId(u);
        return this.ratingRepo.addRating(c);
    }
    
}
