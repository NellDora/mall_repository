package com.nelldora.mall.user.dto;

import com.nelldora.mall.board.domain.Board;
import com.nelldora.mall.order.domain.Order;
import com.nelldora.mall.user.domain.User;
import com.nelldora.mall.user.vo.Grade;

import java.util.Date;
import java.util.List;

public class UserDTO {

    private String id;
    private String passwordOne;
    private String passwordTwo;
    private String name;
    private String nickName;
    private int age;
    private String address;

    private String telephone;
    private int balance;
    private Date joinDate;
    private List<Order> orderList;
    private List<Board> boardList;
    private Grade grade;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.passwordOne = user.getPassword();
        this.name = user.getName();
        this.nickName = user.getNickName();
        this.age = user.getAge();
        this.address = user.getAddress();
        this.telephone = user.getTelephone();
        this.balance = user.getBalance();
        this.joinDate = user.getJoinDate();
        this.orderList = user.getOrderList();
        this.boardList = user.getBoardList();
        this.grade = user.getGrade();
    }

    public String getId() {
        return id;
    }

    public String getPasswordOne() {
        return passwordOne;
    }

    public String getPasswordTwo() {
        return passwordTwo;
    }

    public String getName() {
        return name;
    }

    public String getNickName() {
        return nickName;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public int getBalance() {
        return balance;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public List<Board> getBoardList() {
        return boardList;
    }

    public Grade getGrade() {
        return grade;
    }

    public String getTelephone() {
        return telephone;
    }
}
