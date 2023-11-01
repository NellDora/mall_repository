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
        log.info("넘겨받은 결제 정보 : ={}", payment.getImp_uid());
        log.info("넘겨받은 결제자 이름 : = {}", payment.getMerchant_uid());


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
