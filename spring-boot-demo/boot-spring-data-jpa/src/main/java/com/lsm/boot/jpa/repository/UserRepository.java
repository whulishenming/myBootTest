package com.lsm.boot.jpa.repository;

import com.lsm.boot.jpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByNameAndIsDeleted(String name, byte isDeleted);

    User findByName(String name);

    List<User> findAllByName(String name);

    List<User> findByPhoneNumLike(String phoneNum);

    @Query("select u from User u where u.createBy = ?1 and u.createTime > ?2")
    List<User> findByQuery(String createBy, Date createTime);

    @Query("select u from User u where u.createBy = :createBy and u.createTime > :createTime")
    List<User> findByQuery2(@Param("createBy") String createBy, @Param("createTime") Date createTime);

    @Modifying
    @Query("update User u set u.name = :name, u.updateTime = :updateTime, u.updateBy = :updateBy where u.id = :id")
    int updateById(Long id, String name, Date updateTime, String updateBy);
}
