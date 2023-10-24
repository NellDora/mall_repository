package com.nelldora.mall.user.dto;

import com.nelldora.mall.board.domain.Board;
import com.nelldora.mall.order.domain.Order;
import com.nelldora.mall.user.domain.User;
import com.nelldora.mall.user.vo.Grade;

import java.util.Date;
import java.util.List;

public class UserDTO {

    private String id;
    private String password;
    private String name;
    private String nickName;
    private int age;
    private String address;
    private int balance;
    private Date joinDate;
    private List<Order> orderList;
    private List<Board> boardList;
    private Grade grade;

    public UserDTO(User user) {
        this.id = user.getId();
        this.password = user.getPassword();
        this.name = user.getName();
        this.nickName = user.getNickName();
        this.age = user.getAge();
        this.address = user.getAddress();
        this.balance = user.getBalance();
        this.joinDate = user.getJoinDate();
        this.orderList = user.getOrderList();
        this.boardList = user.getBoardList();
        this.grade = user.getGrade();
    }
}
