package com.lsm.boot.shiro.repository;

import com.lsm.boot.shiro.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    List<UserRole> findByUserIdAndIsDeleted(Long userId, byte isDeleted);
}
