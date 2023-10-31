package com.nelldora.mall.cart.repository;

import com.nelldora.mall.cart.domain.Cart;
import com.nelldora.mall.cart.domain.CartItem;
import com.nelldora.mall.cart.service.CartService;
import com.nelldora.mall.item.domain.Item;
import com.nelldora.mall.item.repository.ItemRepository;
import com.nelldora.mall.user.domain.User;
import com.nelldora.mall.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Rollback(value = false)
@Slf4j
class JpaCartRepositoryTest {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    private final ItemRepository itemRepository;

    private final CartService cartService;

    @Autowired
    public JpaCartRepositoryTest(UserRepository userRepository, CartRepository cartRepository, ItemRepository itemRepository , CartService cartService){
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
        this.cartService = cartService;
    }

    @Test
    void Test1(){
        // 조건 값
        User findUser = userRepository.findById("test").get(0);
        Cart newCart =Cart.createOrder(findUser);

        cartRepository.save(newCart);
    }

    @Test
    void Test2(){

        Cart findCart = cartRepository.findByUserId("test");
        Item findItem1 = itemRepository.findById(9L);
        Item findItem2 = itemRepository.findById(9L);

        log.info("test 유저의 장바구니 코드 = {} ", findCart.getId());
        log.info("찾은 상품의 ID 값 = {}, 이름 = {} , 가격 = {} ", findItem1.getId(), findItem1.getName(), findItem1.getPrice());
        log.info("찾은 상품의 ID 값 = {}, 이름 = {} , 가격 = {} ", findItem2.getId(), findItem2.getName(), findItem2.getPrice());

        CartItem cartItem1 = CartItem.createCartItem(findItem1, findCart , 10 );
        CartItem cartItem2 = CartItem.createCartItem(findItem2, findCart , 10 );

        //findCart.addCartItems(cartItem1, cartItem2);

    }

    @Test
    void Test3(){
        List<User> findUser = userRepository.findById("test");
        findUser.get(0);
        cartService.addCart(findUser.get(0), 8L, 2);
    }
}