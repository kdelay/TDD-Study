package com.tdd.week04.chapter07.user.repository;

import com.tdd.week04.chapter07.user.domain.User;

public interface UserRepository {
    void save(User user);
    User findById(String id);
}
