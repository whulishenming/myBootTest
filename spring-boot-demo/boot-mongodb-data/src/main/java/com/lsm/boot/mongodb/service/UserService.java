package com.lsm.boot.mongodb.service;

import com.lsm.boot.mongodb.model.User;

import java.util.List;

public interface UserService {

    void save(User user);

    void saveAll(List<User> userList);

    List<User> findByName(String name);
}
