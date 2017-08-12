package com.lsm.springboot.service.impl;

import com.lsm.springboot.service.IRedisHashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class RedisHashServiceImpl implements IRedisHashService {

    @Autowired
    private HashOperations<String, String, String> opsForHash;

    @Override
    public void hSet(String key, String field, String value){
        opsForHash.put(key, field, value);
    }

    @Override
    public void hMSet(String key, Map<String, String> map){
        opsForHash.putAll(key, map);
    }

    @Override
    public Boolean hSetNX(String key, String field, String value){
        return opsForHash.putIfAbsent(key, field, value);
    }

    @Override
    public Long hDel(final String key, String... field){
        return opsForHash.delete(key, field);
    }

    @Override
    public Boolean hExists(final String key, String field){
        return opsForHash.hasKey(key, field);
    }

    @Override
    public String hGet(final String key, final String field) {
        return opsForHash.get(key, field);
    }

    @Override
    public List<String> hMGet(final String key, final List<String> fields) {
        return opsForHash.multiGet(key, fields);
    }

    @Override
    public Long hIncrBy(final String key, final String field, final long increment){
        return opsForHash.increment(key, field, increment);
    }

    @Override
    public Double hIncrBy(final String key, final String field, final double increment){
        return opsForHash.increment(key, field, increment);
    }

    @Override
    public Set<String> hKeys(final String key){
        return opsForHash.keys(key);
    }

    @Override
    public Long hLen(final String key){
        return opsForHash.size(key);
    }

    @Override
    public  List<String> hVals(String key){
        return opsForHash.values(key);
    }

    @Override
    public Map<String, String> hGetAll(String key){
        return opsForHash.entries(key);
    }
}
