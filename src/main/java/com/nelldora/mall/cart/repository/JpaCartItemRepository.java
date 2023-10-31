package com.nelldora.mall.cart.repository;

import com.nelldora.mall.cart.domain.CartItem;
import com.nelldora.mall.cart.domain.QCart;
import com.nelldora.mall.cart.domain.QCartItem;
import com.nelldora.mall.user.domain.QUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
@Slf4j
public class JpaCartItemRepository implements CartItemRepository{

    private final EntityManager em;
    private final JPAQueryFactory query;

    public JpaCartItemRepository(EntityManager em){
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }


    @Override
    public void save(CartItem cartItem) {
        em.persist(cartItem);
    }

    @Override
    public CartItem findById(Long id) {
        return em.find(CartItem.class, id);
    }

    @Override
    public List<CartItem> findByCartId(Long id) {
        QCartItem cartItem = QCartItem.cartItem;
        QCart cart = QCart.cart;
        List<CartItem> cartItems = query.select(cartItem).from(cartItem).join(cartItem.cart, cart).on(cart.id.eq(id)).fetch();
        return cartItems;
    }
}
