package com.nelldora.mall.order.dto;

import com.nelldora.mall.order.domain.Delivery;
import com.nelldora.mall.order.domain.Order;
import com.nelldora.mall.order.domain.OrderItem;
import com.nelldora.mall.order.vo.OrderState;
import com.nelldora.mall.user.domain.User;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {

    private Long id;


    private String userId;

    private String userName;

    private List<OrderItem> orderItems;

    private OrderState orderState;

    private Delivery delivery; //배송지

    private LocalDateTime regDate; //등록날짜

    private Date payDate; // 결제 예정 날자

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.userId = order.getUser().getId();
        this.userName = order.getUser().getName();
        this.orderItems = order.getOrderItems();
        this.orderState = order.getOrderState();
        this.delivery = order.getDelivery();
        this.regDate = order.getRegDate();
        this.payDate = order.getPayDate();
    }
}
