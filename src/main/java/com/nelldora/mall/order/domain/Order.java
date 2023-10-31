package com.nelldora.mall.order.domain;

import com.nelldora.mall.order.exception.AlreadyDeliveryException;
import com.nelldora.mall.order.exception.InvalidateCancelException;
import com.nelldora.mall.order.vo.DeliveryState;
import com.nelldora.mall.order.vo.OrderState;
import com.nelldora.mall.user.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Lazy;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Slf4j
public class Order { //사실상 Payment (결제)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    //주문한 주문자 정보

    @OneToMany(mappedBy ="order" , fetch = FetchType.LAZY) //생각해볼 것 => CasheCade를 사용할 것인가? 추후에 orderItem과 다른 연관관계가 없을 것인지?
    private List<OrderItem> orderItems;                     //사용하지 않고 직접 따로 저장하기로

    @Enumerated
    private OrderState orderState;

    @OneToOne // 1:1 관계라서 cascade 사용에 전혀 문제 없음
    @JoinColumn(name="delivery_id")
    private Delivery delivery; //배송지

    private LocalDateTime regDate; //등록날짜

    private Date payDate; // 결제 예정 날자

    protected Order() {
    }

    //주문생성
    public static Order createOrder(User user,Delivery delivery){
        Order order = new Order();
        order.user = user;
        order.delivery = delivery;
        order.regDate = LocalDateTime.now();
        return order;
    }
    
    //주문상품 추가
    public List<OrderItem> addOrderItems(OrderItem ... addOrderItems){
        
        for(OrderItem addOrderItem : addOrderItems){
            orderItems.add(addOrderItem);
        }
        log.info("주문아이템 추가 완료");
        
        //주문 추가 목록 반환
        return orderItems;
    }




}
