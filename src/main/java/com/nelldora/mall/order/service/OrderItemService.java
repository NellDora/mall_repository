package com.nelldora.mall.order.service;

import com.nelldora.mall.cart.domain.CartItem;
import com.nelldora.mall.item.domain.Item;
import com.nelldora.mall.item.repository.ItemRepository;
import com.nelldora.mall.order.domain.Order;
import com.nelldora.mall.order.domain.OrderItem;
import com.nelldora.mall.order.exception.StockLackException;
import com.nelldora.mall.order.repository.OrderItemRepository;
import com.nelldora.mall.order.repository.OrderRepository;
import com.nelldora.mall.order.vo.OrderCheckState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final ItemRepository itemRepository;

    //단일 주문 생성용
    public OrderItem saveOrderItem(Order order, CartItem cartItem){
        OrderItem newOrderItem = OrderItem.createOrderItem(order, cartItem);
        orderItemRepository.save(newOrderItem);
        log.info("OrderItemService : 단일 주문 생성 완료");
        return newOrderItem;
    }
    //장바구니에 있던 것들 전체 주문 상세 생성
    @Transactional
    public OrderCheckState saveOrderItems(Order order, List<CartItem> cartItems){

        OrderCheckState checkState = OrderCheckState.STANDBY;

        try {
            for(CartItem cartItem: cartItems){
                checkStock(cartItem);
            }
            for(CartItem cartItem : cartItems){
                OrderItem newOrderItem = OrderItem.createOrderItem(order,cartItem);
                orderItemRepository.save(newOrderItem);

                log.info("OrderItemService : 저장 되었사옵니다");
            }
            } catch (StockLackException e) {
                throw new RuntimeException(e);
            }
        return checkState;

    }

    //주문 수량과 판매물품 나은 재고 검증 로직
    public void checkStock(CartItem cartItem) throws StockLackException {

        Item findItem = itemRepository.findById(cartItem.getItem().getId());
        if(cartItem.getQuantity()> findItem.getStock()){
            log.info("OrderItemService : 주문 수량 , 재고 확인 완료 ( 문제 없음 ");
        }else{
            throw new StockLackException("물품의 수량이 구매하시는 수량보다 부족합니다.");
        }
    }

}
