package com.nelldora.mall.payment.ajax;

import com.nelldora.mall.order.domain.Order;
import com.nelldora.mall.order.service.OrderService;
import com.nelldora.mall.payment.domain.Payment;
import com.nelldora.mall.payment.exception.PayFailedValidationException;
import com.nelldora.mall.payment.exception.PriceValidationException;
import com.nelldora.mall.payment.service.PaymentService;
import com.nelldora.mall.payment.vo.PaymentValidationCheckState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.PrintWriter;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PayValidationAjax {

    private final OrderService orderService;

    @PostMapping(value = "/payment", produces = "application/json; charset=utf-8")
    public ResponseEntity<String> result(@RequestBody Payment payment) {


        String result = null;
        System.out.println("호출 되는지 확인");


        System.out.println(payment.toString());

        //payment의 merchant_uid값 ( 주문번호) 검색
        Order findOrder = orderService.findById(payment.getMerchantUid());


        PaymentValidationCheckState checkState = PaymentValidationCheckState.OPEN;

        try {
            PaymentService.validationCheck(payment, findOrder);

            //orderService.
            //order 엔티티의 주문상태 업데이트

            checkState = PaymentValidationCheckState.SUCCESS;


            //Payment payment = new Payment();
            //주문정보 데이터 베이스 저장

        } catch (PayFailedValidationException e) {
            e.printStackTrace();
            checkState = PaymentValidationCheckState.FAILED;
            //order 엔티티 주문 상태 취소로 업데이트

            result = "ERROR";
        } catch (PriceValidationException e) {
            e.printStackTrace();
            checkState = PaymentValidationCheckState.PRICEDIFF;
            //order 엔티티 주문 상태 취소로 업데이트

            result = "PAYDIFF";
        }
        System.out.println("위변조 결과 값 : " + checkState);




        return new ResponseEntity<>(HttpStatus.OK);
    }

}