package com.nelldora.mall.order.service;

import com.nelldora.mall.cart.domain.CartItem;
import com.nelldora.mall.item.domain.Item;
import com.nelldora.mall.item.repository.ItemRepository;
import com.nelldora.mall.order.domain.Delivery;
import com.nelldora.mall.order.domain.Order;
import com.nelldora.mall.order.domain.OrderItem;
import com.nelldora.mall.order.exception.StockLackException;
import com.nelldora.mall.order.repository.OrderItemRepository;
import com.nelldora.mall.order.repository.OrderRepository;
import com.nelldora.mall.order.vo.OrderCheckState;
import com.nelldora.mall.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;


    public Order findById (Long id){
        return orderRepository.findById(id);
    }
    public OrderCheckState saveOrder(User user, List<CartItem> cartItems , Delivery delivery){
        //OrderCheckState => 주문 생성이 성공했는지 안했는지 확인을 하기 위한 리턴값
        OrderCheckState orderCheckState = OrderCheckState.STANDBY;
        try {

            Long merchantIdNum = createOrderId();

            for(CartItem cartItem: cartItems){
                checkStock(cartItem);
            }

            
            //주문 객체 생성
            Order newOrder = Order.createOrder(merchantIdNum,user, delivery);
            //주문 결제 DB에 저장 완료
            orderRepository.save(newOrder);

            //주문 상세 아이템 일괄 셍성
            List<OrderItem> newOrderItems= saveOrderItems(newOrder,cartItems);
            //revisionTotalPrice(newOrder.getId(),newOrderItems);\
            calTotalPrice(newOrder.getId(), newOrderItems);
            log.info("상세보기 일괄 생성");
            orderCheckState = OrderCheckState.PASS;
        } catch (StockLackException e) {
            orderCheckState = OrderCheckState.LOQ;
        }


        return orderCheckState;
    }

    //단일 주문 생성용
    public OrderItem saveOrderItem(Order order, CartItem cartItem){
        OrderItem newOrderItem = OrderItem.createOrderItem(order, cartItem);
        orderItemRepository.save(newOrderItem);
        log.info("OrderItemService : 단일 주문 생성 완료");
        return newOrderItem;
    }
    //장바구니에 있던 것들 전체 주문 상세 생성
 
    public List<OrderItem> saveOrderItems(Order order, List<CartItem> cartItems) throws StockLackException {
        //주문체크 상태 인스턴스 생성
            log.info("상세 주문 동작 시작");
            List<OrderItem> orderItems = new ArrayList<>();

            //수량체크는 saveOrder 상층부로 이동
            
            //수량 체크 문제 없을 시 모든 구매할 물품 ( 장바구니)를 주문으로 변경
            for(CartItem cartItem : cartItems){
                //저장할 orderItem 객체 새로 생성
                OrderItem newOrderItem = OrderItem.createOrderItem(order,cartItem);
                orderItems.add(newOrderItem);
                //구매할 상품의 판매 수량 수정
                Item saleItem = cartItem.getItem();
                itemRepository.subtractStock(saleItem.getId(), cartItem.getQuantity());
                log.info("수량 변경 완료");

                //상세 주문 저장 완료
                orderItemRepository.save(newOrderItem);

                //그리고 판매수량을 전체 구매 수량만큼 감소시켜야함
                log.info("OrderItemService : 저장 되었사옵니다");

            }
            return orderItems;

    }

    //주문 수량과 판매물품 나은 재고 검증 로직
    public void checkStock(CartItem cartItem) throws StockLackException {
        log.info("재고확인 시작");
        Item findItem = cartItem.getItem();
        log.info(" {} {} {}", findItem.getId(), findItem.getName() , findItem.getStock());
        //Item findItem = itemRepository.findById(cartItem.getItem().getId());
        if(cartItem.getQuantity()< findItem.getStock()){
            log.info("OrderItemService : 주문 수량 , 재고 확인 완료 ( 문제 없음 ");
        }else{
            throw new StockLackException("물품의 수량이 구매하시는 수량보다 부족합니다.");
        }
    }

    //구매 수량보다 부족한 재고의 아이템을 모두 반환하는 메서드
    public List<CartItem> returnListLackStockItem(List<CartItem> cartItems){
        List<CartItem> lackCartItem = new ArrayList<>();
        log.info("재고 부족 물품 반환 시작");
        for(CartItem cartItem : cartItems){
            Item findItem = cartItem.getItem();
            if(cartItem.getQuantity()>findItem.getStock()){
                lackCartItem.add(cartItem);
            }

        }
        return lackCartItem;

    }

    //주무번호 생성   20231102/000001
    public Long createOrderId(){

        Long orderNumber=0L;

        //생성할 날짜
        LocalDate localDate = LocalDate.now();

        //포메팅 변환용 포메터 선언
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        //데이터 입력
        String todayDate = localDate.format(formatter);

        if(orderRepository.findByIdDate(Long.parseLong(todayDate)).isEmpty()){
            todayDate+="000001";
            orderNumber=Long.parseLong(todayDate);
        }else{
            List<Order> findOrders = orderRepository.findByIdDate(Long.parseLong(todayDate));
            orderNumber = findOrders.get(0).getId()+1;
        };

        return orderNumber;
    }

    @Transactional
    public void totalPriceUpdateForOrder(Long id , Long price){

    }

    private Long calTotalPrice(Long id,List<OrderItem> orderItems){
        Long totalPrice = 0L;
        for(OrderItem orderItem : orderItems){
            totalPrice += orderItem.getOrderPrice();
        }
        orderRepository.updatePrice(id,totalPrice);
        return totalPrice;
    }
}
