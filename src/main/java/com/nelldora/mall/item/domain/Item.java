package com.nelldora.mall.item.domain;

import com.nelldora.mall.order.domain.OrderItem;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "item")
public class Item {
//반드시 DB에 추가 할 내용 추가 기재할 것
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private long id;

    private String name;

    private String description;

    private int price;

    private int stock;

    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItems;

}
