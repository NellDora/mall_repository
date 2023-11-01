package com.nelldora.mall.cart.repository;

import com.nelldora.mall.cart.domain.Cart;
import com.nelldora.mall.cart.domain.QCart;
import com.nelldora.mall.user.domain.QUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Transactional
@Repository
public class JpaCartRepository implements CartRepository{

    private final EntityManager em;
    private final JPAQueryFactory query;

    public JpaCartRepository(EntityManager em){
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Cart save(Cart cart) {
        em.persist(cart);
        return cart;
    }

    @Override
    public void update(Long id, Cart updateCart) {
        Cart findCart =  em.find(Cart.class, id);

    }

    @Override
    public Cart findById(Long id) {

        return em.find(Cart.class,id);
    }

    @Override
    public Cart findByUserId(String id) {
        QCart cart = QCart.cart;
        QUser user = QUser.user;
        return query.select(cart).from(cart).join(cart.user, user).on(user.id.eq(user.id)).fetchFirst();
    }

}
