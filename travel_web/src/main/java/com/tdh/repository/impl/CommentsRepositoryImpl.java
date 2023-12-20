/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.repository.impl;

import com.tdh.pojo.Comments;
import com.tdh.repository.CommentsRepository;
import java.util.List;
import javax.persistence.Query;
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
public class CommentsRepositoryImpl implements CommentsRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Comments> getComments(int productId) {
         Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From Comments Where tourId.id=:id");
        q.setParameter("id", productId);
        return q.getResultList();
    }

    @Override
    public Comments addComment(Comments c) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.save(c);
            return c;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }  
}
