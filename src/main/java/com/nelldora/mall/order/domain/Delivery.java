package com.nelldora.mall.order.domain;

import com.nelldora.mall.order.vo.DeliveryState;

public class Delivery {

    private Long id;

    private String address;

    private String addressNum;

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
