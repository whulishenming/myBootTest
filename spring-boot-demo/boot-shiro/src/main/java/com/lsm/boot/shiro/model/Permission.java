package com.lsm.boot.shiro.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_permission")
@Data
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url")
    private String url;

    @Column(name = "desc")
    private String desc;

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
