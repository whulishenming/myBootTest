package com.lsm.boot.mongodb.service;

import com.lsm.boot.mongodb.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userServiceImpl;

    @Test
    public void insert() throws Exception {
        User user = new User();

        Date now = new Date();

        user.setCreateBy("lsmlsm");
        user.setCreateTime(now);
        user.setIsDeleted(0);
        user.setName("test mongo");
        user.setPassword("123456");
        user.setPhoneNum("18621941234");
        user.setUpdateBy("lsmlsm");
        user.setUpdateTime(now);

        userServiceImpl.insert(user);
    }

    @Test
    public void batchInsert() throws Exception {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            User user = new User();

            Date now = new Date();

            user.setCreateBy("lsm");
            user.setCreateTime(now);
            user.setIsDeleted(0);
            user.setName("test mongo" + i);
            user.setPassword("123456");
            user.setPhoneNum("18621941234" + i);
            user.setUpdateBy("lsm");
            user.setUpdateTime(now);

            users.add(user);
        }

        userServiceImpl.batchInsert(users);

    }

    @Test
    public void findByName() throws Exception {
        List<User> users = userServiceImpl.findByName("test mongo");

        System.out.println(users);

    }

    @Test
    public void deleteById() throws Exception {

        userServiceImpl.deleteById("5a015daae48d7713db173121");
    }

    @Test
    public void removeRecord() throws Exception {
        userServiceImpl.removeRecord("5a015daae48d7713db173122");
    }

}