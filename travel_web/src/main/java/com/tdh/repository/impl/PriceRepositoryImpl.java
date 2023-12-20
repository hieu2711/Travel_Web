/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.repository.impl;

import com.tdh.pojo.Prices;
import com.tdh.repository.PriceRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
public class PriceRepositoryImpl implements PriceRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Prices> getPrice() {
         Session session = this.factory.getObject().getCurrentSession();
          Query q = session.createQuery("FROM Prices Where isDelete = 0");
          return q.getResultList();
    }

    @Override
    public boolean addOrUpdatePriceTours(Prices p) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (p.getId() == null) {
                s.save(p);
            } else {
                s.update(p);
            }
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Prices getPriceById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Prices.class, id);
    }

    @Override
    public boolean deletePrice(int id) {
          Session s = this.factory.getObject().getCurrentSession();
        Prices c = this.getPriceById(id);
        c.setIsDelete(Boolean.TRUE);
        try {
            s.update(c);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
