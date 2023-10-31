package com.nelldora.mall.cart.repository;

import com.nelldora.mall.cart.domain.Cart;

import java.util.List;

public interface CartRepository {

    void save(Cart cart);

    void update(Long id, Cart updateCart);

    Cart findById(Long id);

    Cart findByUserId(String id);
}