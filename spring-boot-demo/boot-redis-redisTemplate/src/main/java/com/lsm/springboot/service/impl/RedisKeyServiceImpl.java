package com.lsm.springboot.service.impl;

import com.lsm.springboot.service.IRedisKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RedisKeyServiceImpl implements IRedisKeyService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void del(final List<String> keys) {
        redisTemplate.delete(keys);
    }

    @Override
    public Boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public Boolean expire(final String key, final long expireTime, final TimeUnit unit) {
        return redisTemplate.expire(key, expireTime, unit);
    }

    @Override
    public Boolean expireAt(final String key, final Date date) {
        return redisTemplate.expireAt(key, date);
    }

    @Override
    public Long ttl(final String key, final TimeUnit timeUnit) {
        return redisTemplate.getExpire(key, timeUnit);
    }

    @Override
    public String randomKey() {
        return redisTemplate.randomKey();
    }

    @Override
    public DataType type(final String key) {
        return redisTemplate.type(key);
    }
}
