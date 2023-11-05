package com.nelldora.mall.cart.repository;

import com.nelldora.mall.cart.domain.CartItem;

import java.util.List;

public interface CartItemRepository {

    void save(CartItem cartItem);

    CartItem findById(Long id);

    List<CartItem> findByCartId(Long id);

    void removeOne(CartItem cartItem);
}
