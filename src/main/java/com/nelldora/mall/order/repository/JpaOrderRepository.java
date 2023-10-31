package com.nelldora.mall.order.repository;


import com.nelldora.mall.order.domain.Order;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

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
        em.persist(order);
    }

    @Override
    public Order findById(Long id) {
        return null;
    }
}
