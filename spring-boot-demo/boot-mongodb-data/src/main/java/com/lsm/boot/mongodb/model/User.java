package com.lsm.boot.mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "c_user")
public class User {

    @Id
    private String id;

    private String name;

    private String password;

    private String phoneNum;

    private Byte isDeleted;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;
}
