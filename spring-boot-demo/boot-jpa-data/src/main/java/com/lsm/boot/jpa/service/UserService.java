package com.lsm.boot.jpa.service;

import com.lsm.boot.jpa.model.User;
import com.lsm.boot.jpa.model.UserStatisticsInfo;
import com.lsm.boot.jpa.param.UserParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserService {

    void save(User user);

    void batchInsert(List<User> list);

    List<User> findByName(String name);

    User findFirstByName(String name);

    List<User> findByPhoneNumLike(String phoneNum);

    List<User> findByPhoneNumStartingWith(String phoneNum);

    List<User> findByPhoneNumContaining(String phoneNum);

    Page<User> findByName(String phoneNum, Pageable pageable);

    List<User> findByQuery(String createBy, Date createTime);

    List<User> findByQuery2(String createBy, Date createTime);

    int updateById(Long id, String name, String updateBy);

    int updateByIds(List<Long> ids, String name, String updateBy);

    List<UserStatisticsInfo> getStatisticsInfos();

    List<Map<String, String>> getStatisticsInfoMap();

    Page<User> findByUserParam(UserParam param);

}
