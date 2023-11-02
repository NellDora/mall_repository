package com.nelldora.mall.order.repository;


import com.nelldora.mall.order.domain.Order;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class JpaOrderRepository implements OrderRepository{

    private final EntityManager em;
    private final JPAQueryFactory query;

    public JpaOrderRepository(EntityManager em){
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public void save(Order order) {
        log.info("OrderRepository : Order 생성 완료");
        em.persist(order);
    }

    @Override
    public Order findById(Long id) {
        return null;
    }

    @Override
    public List<Order> findByIdDate(Long date) {
        List orderList = new ArrayList<>();
                orderList = em.createQuery("select t from Order t where t.id like concat(:idDate ,'%')")
                .setParameter("idDate", date)
                .getResultList();

        return orderList;
    }

    public List<Order> findByIdDateV2(String date) {
        List orderList = new ArrayList<>();
        orderList = em.createQuery("select t from Order t where t.id like concat(:idDate ,'%')")
                .setParameter("idDate", date)
                .getResultList();

        return orderList;
    }

}
