package com.lsm.springboot.service;

import com.lsm.springboot.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@Slf4j
public class IRedisKeyServiceTest extends BaseTest {

    @Autowired
    private IRedisKeyService redisKeyServiceImpl;
    @Autowired
    private IRedisListService redisListServiceImpl;
    @Autowired
    private IRedisHashService redisHashServiceImpl;
    @Autowired
    private IRedisSetService redisSetServiceImpl;
    @Autowired
    private IRedisStringService redisStringServiceImpl;
    @Autowired
    private IRedisZSetService redisZSetServiceImpl;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String STRING_KEY = "string_init";
    private static final String HASH_KEY = "hash_init";
    private static final String LIST_KEY = "list_init";
    private static final String SET_KEY = "set_init";
    private static final String Z_SET_KEY = "z_set_init";

    @Before
    public void flushDb() {
        redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
        redisStringServiceImpl.set(STRING_KEY, "test_string");
        redisHashServiceImpl.hSet(HASH_KEY, "test_field", "test_value");
        redisListServiceImpl.lPush(LIST_KEY, "test_list_push");
        redisSetServiceImpl.sAdd(SET_KEY, "test_set");
        redisZSetServiceImpl.zAdd(Z_SET_KEY, "test_z_set", 88);
    }


    @Test
    public void del() throws Exception {
        redisKeyServiceImpl.del(Arrays.asList(Z_SET_KEY, SET_KEY, "test_key"));
    }

    @Test
    public void exists() throws Exception {
        Boolean exists1 = redisKeyServiceImpl.exists(HASH_KEY);
        Boolean exists2 = redisKeyServiceImpl.exists("test_key");

        log.info("exists1: {}, exists2: {}", exists1, exists2);
    }

    @Test
    public void expire() throws Exception {
        Boolean expire = redisKeyServiceImpl.expire(LIST_KEY, 100, TimeUnit.SECONDS);

        log.info("expire: {}", expire);
    }

    @Test
    public void expireAt() throws Exception {
        Calendar calendar = new GregorianCalendar(2017,8,14);
        Boolean expireAt = redisKeyServiceImpl.expireAt(HASH_KEY, calendar.getTime());

        log.info("expireAt: {}", expireAt);
    }

    @Test
    public void ttl() throws Exception {
        redisKeyServiceImpl.expire(LIST_KEY, 100, TimeUnit.SECONDS);
        Long ttl1 = redisKeyServiceImpl.ttl(LIST_KEY, TimeUnit.SECONDS);
        Long ttl2 = redisKeyServiceImpl.ttl(SET_KEY, TimeUnit.SECONDS);

        log.info("ttl1: {}, ttl2: {}", ttl1, ttl2);
    }

    @Test
    public void randomKey() throws Exception {
        String randomKey = redisKeyServiceImpl.randomKey();

        log.info("randomKey: {}", randomKey);
    }

    @Test
    public void type() throws Exception {
        DataType dataType = redisKeyServiceImpl.type(Z_SET_KEY);

        log.info("dataType: {}", dataType.code());
    }

}