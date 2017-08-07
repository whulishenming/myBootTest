package com.lsm.boot.mongodb.repository;

import com.lsm.boot.mongodb.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    List<User> findByName(String name);

}
