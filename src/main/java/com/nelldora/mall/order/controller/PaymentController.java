package com.nelldora.mall.order.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {

    @PostMapping(value = "/payment", produces ="application/json; charset=utf-8")
    public ResponseEntity<String> result(@RequestBody Payment payment){

        System.out.println("호출 되는지 확인");
        
        //정보가 넘어오는지 확인해야함

        //log.info("넘겨받은 결제 정보 : ={}", payment);
        log.info("넘겨받은 가맹점 번호 : {}", payment.getImp_uid());
        log.info("넘겨받은 구매 번호 :  {}", payment.getMerchant_uid());
        log.info("넘겨받은 결제 금액 : {}", payment.getPaid_amount());
        log.info("넘겨받은 결제 상테 : {}", payment.getStatus());
        log.info("넘겨받은 결제자 명 : {}", payment.getName());
        log.info("넘겨받은 결제 방식 : {}", payment.getPaid_method());
        log.info("넘겨받은 결제 성공여부 : {}", payment.isSuccess());
        


        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping(value = "/payment2", produces ="application/json; charset=utf-8")
    public String result(@RequestBody String imp_uid){

        System.out.println("호출 되는지 확인");

        //정보가 넘어오는지 확인해야함

        //log.info("넘겨받은 결제 정보 : ={}", payment);
        log.info("넘겨받은 결제 정보 : ={}", imp_uid);



        return "OK";

    }

}
