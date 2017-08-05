package com.lsm.boot.jpa.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserParam {

    private String name;

    private String phoneNum;

    private String createBy;

    private int pageNum;

    private int size;
}
