package com.lth.repository.impl;

import com.lth.pojo.ParkingLot;
import com.lth.repository.ParkingLotRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author lth7p
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class ParkingLotRepositoryImpl implements ParkingLotRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;

    @Override
    public List<ParkingLot> getParkingLots(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<ParkingLot> q = b.createQuery(ParkingLot.class);
        Root root = q.from(ParkingLot.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(root.get("name"), String.format("%%%s%%", kw)));
            }

            String fromPrice = params.get("fromPrice");
            if (fromPrice != null && !fromPrice.isEmpty()) {
                predicates.add(b.greaterThanOrEqualTo(root.get("pricePerHour"), Double.parseDouble(fromPrice)));
            }

            String toPrice = params.get("toPrice");
            if (toPrice != null && !toPrice.isEmpty()) {
                predicates.add(b.lessThanOrEqualTo(root.get("pricePerHour"), Double.parseDouble(toPrice)));
            }

//            String cateId = params.get("cateId");
//            if (cateId != null && !cateId.isEmpty()) {
//                predicates.add(b.lessThanOrEqualTo(root.get("categoryId"), Integer.parseInt(cateId)));
//            }
            q.where(predicates.toArray(Predicate[]::new));//danh sach vi tu truy van
        }

        q.orderBy(b.desc(root.get("parkingLotID")));

        Query query = session.createQuery(q);

        if (params != null) {
            String p = params.get("page");
            if (p != null && !p.isEmpty()) {
                int page = Integer.parseInt(p);
                int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));

                query.setMaxResults(pageSize);
                query.setFirstResult((page - 1) * pageSize);// vị trí bắt đầu lấy phần tử
            }
        }

        return query.getResultList();
    }

    @Override
    public int countParkingLots() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(*) FROM ParkingLot");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public boolean addOrUpdateParkingLot(ParkingLot p) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (p.getParkingLotID() == null) {
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
    public ParkingLot getParkingLotById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(ParkingLot.class, id);
    }

    @Override
    public boolean deleteParkingLot(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        ParkingLot p = this.getParkingLotById(id);
        try {
            if (p != null) {
                s.delete(p);
                return true;
            } else {
                return false;
            }
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public int countEmptyParkingSpot(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(ps) FROM ParkingSpot ps WHERE ps.parkingLotID.parkingLotID = :parkingLotID AND ps.status = 'Empty'");
        q.setParameter("parkingLotID", id);

        return ((Long) q.getSingleResult()).intValue();
    }

}
