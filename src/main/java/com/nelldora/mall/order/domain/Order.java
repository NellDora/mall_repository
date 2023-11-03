package com.nelldora.mall.order.domain;

import com.nelldora.mall.order.exception.AlreadyDeliveryException;
import com.nelldora.mall.order.exception.InvalidateCancelException;
import com.nelldora.mall.order.vo.DeliveryState;
import com.nelldora.mall.order.vo.OrderCheckState;
import com.nelldora.mall.order.vo.OrderState;
import com.nelldora.mall.user.domain.User;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Lazy;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Slf4j
@Getter
public class Order { //사실상 Payment (결제)

    @Id
    @Column(name = "order_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    //주문한 주문자 정보

    @OneToMany(mappedBy ="order" , fetch = FetchType.LAZY) //생각해볼 것 => CasheCade를 사용할 것인가? 추후에 orderItem과 다른 연관관계가 없을 것인지?
    private List<OrderItem> orderItems;                     //사용하지 않고 직접 따로 저장하기로

    @Column(name = "order_state")
    @Enumerated
    private OrderState orderState;

    @OneToOne // 1:1 관계라서 cascade 사용에 전혀 문제 없음
    @JoinColumn(name="delivery_id")
    private Delivery delivery; //배송지

    @Column(name = "reg_date")
    private LocalDateTime regDate; //등록날짜

    @Column(name = "pay_Date")
    private Date payDate; // 결제 예정 날자

    @Column(name = "total_price")
    private Long totalPrice;

    protected Order() {
    }

    //주문생성
    public static Order createOrder(Long muid,User user,Delivery delivery){
        Order order = new Order();
        order.id = muid;
        order.user = user;
        order.delivery = delivery;
        order.regDate = LocalDateTime.now();
        order.totalPrice =0L;
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

    public Order orderUpdate(OrderState orderState){
        this.orderState = orderState;
        return this;
    }

    public Order revisionTotalPrice(List<OrderItem> orderItems){

        for(OrderItem orderItem : orderItems){
            this.totalPrice += orderItem.getOrderPrice();
        }
        return this;
    }


    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void totalPriceUpdate(Long price){
        this.totalPrice = price;
    }
}
