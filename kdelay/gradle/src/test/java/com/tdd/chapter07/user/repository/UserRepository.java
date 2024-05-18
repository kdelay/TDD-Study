package com.tdd.chapter07.user.repository;

import com.tdd.chapter07.user.domain.User;

public interface UserRepository {
    void save(User user);
    User findById(String id);
}
