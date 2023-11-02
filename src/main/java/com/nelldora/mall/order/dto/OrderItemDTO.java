package com.nelldora.mall.order.dto;

import com.nelldora.mall.item.domain.Item;
import com.nelldora.mall.order.domain.Order;
import com.nelldora.mall.order.domain.OrderItem;
import com.nelldora.mall.order.vo.OrderState;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class OrderItemDTO {

    private Long id;


    private Long orderItemId;

    private String orderItemName;

    private String orderItemCode;

    private Order order;

    private int quantity;

    private int orderPrice;

    private OrderState orderState;

    public OrderItemDTO(OrderItem orderItem) {
        this.id = orderItem.getId();
        this.orderItemId = orderItem.getItem().getId();
        this.orderItemName = orderItem.getItem().getName();
        this.orderItemCode = orderItem.getItem().getItemCode();
        this.order = orderItem.getOrder();
        this.quantity = orderItem.getQuantity();
        this.orderPrice = orderItem.getOrderPrice();
        this.orderState = orderItem.getOrderState();
    }
}
