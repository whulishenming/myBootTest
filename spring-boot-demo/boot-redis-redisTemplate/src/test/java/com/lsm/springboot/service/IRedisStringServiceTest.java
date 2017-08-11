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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@Slf4j
public class IRedisStringServiceTest extends BaseTest {

    @Autowired
    private IRedisStringService redisStringServiceImpl;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String key = "string_init";

    @Before
    public void flushDb() {
        redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
        redisStringServiceImpl.set(key, "1a2b3c4d5e6f");
    }

    @Test
    public void set() throws Exception {
        redisStringServiceImpl.set(key, "test");
    }

    @Test
    public void setEx() throws Exception {
        redisStringServiceImpl.setEx("expire_key", "test", 100, TimeUnit.SECONDS);
        redisStringServiceImpl.setEx(key, "1a2b3c4d5e6f", 200, TimeUnit.SECONDS);
    }

    @Test
    public void setNx() throws Exception {
        Boolean setNx1 = redisStringServiceImpl.setNx("expire_key", "test");
        Boolean setNx2 = redisStringServiceImpl.setNx(key, "test2222");

        log.info("setNx1: {}, setNx2: {}", setNx1, setNx2);
    }

    @Test
    public void mSet() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put(key, "test1");
        map.put("test2", "test2");
        redisStringServiceImpl.mSet(map);
    }

    @Test
    public void mSetNx() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put(key, "test1");
        map.put("test2", "test2");
        Boolean setNx = redisStringServiceImpl.mSetNx(map);

        log.info("setNx: {}", setNx);
    }

    @Test
    public void get() throws Exception {
        String value = redisStringServiceImpl.get(key);

        log.info("value: {}", value);
    }

    @Test
    public void getSet() throws Exception {
        String value = redisStringServiceImpl.getSet(key, "new Value");
        String nEValue = redisStringServiceImpl.getSet("test", "new Value");

        log.info("value: {}, nEValue: {}", value, nEValue);
    }

    @Test
    public void mGet() throws Exception {
        List<String> values = redisStringServiceImpl.mGet(Arrays.asList(key, "test"));

        log.info("values: {}", JSONObject.toJSONString(values));
    }

    @Test
    public void incrBy() throws Exception {
        Long long1 = redisStringServiceImpl.incrBy("test", 6);
        Long long2 = redisStringServiceImpl.incrBy("test", -2);
        Double aDouble = redisStringServiceImpl.incrBy("test", 12.67);
        Double aDouble2 = redisStringServiceImpl.incrBy("test", -10.00d);

        log.info("long1: {}, long2: {}, aDouble: {}, aDouble2: {}", long1, long2, aDouble, aDouble2);
    }

    @Test
    public void append() throws Exception {
        Integer append1 = redisStringServiceImpl.append(key, "test");
        Integer append2 = redisStringServiceImpl.append("test_key", "test");

        log.info("append1: {}, append2: {}", append1, append2);
    }

    @Test
    public void getRange() throws Exception {
        String exRange = redisStringServiceImpl.getRange(key, 2, 5);
        String neRange = redisStringServiceImpl.getRange("test", 2, 5);

        log.info("exRange: {}, neRange: {}", exRange, neRange);
    }

    @Test
    public void setRange() throws Exception {
        redisStringServiceImpl.setRange(key, "BNM", 3);
        redisStringServiceImpl.setRange("test_key", "BNM", 3);
    }

    @Test
    public void strLen() throws Exception {
        Long length = redisStringServiceImpl.strLen(key);

        log.info("length: {}", length);
    }

    @Test
    public void setBit() throws Exception {
        // TODO
        Boolean setBit1 = redisStringServiceImpl.setBit(key, 2, true);
        Boolean setBit2 = redisStringServiceImpl.setBit("test_key", 2, true);

        log.info("setBit1: {}, setBit2: {}", setBit1, setBit2);
    }

    @Test
    public void getBit() throws Exception {
    }

}