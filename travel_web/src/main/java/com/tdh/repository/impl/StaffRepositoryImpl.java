/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.repository.impl;

import com.tdh.dto.StaffDto;
import com.tdh.pojo.Staffs;
import com.tdh.pojo.Users;
import com.tdh.repository.StaffRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
public class StaffRepositoryImpl implements StaffRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public List<StaffDto> getStaff(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
        Root<Staffs> staffRoot = cq.from(Staffs.class);
        Join<Staffs, Users> userJoin = staffRoot.join("userId");

        cq.multiselect(
                staffRoot.get("id"),
                staffRoot.get("name"),
                staffRoot.get("phone"),
                staffRoot.get("email"),
                staffRoot.get("identification"),
                staffRoot.get("address"),
                staffRoot.get("birthday"),
                staffRoot.get("sex"),
                staffRoot.get("first"),
                userJoin.get("username"),
                userJoin.get("password"),
                userJoin.get("role"),
                userJoin.get("avatar"),
                userJoin.get("id")
        );

        String kw = params.get("kw");
        if (kw != null && !kw.isEmpty()) {
            Predicate keywordPredicate = cb.like(staffRoot.get("name"), "%" + kw + "%");
            cq.where(keywordPredicate);
        }

        List<Object[]> rows = session.createQuery(cq).getResultList();
        List<StaffDto> liststaff = new ArrayList<>();
        for (Object[] row : rows) {
            StaffDto staff = new StaffDto();
            staff.setId((Integer) row[0]);
            staff.setName((String) row[1]);
            staff.setPhone((String) row[2]);
            staff.setEmail((String) row[3]);
            staff.setIdentification((Integer) row[4]);
            staff.setAddress((String) row[5]);
            staff.setBirthday((Date) row[6]);
            staff.setSex((Integer) row[7]);
            staff.setFirst((Date) row[8]);
            staff.setUsername((String) row[9]);
            staff.setPassword((String) row[10]);
            staff.setRole((String) row[11]);
            staff.setAvatar((String) row[12]);
            staff.setId_user((Integer) row[13]);
            liststaff.add(staff);
            
            }
        return liststaff;
    }

    @Override
    public Long countStaff() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT Count(*) FROM Staffs");
        return Long.parseLong(q.getSingleResult().toString());
    }

    @Override
    public boolean addOrUpdateStaff(Staffs p) {
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
    public Staffs getStaffById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Staffs.class, id);
    }

    @Override
    public boolean deleteStaff(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            Staffs p = this.getStaffById(id);
            s.delete(p);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
