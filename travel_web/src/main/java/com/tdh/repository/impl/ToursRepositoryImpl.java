/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.repository.impl;

import com.tdh.dto.ToursDto;
import com.tdh.pojo.Prices;
import com.tdh.pojo.TourCategory;
import com.tdh.pojo.Tours;
import com.tdh.repository.ToursRepository;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
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
@PropertySource("classpath:configs.properties")
@Transactional
public class ToursRepositoryImpl implements ToursRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;

    @Override
    public List<Tours> getTours(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Tours> q = b.createQuery(Tours.class);
        Root root = q.from(Tours.class);
        q.select(root);
        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(root.get("name"), String.format("%%%s%%", kw)));
            }

            String fromPrice = params.get("fromPrice");
            String toPrice = params.get("toPrice");
            if (fromPrice != null && !fromPrice.isEmpty() && toPrice != null && !toPrice.isEmpty()) {
                Join<Tours, Prices> priceJoin = root.join("priceId", JoinType.INNER);
                predicates.add(b.between(priceJoin.get("priceAdult"), Long.parseLong(fromPrice), Long.parseLong(toPrice)));
            }

//            String timeStart = params.get("timeStart");
//            String timeEnd = params.get("timeEnd");
//            if (timeStart != null && !timeStart.isEmpty() && timeEnd != null && !timeEnd.isEmpty()) {
//                predicates.add(b.between(root.get("timeStart"),
//                        Timestamp.valueOf(timeStart), Timestamp.valueOf(timeEnd)));
//            }
            // Sử dụng SimpleDateFormat để chuyển đổi chuỗi thành Timestamp
            String timeStart = params.get("timeStart");
            String timeEnd = params.get("timeEnd");
            if (timeStart != null && !timeStart.isEmpty() && timeEnd != null && !timeEnd.isEmpty()) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                try {
                    Date parsedDate = dateFormat.parse(timeStart);
                    Timestamp startTimestamp = new Timestamp(parsedDate.getTime());
                    parsedDate = dateFormat.parse(timeEnd);
                    Timestamp endTimestamp = new Timestamp(parsedDate.getTime());
                    // Tiếp theo, sử dụng startTimestamp và endTimestamp trong câu truy vấn của bạn
                    predicates.add(b.between(root.get("timeStart"), startTimestamp, endTimestamp));
                } catch (ParseException e) {
                    // Xử lý lỗi khi không thể chuyển đổi thời gian
                    e.printStackTrace(); // Hoặc xử lý lỗi theo cách bạn muốn
                }
            } else {
                // Xử lý khi không có dữ liệu thời gian
            }

            String cateId = params.get("cateId");
            if (cateId != null && !cateId.isEmpty()) {
                predicates.add(b.equal(root.get("tourCate"), Integer.parseInt(cateId)));
            }

            q.where(predicates.toArray(Predicate[]::new));
        }

//        q.orderBy(b.desc(root.get("id")));
        Query query = session.createQuery(q);

        if (params != null) {
            String page = params.get("page");
            if (page != null && !page.isEmpty()) {
                int p = Integer.parseInt(page);
                int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));

                query.setMaxResults(pageSize);
                query.setFirstResult((p - 1) * pageSize);
            }
        }
        return query.getResultList();

    }

    @Override
    public Long countProduct() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT Count(*) FROM Tours");

        return Long.parseLong(q.getSingleResult().toString());
    }

    @Override
    public boolean addOrUpdateTours(Tours p) {
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
    public Tours getToursById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Tours.class, id);
    }

    @Override
    public boolean deleteTours(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            Tours p = this.getToursById(id);
            s.delete(p);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

//    public List<Tours> getTours(Map<String, String> params) {
//        Session session = this.factory.getObject().getCurrentSession();
//        StringBuilder sql = new StringBuilder();
//        sql.append("SELECT t.* ");
//        sql.append("FROM tours t ");
//        sql.append("INNER JOIN prices p ON t.id = p.tour_id ");
//
//        if (params != null) {
//            sql.append("WHERE 1 = 1 ");
//
//            String kw = params.get("kw");
//            if (kw != null && !kw.isEmpty()) {
//                sql.append("AND t.name LIKE :kw ");
//            }
//
//            String fromPrice = params.get("fromPrice");
//            if (fromPrice != null && !fromPrice.isEmpty()) {
//                sql.append("AND t.price >= :fromPrice ");
//            }
//
//            String toPrice = params.get("toPrice");
//            if (toPrice != null && !toPrice.isEmpty()) {
//                sql.append("AND t.price_tours <= :toPrice ");
//            }
//
//            String cateId = params.get("cateId");
//            if (cateId != null && !cateId.isEmpty()) {
//                sql.append("AND t.tourCate = :cateId ");
//            }
//        }
//
//        Query query = session.createSQLQuery(sql.toString()).addEntity(Tours.class);
//
//        if (params != null) {
//            String kw = params.get("kw");
//            if (kw != null && !kw.isEmpty()) {
//                query.setParameter("kw", "%" + kw + "%");
//            }
//
//            String fromPrice = params.get("fromPrice");
//            if (fromPrice != null && !fromPrice.isEmpty()) {
//                query.setParameter("fromPrice", Double.parseDouble(fromPrice));
//            }
//
//            String toPrice = params.get("toPrice");
//            if (toPrice != null && !toPrice.isEmpty()) {
//                query.setParameter("toPrice", Double.parseDouble(toPrice));
//            }
//
//            String cateId = params.get("cateId");
//            if (cateId != null && !cateId.isEmpty()) {
//                query.setParameter("cateId", Integer.parseInt(cateId));
//            }
//        }
//
//        return query.getResultList();
//    }
//
//}
    @Override
    public List<Tours> getListTours() {
        Session session = this.factory.getObject().getCurrentSession();
        Query q = session.createQuery("FROM Tours");
        return q.getResultList();
    }
}
