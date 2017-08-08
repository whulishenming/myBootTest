package com.lsm.boot.shiro.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nick")
    private String nick;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_num")
    private String phoneNum;

    @Column(name = "last_login_time")
    private Date lastLoginTime;

    @Column(name = "status")
    private Byte status;

    @Column(name = "is_deleted")
    private Byte isDeleted;

    @Column(name = "create_user_id")
    private Long createUserId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_user_id")
    private Long updateUserId;

    @Column(name = "update_time")
    private Date updateTime;

}
