package com.nelldora.mall.order.domain;

import com.nelldora.mall.item.domain.Item;
import com.nelldora.mall.order.vo.OrderState;

import javax.persistence.*;

@Entity
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

    private int count;

    @Column(name = "order_price")
    private int orderPrice;

    //주문상태
    @Column(name = "order_state")
    private OrderState orderState;

}
