package com.nelldora.mall.order.repository;

import com.nelldora.mall.order.domain.OrderItem;

public interface OrderItemRepository {

    void save (OrderItem orderItem);
}
