package com.nelldora.mall.cart.repository;

import com.nelldora.mall.cart.domain.Cart;

import java.util.List;

public interface CartRepository {

    Cart save(Cart cart);

    void update(Long id, Cart updateCart);

    Cart findById(Long id);

    Cart findByUserNumber(Long userNum);
}
