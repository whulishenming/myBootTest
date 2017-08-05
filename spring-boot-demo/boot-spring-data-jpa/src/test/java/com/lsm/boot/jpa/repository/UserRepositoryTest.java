package com.lsm.boot.jpa.repository;

import com.lsm.boot.jpa.JpaApplicationTests;
import com.lsm.boot.jpa.model.User;
import com.lsm.boot.jpa.model.UserStatisticsInfo;
import com.lsm.boot.jpa.param.UserParam;
import com.lsm.boot.jpa.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class UserRepositoryTest extends JpaApplicationTests {

    @Autowired
    private UserService userServiceImpl;


    @Test
    public void insert() {
        User user = new User();

        Date now = new Date();

        user.setCreateBy("lsm");
        user.setCreateTime(now);
        user.setIsDeleted((byte) 0);
        user.setName("test Name");
        user.setPassword("123456");
        user.setPhoneNum("18621941234");
        user.setUpdateBy("lsm");
        user.setUpdateTime(now);
        userServiceImpl.save(user);

        System.out.println(user.getId());
    }

    @Test
    public void batchInsert() {
        List<User> list = new ArrayList<>();

        Date now = new Date();

        for (int i = 4000; i < 5000; i++) {
            User user = new User();
            user.setId(10000L + i);
            user.setCreateBy("lsm"+ i % 20);
            user.setCreateTime(now);
            user.setIsDeleted((byte) 0);
            user.setName("test Name" + i % 100);
            user.setPassword("123456");
            user.setPhoneNum("1862194" + i);
            user.setUpdateBy("lsm"+ i % 20);
            user.setUpdateTime(now);

            list.add(user);
        }

        userServiceImpl.batchInsert(list);
    }

    @Test
    public void findByNameAndIsDeleted() throws Exception {
        List<User> userList = userServiceImpl.findByName("test Name0");
        System.out.println(userList);
    }

    @Test
    public void findFirstByName() throws Exception {
        User user = userServiceImpl.findFirstByName("test Name0");
        System.out.println(user);
    }

    @Test
    public void findByPhoneNumLike() throws Exception {
        List<User> userList = userServiceImpl.findByPhoneNumLike("1862194101");
        System.out.println(userList);
    }

    @Test
    public void findByPhoneNumStartingWith() throws Exception {
        List<User> userList = userServiceImpl.findByPhoneNumStartingWith("1862194101");
        System.out.println(userList);
    }

    @Test
    public void findByPhoneNumContaining() throws Exception {
        List<User> userList = userServiceImpl.findByPhoneNumContaining("101");
        System.out.println(userList);
    }

    @Test
    public void findByName() throws Exception {
        // 表示第四页
        Pageable pageable = new PageRequest(3, 3, Sort.Direction.DESC, "name", "createBy");
        Page<User> page = userServiceImpl.findByName("test Name0", pageable);

        System.out.println(page);
    }

    @Test
    public void findByQuery() throws Exception {
        List<User> userList = userServiceImpl.findByQuery("lsm0", new Date());
        System.out.println(userList);
    }

    @Test
    public void findByQuery2() throws Exception {
        List<User> userList = userServiceImpl.findByQuery2("lsm0", new Date());
        System.out.println(userList);
    }

    @Test
    public void updateById() throws Exception {
        int count = userServiceImpl.updateById(1L, "update name", "lishenming");
        System.out.println(count);
    }

    @Test
    public void updateByIds() throws Exception {
        int count = userServiceImpl.updateByIds(Arrays.asList(100L, 200L, 300L, 400L), "update name", "lishenming");
        System.out.println(count);
    }

    @Test
    public void getStatisticsInfos() throws Exception {
        List<UserStatisticsInfo> statisticsInfos = userServiceImpl.getStatisticsInfos();

        for (UserStatisticsInfo statisticsInfo : statisticsInfos) {
            System.out.println(statisticsInfo.getTotalNum() + ":" + statisticsInfo.getCreateBy());
        }

    }

    @Test
    public void getStatisticsInfoMap() throws Exception {
        List<Map<String, String>> infoMap = userServiceImpl.getStatisticsInfoMap();

        System.out.println(infoMap);
    }

    @Test
    public void findBySpecification() {
        Page<User> users = userServiceImpl.findByUserParam(new UserParam("test Name0", "1862194", "-1", 2, 5));

        System.out.println(users);
    }
}