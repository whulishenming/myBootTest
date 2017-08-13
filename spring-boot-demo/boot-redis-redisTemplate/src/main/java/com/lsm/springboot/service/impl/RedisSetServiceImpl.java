package com.lsm.springboot.service.impl;

import com.lsm.springboot.service.IRedisSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class RedisSetServiceImpl implements IRedisSetService{

    @Autowired
    private SetOperations<String, String> opsForSet;

    @Override
    public Long sAdd(final String key, final String... values) {
        return opsForSet.add(key, values);
    }

    @Override
    public Long sRem(final String key, final String... values) {
        return opsForSet.remove(key, values);
    }

    @Override
    public String sPop(final String key) {
        return opsForSet.pop(key);
    }

    @Override
    public List<String> sRandMember(final String key, final long count){
        return opsForSet.randomMembers(key, count);
    }

    @Override
    public Set<String> sRandMember(final long count, final String key){
        return opsForSet.distinctRandomMembers(key, count);
    }

    @Override
    public Boolean sMove(final String sourceKey, final String member, final String destKey){
        return opsForSet.move(sourceKey, member, destKey);
    }

    @Override
    public Long sCard(final String key){
        return opsForSet.size(key);
    }

    @Override
    public Boolean sIsMember(final String key, final String member) {
        return opsForSet.isMember(key, member);
    }

    @Override
    public Set<String> sInter(final String key, final List<String> otherKeys){
        return opsForSet.intersect(key, otherKeys);
    }

    @Override
    public Long sInterStore(final String key, final List<String> otherKeys, final String destKey){
        return opsForSet.intersectAndStore(key, otherKeys, destKey);
    }

    @Override
    public Set<String> sUnion(final String key, final List<String> otherKeys){
        return opsForSet.union(key, otherKeys);
    }

    @Override
    public Long sUnionStore(final String key, final List<String> otherKeys, final String destKey){
        return opsForSet.unionAndStore(key, otherKeys, destKey);
    }

    @Override
    public Set<String> sDiff(final String key, final List<String> otherKeys){
        return opsForSet.difference(key, otherKeys);
    }

    @Override
    public Long sDiffStore(final String key, final List<String> otherKeys, final String destKey){
        return opsForSet.differenceAndStore(key, otherKeys, destKey);
    }

    @Override
    public Set<String> sMembers(final String key){
        return opsForSet.members(key);
    }



}
