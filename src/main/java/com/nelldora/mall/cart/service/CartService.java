package com.nelldora.mall.cart.service;

import com.nelldora.mall.cart.domain.Cart;
import com.nelldora.mall.cart.domain.CartItem;
import com.nelldora.mall.cart.repository.CartItemRepository;
import com.nelldora.mall.cart.repository.CartRepository;
import com.nelldora.mall.item.domain.Item;
import com.nelldora.mall.item.repository.ItemRepository;
import com.nelldora.mall.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ItemRepository itemRepository;



    //장바구니 추가
    @Transactional
    public void addCart(User user , Long itemId,int count){
        Cart findUserCart= null;
        //유저 카트 조회 시작
        if(cartRepository.findByUserId(user.getId())!=null){
            findUserCart =  cartRepository.findByUserId(user.getId());
        }else{
            Cart newCart =Cart.createOrder(user);
            findUserCart = cartRepository.save(newCart);
        }


        //아이템 조회
        Item findItem = itemRepository.findById(itemId);

        //---------------장바구니에 넣기 시작------------

        // 카드내 상세 상품 추기
        CartItem addCartItem = CartItem.createCartItem(findItem,findUserCart, count);
        cartItemRepository.save(addCartItem);

        //장바구니에 추가
        findUserCart.addCartItem(addCartItem);


        //---------------장바구니에 넣기 끝------------
    }

    public List<CartItem> findByCartIdForCartItemList(Long id){
        return cartItemRepository.findByCartId(id);
    }

    public Cart findByUserId (String id){
        return cartRepository.findByUserId(id);
    }

}
