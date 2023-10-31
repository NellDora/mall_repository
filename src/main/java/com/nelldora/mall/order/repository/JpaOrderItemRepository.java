package com.nelldora.mall.order.repository;

import com.nelldora.mall.order.domain.OrderItem;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@Slf4j
public class JpaOrderItemRepository implements OrderItemRepository{

    private final EntityManager em;
    private final JPAQueryFactory query;

    public JpaOrderItemRepository(EntityManager em){
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public void save(OrderItem orderItem) {
        em.persist(orderItem);
    }


}
