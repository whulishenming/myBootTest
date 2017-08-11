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

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class IRedisListServiceTest extends BaseTest {

    @Autowired
    private IRedisListService redisListServiceImpl;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String key = "list_init";

    @Before
    public void flushDb() {
        redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
        redisListServiceImpl.lPush(key, "init 1", "init 3", "init 2", "init 3", "init 4", "init 5", "init 6", "init 3", "init 5", "init 1");
    }

    @Test
    public void testLPush() {
        long length = redisListServiceImpl.lPush(key, "test 1", "test 2");
        log.info("length: {}", length);
    }

    @Test
    public void lPushX() {
        long length1 = redisListServiceImpl.lPushX(key, "test 1");
        long length2 = redisListServiceImpl.lPushX("test_key", "test 1");
        log.info("length1: {}, length2: {}", length1, length2);
    }

    @Test
    public void testRPush() {

        long length = redisListServiceImpl.rPush(key, "test 1", "test 2");
        log.info("length: {}", length);
    }

    @Test
    public void testLPop() {
        String lPop = redisListServiceImpl.lPop(key);
        log.info("lPop: {}", lPop);
    }

    @Test
    public void bLPop() {
        String bLPop = redisListServiceImpl.bLPop("fdsfsdfds", 100, TimeUnit.SECONDS);
        log.info("bLPop: {}", bLPop);
    }

    @Test
    public void testRPop() {
        String rPop = redisListServiceImpl.rPop(key);
        log.info("rPop: {}", rPop);
    }

    @Test
    public void bRPop() {
        String bRPop = redisListServiceImpl.bRPop("fdsfsdfds", 10, TimeUnit.SECONDS);
        log.info("bRPop: {}", bRPop);
    }

    @Test
    public void lSet() {
        redisListServiceImpl.lSet(key, 2, "index_name");
    }

    @Test
    public void lInsert() {
        Long length = redisListServiceImpl.lInsert(key, "init 3", "insert_name");
        log.info("length: {}", length);
    }

    @Test
    public void lRem1() {
        Long length = redisListServiceImpl.lRem(key, 2, "init 3");
        log.info("length: {}", length);
    }

    @Test
    public void lRem2() {
        Long length = redisListServiceImpl.lRem(key, -2, "init 3");
        log.info("length: {}", length);
    }

    @Test
    public void lRem3() {
        Long length = redisListServiceImpl.lRem(key, 0, "init 3");
        log.info("length: {}", length);
    }

    @Test
    public void lTrim() {
        redisListServiceImpl.lTrim(key, 2, 4);
    }

    @Test
    public void testLLen() {
        Long length = redisListServiceImpl.lLen(key);
        log.info("length: {}", length);
    }

    @Test
    public void lIndex() {
        String value = redisListServiceImpl.lIndex(key, 2);
        log.info("value: {}", value);
    }

    @Test
    public void testLRange() {
        List<String> list = redisListServiceImpl.lRange(key, 2, 4);
        log.info("length: {}", JSONObject.toJSONString(list));
    }

    @Test
    public void rPopLPush() {
        String value = redisListServiceImpl.rPopLPush(key, "test");
        log.info("value: {}", value);
    }

    @Test
    public void bRPopLPush() {
        String value = redisListServiceImpl.bRPopLPush("test", key, 10, TimeUnit.SECONDS);
        log.info("value: {}", value);
    }

}
