package com.lsm.boot.mongodb.service;

import com.lsm.boot.mongodb.model.User;
import com.lsm.boot.mongodb.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userServiceImpl;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void save() throws Exception {
        User user = new User();

        Date now = new Date();

        user.setCreateBy("lsm");
        user.setCreateTime(now);
        user.setIsDeleted((byte) 0);
        user.setName("test mongo");
        user.setPassword("123456");
        user.setPhoneNum("18621941234");
        user.setUpdateBy("lsm");
        user.setUpdateTime(now);

        userServiceImpl.save(user);

//        userRepository.save(user);

        System.out.println(user.getId());
    }

    @Test
    public void saveAll() {
        List<User> userList = new ArrayList<>();
        for (int i = 10000; i < 20000; i++) {
            User user = new User();

            Date now = new Date();

            user.setCreateBy("lsm" + i % 11);
            user.setCreateTime(now);
            user.setIsDeleted((byte) 0);
            user.setName("test Name" + i % 13);
            user.setPassword("123456");
            user.setPhoneNum("186219" + i);
            user.setUpdateBy("lsm" + i % 17);
            user.setUpdateTime(now);

            userList.add(user);
        }


        userServiceImpl.saveAll(userList);
    }

    @Test
    public void findByName() {

        List<User> userList = userServiceImpl.findByName("test Name1");

        System.out.println(userList);
    }

}