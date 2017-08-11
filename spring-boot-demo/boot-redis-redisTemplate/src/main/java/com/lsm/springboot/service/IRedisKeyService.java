package com.lsm.springboot.service;

import org.springframework.data.redis.connection.DataType;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public interface IRedisKeyService {

    /**
     * 用于删除已存在的键。不存在的 key 会被忽略
     */
    void del(List<String> keys);

    /**
     * 检查给定 key 是否存在
     */
    Boolean exists(String key);

    /**
     * 为给定 key 设置过期时间
     */
    Boolean expire(String key, long expireTime, TimeUnit unit);

    /**
     * 为给定 key 设置过期时间
     */
    Boolean expireAt(String key, Date date);

    /**
     * 返回 key 的剩余的过期时间
     */
    Long ttl(String key, TimeUnit timeUnit);

    /**
     * 从当前数据库中随机返回一个 key
     * @return
     */
    String randomKey();

    /**
     * 返回 key 所储存的值的类型
     */
    DataType type(String key);
}
