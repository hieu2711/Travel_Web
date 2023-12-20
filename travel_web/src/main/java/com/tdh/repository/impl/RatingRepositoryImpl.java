/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.repository.impl;

import com.tdh.pojo.Rating;
import com.tdh.repository.RatingRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
public class RatingRepositoryImpl implements RatingRepository{
    
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private LocalSessionFactoryBean factory;
    @Override
    public double getRatingAverage(int productId) {
        List<Integer> ratings = entityManager
            .createQuery("SELECT r.rating FROM Rating r WHERE r.tourId.id = :tourId", Integer.class)
            .setParameter("tourId", productId)
            .getResultList();

        int totalRating = 0;
        for (Integer rating : ratings) {
            totalRating += rating;
        }

        if (!ratings.isEmpty()) {
            return (double) totalRating / ratings.size();
        } else {
            return 0.0;
        }
    }

    @Override
    public Rating addRating(Rating c) {
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
