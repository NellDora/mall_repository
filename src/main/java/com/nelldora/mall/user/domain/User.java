package com.nelldora.mall.user.domain;

import com.nelldora.mall.board.domain.Board;
import com.nelldora.mall.order.domain.Order;
import com.nelldora.mall.order.exception.BalanceOverException;
import com.nelldora.mall.user.vo.Grade;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_number")
    private Long userNumber;

    @Column(name = "user_id")
    private String id;

    private String password;

    private String name;

    private String nickName;

    private int age;

    private String address;

    private String telephone;

    //특정 사이트들처럼 돈 충전해서 쓸 수 있도록 충전시스템
    private int balance;

    @Column(name = "join_date")
    private Date joinDate;

    @OneToMany(mappedBy = "user" ,fetch = FetchType.LAZY)
    private List<Order> orderList = new ArrayList<>();
    //일대다 양방향 관계

    @OneToMany(mappedBy = "writer" , fetch =FetchType.LAZY)
    private List<Board> boardList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Grade grade;

    protected User() {
    }

    //이게 맞는지는 모르겠지만 순간적으로 동시에 만들어지는 것을 방지하기 위한 아이디 생성 static 처리
    public static void createUser(String id ,String name,String password, String nickname, int age, String telephone){
        User user = new User();
        user.id = id;
        user.password= password;
        user.name = name;
        user.nickName = nickname;
        user.age = age;
        user.telephone = telephone;

    }


    //이용자 금액 충전하기
    public int deposit(int money){
        balance += money;
        return balance;
    }

    //이용자 금액 사용하기
    public int withdraw(int money) throws BalanceOverException {

        //잔액과 비교하여 부족하면 잔액부족 예외 발생새키기
        if(balance<money){
            throw new BalanceOverException("보유하신 잔액이 결제금액보다 부족합니다.");
        }else{
            balance-=money;
        }
        return balance;
    }





}
