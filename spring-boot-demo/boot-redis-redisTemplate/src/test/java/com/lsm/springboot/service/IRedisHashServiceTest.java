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

import java.util.*;

@Slf4j
public class IRedisHashServiceTest extends BaseTest {

    @Autowired
    private IRedisHashService redisHashServiceImpl;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String key = "hash_init";

    @Before
    public void flushDb() {
        redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
        Map<String, String> map = new HashMap<>();
        map.put("id", "1001");
        map.put("name", "lishenming");
        map.put("age", "25");
        map.put("phoneNum", "18621949186");
        map.put("birthday", "1992-01-16");

        redisHashServiceImpl.hMSet(key, map);
    }

    @Test
    public void hSet() throws Exception {
        redisHashServiceImpl.hSet(key, "name2", "value2");
    }

    @Test
    public void hSetNX() throws Exception {
        Boolean setNX1 = redisHashServiceImpl.hSetNX(key, "name", "lsm1");
        Boolean setNX2 = redisHashServiceImpl.hSetNX(key, "name2", "lsm2");

        log.info("setNX1: {}, setNX2: {}", setNX1, setNX2);
    }

    @Test
    public void hDel() throws Exception {
        Long del = redisHashServiceImpl.hDel(key, "name", "name2");

        log.info("del: {}", del);
    }

    @Test
    public void hExists() throws Exception {
        Boolean hExists1 = redisHashServiceImpl.hExists(key, "name");
        Boolean hExists2 = redisHashServiceImpl.hExists(key, "name2");

        log.info("hExists1: {}, hExists2: {}", hExists1, hExists2);
    }

    @Test
    public void hGet() throws Exception {
        String hGet1 = redisHashServiceImpl.hGet(key, "name");
        String hGet2 = redisHashServiceImpl.hGet(key, "name2");

        log.info("hGet1: {}, hGet2: {}", hGet1, hGet2);
    }

    @Test
    public void hMGet() throws Exception {
        List<String> hMGet = redisHashServiceImpl.hMGet(key, Arrays.asList("name", "name2", "age"));

        log.info("hMGet: {}", JSONObject.toJSONString(hMGet));
    }

    @Test
    public void hIncrBy() throws Exception {
        Long age1 = redisHashServiceImpl.hIncrBy(key, "age2", 18);
        Long age2 = redisHashServiceImpl.hIncrBy(key, "age2", -5);
        Double age3 = redisHashServiceImpl.hIncrBy(key, "age2", 1.5);
        Double age4 = redisHashServiceImpl.hIncrBy(key, "age2", -3.5);

        log.info("age1: {}, age2: {}, age3: {}, age4: {}", age1, age2, age3, age4);
    }

    @Test
    public void hKeys() throws Exception {
        Set<String> hKeys = redisHashServiceImpl.hKeys(key);

        log.info("hKeys: {}", JSONObject.toJSONString(hKeys));
    }

    @Test
    public void hLen() throws Exception {
        Long hLen = redisHashServiceImpl.hLen(key);

        log.info("hLen: {}", hLen);
    }

    @Test
    public void hVals() throws Exception {
        List<String> hVals = redisHashServiceImpl.hVals(key);

        log.info("hVals: {}", hVals);
    }

    @Test
    public void hGetAll() throws Exception {
        Map<String, String> map = redisHashServiceImpl.hGetAll(key);

        log.info("map: {}", JSONObject.toJSONString(map));
    }

}