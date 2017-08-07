package com.lsm.boot.mongodb.service.impl;

import com.lsm.boot.mongodb.model.User;
import com.lsm.boot.mongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(User user) {
        mongoTemplate.save(user);
    }

    @Override
    public void saveAll(List<User> userList) {

        mongoTemplate.save(userList);
    }

    @Override
    public List<User> findByName(String name) {

        Query query=new Query(Criteria.where("name").is(name));

        return mongoTemplate.find(query, User.class);
    }
}
