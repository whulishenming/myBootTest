package com.lsm.boot.shiro.repository;

import com.lsm.boot.shiro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByUserNameAndPassword(String userName, String password);

    void updateLastLoginTimeById(Date LastLoginTime, Long id);
}
