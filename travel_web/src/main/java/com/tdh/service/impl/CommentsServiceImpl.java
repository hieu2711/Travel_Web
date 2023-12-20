/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.service.impl;

import com.tdh.pojo.Comments;
import com.tdh.pojo.Users;
import com.tdh.repository.AccountRepository;
import com.tdh.repository.CommentsRepository;
import com.tdh.service.CommentsService;
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
public class CommentsServiceImpl implements CommentsService{
    @Autowired
    private CommentsRepository commentRepo;
    @Autowired
    private AccountRepository userRepo;

    @Override
    public List<Comments> getComments(int productId) {
         return this.commentRepo.getComments(productId);
    }

    @Override
    public Comments addComment(Comments c) {
        c.setCreatedDate(new Date());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users u = this.userRepo.getUserByUsername(authentication.getName());
        c.setUserId(u);
        
        return this.commentRepo.addComment(c);
    }
}
