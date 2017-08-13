package com.lsm.springboot.service;

import com.alibaba.fastjson.JSONObject;
import com.lsm.springboot.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@Slf4j
public class IRedisSetServiceTest extends BaseTest {
    @Autowired
    private IRedisSetService redisSetServiceImpl;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String key = "set_init";

    private static final String key2 = "set_init_2";

    @Before
    public void flushDb() {
        redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
        redisSetServiceImpl.sAdd(key, "value1", "value5", "value4", "value9", "value2", "value4");
        redisSetServiceImpl.sAdd(key2, "value11", "value5", "value14", "value9", "value12", "value24");
    }

    @Test
    public void sRem() throws Exception {
        Long sRem = redisSetServiceImpl.sRem(key, "value5", "test");

        log.info("sRem: {}", sRem);
    }

    @Test
    public void sPop() throws Exception {
        String sPop = redisSetServiceImpl.sPop(key);

        log.info("sPop: {}", sPop);
    }

    @Test
    public void sRandMember() throws Exception {
        List<String> sRandMember = redisSetServiceImpl.sRandMember(key, 2);

        log.info("sRandMember: {}", JSONObject.toJSONString(sRandMember));
    }

    @Test
    public void sRandMember2() {
        Set<String> sRandMember = redisSetServiceImpl.sRandMember(2, key);

        log.info("sRandMember: {}", JSONObject.toJSONString(sRandMember));
    }

    @Test
    public void sMove() throws Exception {
        Boolean sMove = redisSetServiceImpl.sMove(key, "value1", "test_key");

        log.info("sMove: {}", sMove);
    }

    @Test
    public void sCard() throws Exception {
        Long sCard = redisSetServiceImpl.sCard(key);

        log.info("sCard: {}", sCard);
    }

    @Test
    public void sIsMember() throws Exception {
        Boolean sIsMember1 = redisSetServiceImpl.sIsMember(key, "test");
        Boolean sIsMember2 = redisSetServiceImpl.sIsMember(key, "value1");

        log.info("sIsMember1: {}, sIsMember2: {}", sIsMember1, sIsMember2);
    }

    @Test
    public void sInter() throws Exception {
        Set<String> sInter = redisSetServiceImpl.sInter(key, Arrays.asList(key2));

        log.info("sInter: {}", JSONObject.toJSONString(sInter));
    }

    @Test
    public void sInterStore() throws Exception {
        Long sInterStore = redisSetServiceImpl.sInterStore(key, Arrays.asList(key2), "test_key");

        log.info("sInterStore: {}", sInterStore);
    }

    @Test
    public void sUnion() throws Exception {
        Set<String> sUnion = redisSetServiceImpl.sUnion(key, Arrays.asList(key2));

        log.info("sUnion: {}", JSONObject.toJSONString(sUnion));
    }

    @Test
    public void sUnionStore() throws Exception {
        Long sUnionStore = redisSetServiceImpl.sUnionStore(key, Arrays.asList(key2), "test_key");

        log.info("sUnionStore: {}", sUnionStore);
    }

    @Test
    public void sDiff() throws Exception {
        Set<String> sDiff = redisSetServiceImpl.sDiff(key, Arrays.asList(key2));

        log.info("sDiff: {}", JSONObject.toJSONString(sDiff));
    }

    @Test
    public void sDiffStore() throws Exception {
        Long sDiffStore = redisSetServiceImpl.sDiffStore(key, Arrays.asList(key2), "test_key");

        log.info("sDiffStore: {}", sDiffStore);
    }

    @Test
    public void sMembers() throws Exception {
        Set<String> sMembers = redisSetServiceImpl.sMembers(key);

        log.info("sMembers: {}", JSONObject.toJSONString(sMembers));
    }

}