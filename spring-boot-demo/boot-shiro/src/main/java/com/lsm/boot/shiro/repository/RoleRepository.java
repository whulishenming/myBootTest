package com.lsm.boot.shiro.repository;

import com.lsm.boot.shiro.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("select role from Role role where role.id in :ids")
    List<Role> findByIds(@Param("ids") List<Long> ids);

}
