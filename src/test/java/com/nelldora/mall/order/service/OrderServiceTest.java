package com.nelldora.mall.order.service;

import com.nelldora.mall.user.domain.User;
import com.nelldora.mall.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Slf4j
class OrderServiceTest {

    private final OrderService orderService;
    private final UserRepository userRepository;

    @Autowired
    public OrderServiceTest(OrderService orderService , UserRepository userRepository){
        this.orderService = orderService;
        this.userRepository = userRepository;
    }

    @Test
    void Test1() {
        //ID로 유저 찾기의 경우 1개 일테인데 리스트로 받는거 추후 변경 필요
        //입력 조건
        User findUserResult = userRepository.findById("test");
        User findUser = findUserResult;

        //실행 주문 시작

    }
    @Test
    void Test2(){
        Long num =orderService.createOrderId();
        log.info("생성된 아이디 값 : {}",num);

        
        
    }

}