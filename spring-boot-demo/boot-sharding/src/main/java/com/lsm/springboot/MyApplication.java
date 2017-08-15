package com.lsm.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by lishenming on 2017/4/18.
 * https://dangdangdotcom.github.io/sharding-jdbc/00-overview/
 * sharding 支持
 *  1. INSERT
 *  2. UPDATE
 *  3. DELETE
 *  4. SELECT主语句
        SELECT select_expr [, select_expr ...] FROM table_reference [, table_reference ...]
            [WHERE where_condition]
            [GROUP BY {col_name | position} [ASC | DESC]]
            [ORDER BY {col_name | position} [ASC | DESC], ...]
            [LIMIT {[offset,] row_count | row_count OFFSET offset}]
 */
@SpringBootApplication
public class MyApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(MyApplication.class, args);
    }
}
