package com.nelldora.mall.cart.domain;

import com.nelldora.mall.item.domain.Item;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "cart_item")
@Getter
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "cart_id") //주문리스트에 들어 갈 것이므로 주문 번호
    private Cart cart;

    private int quantity;

    @Column(name = "cartitem_total_price")
    private int totalPrice;

    protected CartItem(){};

    public static CartItem createCartItem(Item item, Cart cart , int count){
        CartItem cartItem = new CartItem();
        cartItem.item = item;
        cartItem.cart = cart;
        cartItem.quantity = count;
        cartItem.totalPrice = cal(item.getPrice(), count);
        return cartItem;
    }

    private static int cal(int price , int count){
        int totalPrice = price*count;
        return totalPrice;
    }

}
