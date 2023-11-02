package com.nelldora.mall.order.repository;

import com.nelldora.mall.order.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class JpaOrderRepositoryTest {

    private final OrderRepository orderRepository;
    private final JpaOrderRepository jpaOrderRepository;

    @Autowired
    public JpaOrderRepositoryTest(OrderRepository orderRepository, JpaOrderRepository jpaOrderRepository){

        this.orderRepository = orderRepository;
        this.jpaOrderRepository = jpaOrderRepository;
    }

    @Test
    void Test1(){

        List<Order> orderList = orderRepository.findByIdDate(20231102L);

        for(Order order : orderList){
            log.info(" 받은 ID값 : {}" ,order.getId());
        }
    }

    @Test
    void Test2(){

        List<Order> orderList = jpaOrderRepository.findByIdDateV2("20231102");

        for(Order order : orderList){
            log.info(" 받은 ID값 : {}" ,order.getId());
        }
    }
}