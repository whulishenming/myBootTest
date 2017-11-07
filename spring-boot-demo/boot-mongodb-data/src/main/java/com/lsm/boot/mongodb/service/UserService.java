package com.lsm.boot.mongodb.service;

import com.lsm.boot.mongodb.model.User;

import java.util.List;

public interface UserService {

    void insert(User user);

    void batchInsert(List<User> userList);

    List<User> findByName(String name);

    void deleteById(String id);

    void removeRecord(String id);
}
