/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.repository.impl;

import com.tdh.pojo.News;
import com.tdh.pojo.TourCategory;
import com.tdh.repository.NewsRepository;
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
public class NewsRepositoryImpl implements NewsRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public List<News> getNews() {
       Session session = this.factory.getObject().getCurrentSession();
          Query q = session.createQuery("FROM News where isDelete = 0");
          return q.getResultList();
    }

    @Override
    public News getNewsById(int id) {
         Session s = this.factory.getObject().getCurrentSession();
        return s.get(News.class, id);
    }

    @Override
    public boolean addOrUpdateNews(News c) {
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
    public boolean deleteNews(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            News c = this.getNewsById(id);
             c.setIsDelete(Boolean.TRUE);
            s.update(c);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
}
