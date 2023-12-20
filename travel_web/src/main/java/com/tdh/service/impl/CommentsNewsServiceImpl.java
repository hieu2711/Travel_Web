/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.service.impl;

import com.tdh.pojo.ConmentsNews;
import com.tdh.pojo.Users;
import com.tdh.repository.AccountRepository;
import com.tdh.repository.CommentsNewsRepository;
import com.tdh.service.CommentsNewsService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class CommentsNewsServiceImpl implements CommentsNewsService{
    
    @Autowired
    private CommentsNewsRepository commentsNewsRepo;
    @Autowired
    private AccountRepository userRepo;
    
    @Override
    public List<ConmentsNews> getComments(int productId) {
       return this.commentsNewsRepo.getComments(productId);
    }

    @Override
    public ConmentsNews addComment(ConmentsNews c) {
        c.setCreatedDate(new Date());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users u = this.userRepo.getUserByUsername(authentication.getName());
        c.setUserId(u);
        return this.commentsNewsRepo.addComment(c);
    }
    
}
