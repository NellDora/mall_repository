package com.nelldora.mall.user.domain;

import com.nelldora.mall.user.vo.Grade;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;

    private String nickName;

    private String age;

    private String address;

    private String telephone;

    private LocalDateTime regDate;

    //private List<Order> orderList = new ArrayList<>();

    private Grade grade;

    protected User() {
    }

    public User createUser(){
        User user = new User();
        return this;
    }


}
