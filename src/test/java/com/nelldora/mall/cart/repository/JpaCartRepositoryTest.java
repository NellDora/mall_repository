package com.nelldora.mall.cart.repository;

import com.nelldora.mall.cart.domain.Cart;
import com.nelldora.mall.cart.domain.CartItem;
import com.nelldora.mall.cart.service.CartService;
import com.nelldora.mall.item.domain.Item;
import com.nelldora.mall.item.repository.ItemRepository;
import com.nelldora.mall.user.domain.User;
import com.nelldora.mall.user.dto.UserDTO;
import com.nelldora.mall.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

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
        User findUser = userRepository.findById("test3");
        Cart newCart =Cart.createOrder(findUser);

        cartRepository.save(newCart);
    }

    @Test
    void Test2(){

        User findUser = userRepository.findById("test");
        Cart findCart = cartRepository.findByUserNumber(findUser.getUserNumber());
        Item findItem1 = itemRepository.findById(6L);
        Item findItem2 = itemRepository.findById(7L);
        Item findItem3 = itemRepository.findById(8L);

        log.info("test 유저의 장바구니 코드 = {} ", findCart.getId());
        log.info("찾은 상품의 ID 값 = {}, 이름 = {} , 가격 = {} ", findItem1.getId(), findItem1.getName(), findItem1.getPrice());
        log.info("찾은 상품의 ID 값 = {}, 이름 = {} , 가격 = {} ", findItem2.getId(), findItem2.getName(), findItem2.getPrice());

        CartItem cartItem1 = CartItem.createCartItem(findItem1, findCart , 4 );
        CartItem cartItem2 = CartItem.createCartItem(findItem2, findCart , 6 );
        CartItem cartItem3 = CartItem.createCartItem(findItem3, findCart , 8 );


        //findCart.addCartItems(cartItem1, cartItem2);

    }

    @Test
    void Test3(){
        User findUser = userRepository.findById("aa");
        log.info(findUser.getId());
        cartService.addCart(findUser, 6L, 4);
        cartService.addCart(findUser, 7L, 6);
        cartService.addCart(findUser, 8L, 8);
    }

    @Test
    void Test32(){

        User findUser = userRepository.findById("test2");
        log.info(findUser.getId());
        cartService.addCart(findUser, 10L, 4);
        cartService.addCart(findUser, 11L, 4);
        cartService.addCart(findUser, 12L, 4);
    }
    @Test
    void Test36() {
        String test1 = "test";
        User findUser1 = userRepository.findById("test2");

        String test2 = "test2";
        User findUser2 = userRepository.findById("test2");

        log.info("test의 카트 값 {}", cartRepository.findByUserNumber(findUser1.getUserNumber()));
        if(cartRepository.findByUserNumber(findUser2.getUserNumber())==null){
            log.info("test2의 카드는 없습니다.");
        }else{
            log.info("test의 카트 값 {}", cartRepository.findByUserNumber(findUser2.getUserNumber()));
        }

    }
    @Test
    void Test33(){
        User findUser = userRepository.findById("test");
        log.info(findUser.getId());

        User findUser2 = userRepository.findById("test2");
        log.info(findUser2.getId());
    }
    @Test
    void Test4(){
        User findUser = userRepository.findById("test");
        cartService.addCart(findUser, 6L, 5);
    }

    @Transactional
    @Test
    void Test5(){
        User findUser = userRepository.findById("test");
        Cart findCart = cartRepository.findByUserNumber(findUser.getUserNumber());
        UserDTO user = new UserDTO(findCart.getUser());
        log.info("찾은 카트의 정보 {}, {}, {}", findCart.getId(),findCart.getUser().getId() ,findCart.getTotalPrice());
    }

    @Test
    void Test6(){
        User findUser = userRepository.findById("test2");
        cartService.addCart(findUser, 7L, 5);
    }
}