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
import java.util.List;

@Entity
@Table(name = "orders")
@Slf4j
public class Order {

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

    private Date regDate; //등록날짜

    private Date payDate; // 결제 예정 날자

    protected Order() {
    }

    //주문생성
    public static Order createOrder(User user,Delivery delivery, Date regDate, OrderItem ... orderItems){
        Order order = new Order();
        order.user = user;
        order.delivery = delivery;

        for(OrderItem orderItem : orderItems) {
            order.orderItems.add(orderItem);
        }
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

    //배송지 변경하기 -> 주문 상태에 따라 주소 변경이 가능한지 안한지 상세 분류 필요
    public Delivery changeAddress(Delivery reDelivery) throws AlreadyDeliveryException {

        // 이미 배송 출발하거나 배달 완료 되었을 경우
        if(delivery.getDeliveryState().equals(DeliveryState.START)||delivery.getDeliveryState().equals(DeliveryState.ARRIVAL)){
            throw new AlreadyDeliveryException("배송이 출발했거나 배송이 완료되었습니다.");
        }else{
            delivery =reDelivery;
        }
        //배송확인을 위한 수정 배송정보 출력
        return delivery;
    }

    //주문 취소하기
    public void cancel() throws InvalidateCancelException {

        //이미 배송 출발했거나 배달 완료 되었을 경우 취소 불가능
        if(delivery.getDeliveryState().equals(DeliveryState.START)||delivery.getDeliveryState().equals(DeliveryState.ARRIVAL)){
            throw new InvalidateCancelException("배송이 출발했거나 배송이 완료되어 주문 취소가 불가합니다");
        }
    }



}
