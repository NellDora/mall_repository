package com.nelldora.mall.user.repository;

import com.nelldora.mall.user.domain.User;

public interface UserRepository {

    void save(User user);

    User findById(String id);
}
