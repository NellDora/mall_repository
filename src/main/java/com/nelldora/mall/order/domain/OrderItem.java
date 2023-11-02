package com.nelldora.mall.order.domain;

import com.nelldora.mall.cart.domain.CartItem;
import com.nelldora.mall.item.domain.Item;
import com.nelldora.mall.order.vo.OrderState;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "order_id") //주문리스트에 들어 갈 것이므로 주문 번호
    private Order order;

    private int quantity;

    @Column(name = "order_price")
    private int orderPrice;

    //주문상태
    @Column(name = "order_state")
    private OrderState orderState;

    protected OrderItem(){

    }

    public static OrderItem createOrderItem(Order order, CartItem cartItem){
    OrderItem orderItem = new OrderItem();
    orderItem.item = cartItem.getItem();
    orderItem.quantity = cartItem.getQuantity();
    orderItem.orderPrice = cartItem.getTotalPrice();
    orderItem.order = order;
    orderItem.orderState = OrderState.STANDBY;
    return orderItem;
    }
}
