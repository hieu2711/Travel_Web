/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.repository.impl;

import com.tdh.pojo.Customers;
import com.tdh.repository.CustomerRepository;
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
public class CustomerRepositoryImpl implements CustomerRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Customers addCustomer(Customers c) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(c);
        return c;
    }

    @Override
    public Customers getCustomerByUserId(int userId) {
        Session session = this.factory.getObject().getCurrentSession();
        String hqlQuery = "FROM Customers c WHERE c.userId.id = :userId";
        return (Customers) session.createQuery(hqlQuery)
                .setParameter("userId", userId)
                .uniqueResult();
    }

}
