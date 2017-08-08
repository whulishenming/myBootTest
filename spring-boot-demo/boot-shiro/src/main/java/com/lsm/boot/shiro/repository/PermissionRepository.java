package com.lsm.boot.shiro.repository;

import com.lsm.boot.shiro.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
