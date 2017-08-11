package com.lsm.springboot.service.impl;

import com.lsm.springboot.service.IRedisListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RedisListServiceImpl implements IRedisListService {

    @Autowired
    private ListOperations<String, String> opsForList;

    @Override
    public long lPush(final String key, final String... value) {
        return opsForList.leftPushAll(key, value);
    }

    @Override
    public long lPushX(final String key, final String value) {
        return opsForList.leftPushIfPresent(key, value);
    }

    @Override
    public long rPush(final String key, final String... value) {
        return opsForList.rightPushAll(key, value);
    }

    @Override
    public String lPop(final String key) {
        return opsForList.leftPop(key);
    }

    @Override
    public String bLPop(final String key, final long timeout, final TimeUnit unit) {
        return opsForList.leftPop(key, timeout, unit);
    }

    @Override
    public String rPop(final String key) {
        return opsForList.rightPop(key);
    }

    @Override
    public String bRPop(final String key, final long timeout, final TimeUnit unit) {
        return opsForList.rightPop(key, timeout, unit);
    }

    @Override
    public void lSet(final String key, final long index, final String value) {
        opsForList.set(key, index, value);
    }

    @Override
    public Long lInsert(final String key, final String pivot, final String value) {
        return opsForList.leftPush(key, pivot, value);
    }

    @Override
    public Long lRem(final String key, final long count, final String value) {
        return opsForList.remove(key, count, value);
    }

    @Override
    public void lTrim(final String key, final long start, final long end) {
        opsForList.trim(key, start, end);
    }

    @Override
    public Long lLen(final String key) {
        return opsForList.size(key);
    }

    @Override
    public String lIndex(final String key, final long index) {
        return opsForList.index(key, index);
    }

    @Override
    public List<String> lRange (final String key, final long start, final long end) {
        return opsForList.range(key, start, end);
    }

    @Override
    public String rPopLPush(final String sourceKey, final String destKye) {
        return opsForList.rightPopAndLeftPush(sourceKey, destKye);
    }

    @Override
    public String bRPopLPush(final String sourceKey, final String destKye, final long timeout, final TimeUnit unit) {
        return opsForList.rightPopAndLeftPush(sourceKey, destKye, timeout, unit);
    }
}
