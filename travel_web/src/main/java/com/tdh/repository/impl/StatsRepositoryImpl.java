/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.repository.impl;

import com.tdh.pojo.Receipts;
import com.tdh.pojo.TourCategory;
import com.tdh.pojo.Tours;
import com.tdh.repository.StatsRepository;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
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
public class StatsRepositoryImpl implements StatsRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private SimpleDateFormat f;

    @Override
    public List<Object[]> countToursByCates() {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root rP = q.from(Tours.class);
        Root rC = q.from(TourCategory.class);

        q.multiselect(rC.get("id"), rC.get("name"), b.count(rP.get("id")));

        q.where(b.equal(rP.get("category"), rC.get("id")));
        q.groupBy(rC.get("id"));

        Query query = session.createQuery(q);
        return query.getResultList();
    }

//    @Override
//    public List<Object[]> statsRevenue(Map<String, String> params) {
//        Session session = this.factory.getObject().getCurrentSession();
//        CriteriaBuilder b = session.getCriteriaBuilder();
//        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
//        Root rP = q.from(Tours.class);
//        Root rD = q.from(OrderDetail.class);
//        Root rO = q.from(SaleOrder.class);
//
//        q.multiselect(rP.get("id"), rP.get("name"), b.sum(b.prod(rD.get("unitPrice"), rD.get("num"))));
//
//        List<Predicate> predicates = new ArrayList<>();
//        predicates.add(b.equal(rD.get("productId"), rP.get("id")));
//        predicates.add(b.equal(rD.get("orderId"), rO.get("id")));
//
//        String fd = params.get("fromDate");
//        if (fd != null && !fd.isEmpty()) {
//            try {
//                predicates.add(b.greaterThanOrEqualTo(rO.get("createdDate"), f.parse(fd)));
//            } catch (ParseException ex) {
//                Logger.getLogger(StatsRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
//        String td = params.get("toDate");
//        if (td != null && !td.isEmpty()) {
//            try {
//                predicates.add(b.lessThanOrEqualTo(rO.get("createdDate"), f.parse(td)));
//            } catch (ParseException ex) {
//                Logger.getLogger(StatsRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
//        String quarter = params.get("quarter");
//        if (quarter != null && !quarter.isEmpty()) {
//            String year = params.get("year");
//            if (year != null && !year.isEmpty()) {
//                predicates.addAll(Arrays.asList(
//                        b.equal(b.function("YEAR", Integer.class, rO.get("createdDate")), Integer.parseInt(year)),
//                        b.equal(b.function("QUARTER", Integer.class, rO.get("createdDate")), Integer.parseInt(quarter))
//                ));
//            }
//        }
//
//        q.where(predicates.toArray(Predicate[]::new));
//
//        q.groupBy(rP.get("id"));
//
//        Query query = session.createQuery(q);
//        return query.getResultList();
//
//    }
    @Override
    public int[] statsRevenue(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();

        String sql = "SELECT "
                + "    MONTH(r.payments_date) AS month, "
                + "    SUM((p.price_adult * r.adult) + (p.price_child * r.child)) AS total_revenue "
                + "FROM "
                + "    receipts r "
                + "JOIN "
                + "    tours t ON r.tour_id = t.id "
                + "JOIN "
                + "    prices p ON t.price_id = p.id "
                + "WHERE "
                + "    YEAR(r.payments_date) = :year "
                + "GROUP BY "
                + "    MONTH(r.payments_date) "
                + "ORDER BY "
                + "    month ";

        Query query = session.createNativeQuery(sql);
        String yearParam = params.get("year");
        if (yearParam != null) {
            Integer year = Integer.valueOf(yearParam);
            query.setParameter("year", year);
        } else {
           query.setParameter("year", 0);
        }

        List<Object[]> resultList = query.getResultList();
        int[] totalSoldToursArray = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            Object[] result = resultList.get(i);
            int totalSoldTours = ((Number) result[1]).intValue();
            totalSoldToursArray[i] = totalSoldTours;
        }
        return totalSoldToursArray;
    }

    @Override
    public int[] statsSale(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();

        String sql = "SELECT "
                + "    DATE_FORMAT(payments_date, '%Y-%m') AS month, "
                + "    COUNT(*) AS totalSoldTours "
                + "FROM receipts "
                + "WHERE YEAR(payments_date) = :year " 
                + "GROUP BY month ORDER BY month";

        Query query = session.createNativeQuery(sql);
        String yearParam = params.get("year");
        if (yearParam != null) {
            Integer year = Integer.valueOf(yearParam);
            query.setParameter("year", year);
        } else {
           query.setParameter("year", 0);
        }
        List<Object[]> resultList = query.getResultList();
        int[] totalSoldToursArray = new int[resultList.size()];

        for (int i = 0; i < resultList.size(); i++) {
            Object[] result = resultList.get(i);
            int totalSoldTours = ((Number) result[1]).intValue();
            totalSoldToursArray[i] = totalSoldTours;
        }
        return totalSoldToursArray;
    }
}
