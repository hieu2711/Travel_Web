/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.repository.impl;

import com.tdh.pojo.Receipts;
import com.tdh.repository.ReceiptsRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
public class ReceiptsRepositoryImpl implements ReceiptsRepository{
    @Autowired
    private LocalSessionFactoryBean factory;
    @Override
    public Receipts addReceipts(Receipts r) {
       Session s = this.factory.getObject().getCurrentSession();
        try {
            s.save(r);
            return r;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
}
