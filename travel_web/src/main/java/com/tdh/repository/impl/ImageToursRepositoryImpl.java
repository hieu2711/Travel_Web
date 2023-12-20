/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.repository.impl;

import com.tdh.pojo.TourImages;
import com.tdh.repository.ImageToursRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
public class ImageToursRepositoryImpl implements ImageToursRepository{

     @Autowired
    private LocalSessionFactoryBean factory;
    @Override
    public List<TourImages> getImageTours() {
        Session session = this.factory.getObject().getCurrentSession();
          Query q = session.createQuery("FROM TourImages");
          return q.getResultList();
    }

    @Override
    public TourImages getToursImgagesById(int id) {
         Session s = this.factory.getObject().getCurrentSession();
        return s.get(TourImages.class, id);
    }

    @Override
    public boolean addOrUpdateToursImages(TourImages c) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (c.getId() == null) {
                s.save(c);
            } else {
                s.update(c);
            }
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteToursImgages(int id) {
       Session s = this.factory.getObject().getCurrentSession();
        try {
            TourImages c = this.getToursImgagesById(id);
            s.delete(c);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<TourImages> getTourImagesByToursId(int toursId) {
    Session session = this.factory.getObject().getCurrentSession();
    CriteriaBuilder builder = session.getCriteriaBuilder();
    CriteriaQuery<TourImages> query = builder.createQuery(TourImages.class);
    Root<TourImages> root = query.from(TourImages.class);
    query.select(root);
    query.where(builder.equal(root.get("toursId"), toursId));
    List<TourImages> results = session.createQuery(query).getResultList();
    return results;
}







    
}
