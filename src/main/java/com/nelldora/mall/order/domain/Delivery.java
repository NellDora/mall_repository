package com.nelldora.mall.order.domain;

import com.nelldora.mall.order.vo.DeliveryState;

import javax.persistence.*;

@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    @Column(name = "address_num")
    private String addressNum;

    @Enumerated(EnumType.STRING)
    private DeliveryState deliveryState;

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getAddressNum() {
        return addressNum;
    }

    public DeliveryState getDeliveryState() {
        return deliveryState;
    }
}
