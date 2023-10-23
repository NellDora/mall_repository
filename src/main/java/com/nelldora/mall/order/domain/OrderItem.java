package com.nelldora.mall.order.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue
    @Table(name = "oitem_id")
    private Long id;

    private Long item_id;

}
