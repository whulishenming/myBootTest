package com.lsm.springboot.service.impl;

import com.lsm.springboot.service.IRedisStringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class RedisStringServiceImpl implements IRedisStringService {

    @Autowired
    private ValueOperations<String, String> opsForValue;

    @Override
    public void set(final String key, final String value) {
        opsForValue.set(key, value);
    }

    @Override
    public void setEx(final String key, final String value, long expire, TimeUnit unit){
        opsForValue.set(key, value, expire, unit);
    }

    @Override
    public Boolean setNx(final String key, final String value){
        return opsForValue.setIfAbsent(key, value);
    }

    @Override
    public void mSet(final Map<String, String> map) {
        opsForValue.multiSet(map);
    }

    @Override
    public Boolean mSetNx(final Map<String, String> map){
        return opsForValue.multiSetIfAbsent(map);
    }

    @Override
    public String get(final String key) {
        return opsForValue.get(key);
    }

    @Override
    public String getSet(final String key, final String newValue) {
        return opsForValue.getAndSet(key, newValue);
    }

    @Override
    public List<String> mGet(final List<String> keys){
        return opsForValue.multiGet(keys);
    }

    @Override
    public Long incrBy(final String key, final long delta){
        return opsForValue.increment(key, delta);
    }

    @Override
    public Double incrBy(final String key, final double delta){
        return opsForValue.increment(key, delta);
    }

    @Override
    public Integer append(final String key, final String value) {
        return opsForValue.append(key, value);
    }

    @Override
    public String getRange(final String key, final long start, final long end) {
        return opsForValue.get(key, start, end);
    }

    @Override
    public void setRange(final String key, final String value, final long offset) {
        opsForValue.set(key, value, offset);
    }

    @Override
    public Long strLen(final String key) {
        return opsForValue.size(key);
    }

    @Override
    public Boolean setBit(final String key, final long offset, final boolean value) {
        return opsForValue.setBit(key, offset, value);
    }

    @Override
    public Boolean getBit(final String key, final long offset) {
        return opsForValue.getBit(key, offset);
    }
}
