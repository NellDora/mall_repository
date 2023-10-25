package com.nelldora.mall.user.repository;

import com.nelldora.mall.user.domain.User;

import java.util.List;

public interface UserRepository {

    void save(User user);

    User findByNum(int num);

    List<User> findById(String id);
}
