package com.lsm.boot.shiro.repository;

import com.lsm.boot.shiro.model.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {
}
