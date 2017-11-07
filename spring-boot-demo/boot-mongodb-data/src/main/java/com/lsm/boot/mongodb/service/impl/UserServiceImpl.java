package com.lsm.boot.mongodb.service.impl;

import com.lsm.boot.mongodb.model.User;
import com.lsm.boot.mongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void insert(User user) {
        mongoTemplate.insert(user);
    }

    @Override
    public void batchInsert(List<User> userList) {

        mongoTemplate.insert(userList, User.class);
    }

    @Override
    public List<User> findByName(String name) {

        return mongoTemplate.find(query(where("name").is(name)).with(new Sort(Sort.Direction.DESC, "createTime")).limit(10), User.class);
    }

    @Override
    public void deleteById(String id) {

        mongoTemplate.updateFirst(query(where("id").is(id)), update("isDeleted", 1), User.class);
    }

    @Override
    public void removeRecord(String id) {
        mongoTemplate.remove(query(where("id").is(id)), User.class);
    }
}
