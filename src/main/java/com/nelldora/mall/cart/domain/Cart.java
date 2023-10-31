package com.nelldora.mall.cart.domain;

import com.nelldora.mall.order.domain.Delivery;
import com.nelldora.mall.user.domain.User;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "cart")
@Slf4j
@Getter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    //주문한 주문자 정보

    @OneToMany(mappedBy ="cart" , fetch = FetchType.LAZY) //생각해볼 것 => CasheCade를 사용할 것인가? 추후에 orderItem과 다른 연관관계가 없을 것인지?
    private List<CartItem>  cartItems;                     //사용하지 않고 직접 따로 저장하기로

    @Column(name = "total_price")
    private int totalPrice;

    protected Cart() {
    }

    //내 장바구니
    public static Cart createOrder(User user){
        Cart cart = new Cart();
        cart.user = user;
        cart.totalPrice =0;
        return cart;
    }



    //장바구니 물품 추가
    public CartItem addCartItem(CartItem addCartItem){
        this.totalPrice += addCartItem.getTotalPrice();
        return addCartItem;
    }

    //장바구니 물품 일괄 추가
    public List<CartItem> addCartItems(CartItem ... addCartItems){

        for(CartItem addCartItem : addCartItems){
            cartItems.add(addCartItem);
            this.totalPrice += addCartItem.getTotalPrice();
        }
        log.info("주문아이템 추가 완료");

        //주문 추가 목록 반환
        return cartItems;
    }
}
