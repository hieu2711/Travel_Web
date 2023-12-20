/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.repository.impl;

import com.tdh.pojo.Customers;
import com.tdh.pojo.News;
import com.tdh.pojo.Prices;
import com.tdh.pojo.Users;
import com.tdh.repository.AccountRepository;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
public class AccountRepositoryImpl implements AccountRepository{
    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @Override
    public List<Users> getUser() {
        Session session = this.factory.getObject().getCurrentSession();
          Query q = session.createQuery("FROM Users");
          return q.getResultList();
    }

    @Override
    public Users getUserById(int id) {
         Session s = this.factory.getObject().getCurrentSession();
        return s.get(Users.class, id);
    }

    @Override
    public boolean addOrUpdateUser(Users c) {
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
    public boolean deleteUser(int id) {
       Session s = this.factory.getObject().getCurrentSession();
        try {
            Users c = this.getUserById(id);
            s.delete(c);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Users getUserByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From Users Where username=:un");
        q.setParameter("un", username);
        
        return (Users) q.getSingleResult();
    }
    
    @Override
    public boolean authUser(String username, String password) {
        Users  u = this.getUserByUsername(username);
        
        return this.passEncoder.matches(password, u.getPassword());
    }
    @Override
    public Users addUser(Users u) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(u);
        
        return u;
    }

    @Override
    public Customers addCustomer(Customers cus) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(cus);
        return cus;
    }

    @Override
    public Boolean existsByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("select u from Users u where u.username =:username");
        q.setParameter("username", username);
        q.setMaxResults(1);
        Users user = null;
        try {
            user = (Users) q.getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }
}
