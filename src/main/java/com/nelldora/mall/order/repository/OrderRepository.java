package com.nelldora.mall.order.repository;

import com.nelldora.mall.order.domain.Order;
import com.nelldora.mall.user.domain.User;

import java.util.List;

public interface OrderRepository {

    void save(Order order);

    Order findById(Long id);

    //주문 시작시 받아야 할 것  -> 유저,
    //String createOrder(User user, ){

    //}
    List<Order> findByIdDate(Long date);


}
