package com.lsm.springboot.service;

import com.alibaba.fastjson.JSONObject;
import com.lsm.springboot.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class IRedisZSetServiceTest extends BaseTest {

    @Autowired
    private IRedisZSetService redisZSetServiceImpl;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String key = "z_set_init";

    @Before
    public void flushDb() {
        redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
        Set<ZSetOperations.TypedTuple<String>> tuples = new HashSet<>();
        ZSetOperations.TypedTuple<String> tuple1 = new DefaultTypedTuple<>("nanjing", Double.parseDouble("90"));
        ZSetOperations.TypedTuple<String> tuple2 = new DefaultTypedTuple<>("shanghai", Double.parseDouble("95"));
        ZSetOperations.TypedTuple<String> tuple3 = new DefaultTypedTuple<>("nanchang", Double.parseDouble("70"));
        ZSetOperations.TypedTuple<String> tuple4 = new DefaultTypedTuple<>("jiujiang", Double.parseDouble("75"));
        ZSetOperations.TypedTuple<String> tuple5 = new DefaultTypedTuple<>("wuxi", Double.parseDouble("82"));
        ZSetOperations.TypedTuple<String> tuple6 = new DefaultTypedTuple<>("beijing", Double.parseDouble("85"));
        ZSetOperations.TypedTuple<String> tuple7 = new DefaultTypedTuple<>("hefei", Double.parseDouble("65"));
        ZSetOperations.TypedTuple<String> tuple8 = new DefaultTypedTuple<>("changsha", Double.parseDouble("85"));
        ZSetOperations.TypedTuple<String> tuple9 = new DefaultTypedTuple<>("shuzhou", Double.parseDouble("82"));
        ZSetOperations.TypedTuple<String> tuple10 = new DefaultTypedTuple<>("hangzhou", Double.parseDouble("88"));
        tuples.add(tuple1);
        tuples.add(tuple2);
        tuples.add(tuple3);
        tuples.add(tuple4);
        tuples.add(tuple5);
        tuples.add(tuple6);
        tuples.add(tuple7);
        tuples.add(tuple8);
        tuples.add(tuple9);
        tuples.add(tuple10);
        redisZSetServiceImpl.zAdd(key, tuples);
    }

    @Test
    public void zAdd() throws Exception {
        Boolean add = redisZSetServiceImpl.zAdd("z_add", "nanjing", 100);
        log.info("add success: {}", add);
    }

    @Test
    public void zAdd1() throws Exception {
        Set<ZSetOperations.TypedTuple<String>> tuples = new HashSet<>();
        ZSetOperations.TypedTuple<String> tuple = new DefaultTypedTuple<>("nanjing", Double.parseDouble("100"));
        ZSetOperations.TypedTuple<String> tuple2 = new DefaultTypedTuple<>("shanghai", Double.parseDouble("101"));
        tuples.add(tuple);
        tuples.add(tuple2);
        Long add = redisZSetServiceImpl.zAdd("z_add", tuples);
        // 更新
        redisZSetServiceImpl.zAdd("z_add", "nanjing", 90);
        log.info("add success num: {}", add);
    }

    @Test
    public void zCard() throws Exception {
        Long count = redisZSetServiceImpl.zCard(key);
        log.info("total count: {}", count);
    }

    @Test
    public void zCount() throws Exception {
        Long count = redisZSetServiceImpl.zCount(key, 82, 90);
        log.info("total count: {}", count);
    }

    @Test
    public void zIncrby() throws Exception {
        Double njScore = redisZSetServiceImpl.zIncrby(key, "test", 2.1);
        Double ncScore = redisZSetServiceImpl.zIncrby(key, "nanchang", -2.1);
        log.info("nanjing score: {}, nanchang score: {}", njScore, ncScore);
    }

    @Test
    public void zInterstore() throws Exception {
        Set<ZSetOperations.TypedTuple<String>> tuples1 = new HashSet<>();
        ZSetOperations.TypedTuple<String> tuple11 = new DefaultTypedTuple<>("nanjing", Double.parseDouble("190"));
        ZSetOperations.TypedTuple<String> tuple12 = new DefaultTypedTuple<>("shanghai", Double.parseDouble("195"));
        ZSetOperations.TypedTuple<String> tuple13 = new DefaultTypedTuple<>("test", Double.parseDouble("100"));
        tuples1.add(tuple11);
        tuples1.add(tuple12);
        tuples1.add(tuple13);
        Set<ZSetOperations.TypedTuple<String>> tuples2 = new HashSet<>();
        ZSetOperations.TypedTuple<String> tuple21 = new DefaultTypedTuple<>("nanjing", Double.parseDouble("1070"));
        ZSetOperations.TypedTuple<String> tuple22 = new DefaultTypedTuple<>("test2", Double.parseDouble("1075"));
        tuples2.add(tuple21);
        tuples2.add(tuple22);
        Set<ZSetOperations.TypedTuple<String>> tuples3 = new HashSet<>();
        ZSetOperations.TypedTuple<String> tuple31 = new DefaultTypedTuple<>("nanjing", Double.parseDouble("10090"));
        ZSetOperations.TypedTuple<String> tuple32 = new DefaultTypedTuple<>("shanghai", Double.parseDouble("10095"));
        ZSetOperations.TypedTuple<String> tuple33 = new DefaultTypedTuple<>("test3", Double.parseDouble("10070"));
        ZSetOperations.TypedTuple<String> tuple34 = new DefaultTypedTuple<>("jiujiang", Double.parseDouble("10075"));
        tuples3.add(tuple31);
        tuples3.add(tuple32);
        tuples3.add(tuple33);
        tuples3.add(tuple34);
        redisZSetServiceImpl.zAdd("tuples1", tuples1);
        redisZSetServiceImpl.zAdd("tuples2", tuples2);
        redisZSetServiceImpl.zAdd("tuples3", tuples3);
        Long interstore = redisZSetServiceImpl.zInterstore(key, Arrays.asList("tuples1", "tuples2", "tuples3"), "destKey");
        log.info("interstore count: {}", interstore);
    }

    @Test
    public void zUnionStore() throws Exception {
        Set<ZSetOperations.TypedTuple<String>> tuples1 = new HashSet<>();
        ZSetOperations.TypedTuple<String> tuple11 = new DefaultTypedTuple<>("nanjing", Double.parseDouble("190"));
        ZSetOperations.TypedTuple<String> tuple12 = new DefaultTypedTuple<>("shanghai", Double.parseDouble("195"));
        ZSetOperations.TypedTuple<String> tuple13 = new DefaultTypedTuple<>("test", Double.parseDouble("100"));
        tuples1.add(tuple11);
        tuples1.add(tuple12);
        tuples1.add(tuple13);
        Set<ZSetOperations.TypedTuple<String>> tuples2 = new HashSet<>();
        ZSetOperations.TypedTuple<String> tuple21 = new DefaultTypedTuple<>("nanjing", Double.parseDouble("1070"));
        ZSetOperations.TypedTuple<String> tuple22 = new DefaultTypedTuple<>("test2", Double.parseDouble("1075"));
        tuples2.add(tuple21);
        tuples2.add(tuple22);
        Set<ZSetOperations.TypedTuple<String>> tuples3 = new HashSet<>();
        ZSetOperations.TypedTuple<String> tuple31 = new DefaultTypedTuple<>("nanjing", Double.parseDouble("10090"));
        ZSetOperations.TypedTuple<String> tuple32 = new DefaultTypedTuple<>("shanghai", Double.parseDouble("10095"));
        ZSetOperations.TypedTuple<String> tuple33 = new DefaultTypedTuple<>("test3", Double.parseDouble("10070"));
        ZSetOperations.TypedTuple<String> tuple34 = new DefaultTypedTuple<>("jiujiang", Double.parseDouble("10075"));
        tuples3.add(tuple31);
        tuples3.add(tuple32);
        tuples3.add(tuple33);
        tuples3.add(tuple34);
        redisZSetServiceImpl.zAdd("tuples1", tuples1);
        redisZSetServiceImpl.zAdd("tuples2", tuples2);
        redisZSetServiceImpl.zAdd("tuples3", tuples3);
        Long unionStore = redisZSetServiceImpl.zUnionStore(key, Arrays.asList("tuples1", "tuples2", "tuples3"), "destKey");
        log.info("unionStore count: {}", unionStore);
    }

    @Test
    public void zRange() throws Exception {
        Set<String> range = redisZSetServiceImpl.zRange(key, 2, 5);
        Set<String> range2 = redisZSetServiceImpl.zRange(key, 2, -2);
        log.info("range: {}, range2: {}", JSONObject.toJSON(range), JSONObject.toJSON(range2));
    }

    @Test
    public void zRevRange() throws Exception {
        Set<String> range = redisZSetServiceImpl.zRevRange(key, 2, 5);
        Set<String> range2 = redisZSetServiceImpl.zRevRange(key, 2, -2);
        log.info("range: {}, range2: {}", JSONObject.toJSON(range), JSONObject.toJSON(range2));
    }

    @Test
    public void zRangeWithScores() throws Exception {
        Set<ZSetOperations.TypedTuple<String>> typedTuples = redisZSetServiceImpl.zRangeWithScores(key, 2, 5);
        Set<ZSetOperations.TypedTuple<String>> typedTuples2 = redisZSetServiceImpl.zRangeWithScores(key, 2, -2);
        log.info("typedTuples: {}, typedTuples2: {}", JSONObject.toJSON(typedTuples), JSONObject.toJSON(typedTuples2));
    }

    @Test
    public void zRevRangeWithScores() throws Exception {
        Set<ZSetOperations.TypedTuple<String>> typedTuples = redisZSetServiceImpl.zRevRangeWithScores(key, 2, 5);
        Set<ZSetOperations.TypedTuple<String>> typedTuples2 = redisZSetServiceImpl.zRevRangeWithScores(key, 2, -2);
        log.info("typedTuples: {}, typedTuples2: {}", JSONObject.toJSON(typedTuples), JSONObject.toJSON(typedTuples2));
    }

    @Test
    public void zRangeByScore() throws Exception {
        Set<String> rangeByScore = redisZSetServiceImpl.zRangeByScore(key, 82, 90);
        log.info("rangeByScore: {}", JSONObject.toJSON(rangeByScore));
    }

    @Test
    public void zRevRangeByScore() throws Exception {
        Set<String> rangeByScore = redisZSetServiceImpl.zRevRangeByScore(key, 82, 90);
        log.info("rangeByScore: {}", JSONObject.toJSON(rangeByScore));
    }

    @Test
    public void zRangeByScore1() throws Exception {
        Set<String> rangeByScore = redisZSetServiceImpl.zRangeByScore(key, 82, 90, 1, 2);
        log.info("rangeByScore: {}", JSONObject.toJSON(rangeByScore));
    }

    @Test
    public void zRevRangeByScore1() throws Exception {
        Set<String> rangeByScore = redisZSetServiceImpl.zRevRangeByScore(key, 82, 90, 1, 2);
        log.info("rangeByScore: {}", JSONObject.toJSON(rangeByScore));
    }

    @Test
    public void zRangeByScoreWithScores() throws Exception {
        Set<ZSetOperations.TypedTuple<String>> tuples = redisZSetServiceImpl.zRangeByScoreWithScores(key, 82, 90);
        log.info("tuples: {}", JSONObject.toJSON(tuples));
    }

    @Test
    public void zRevRangeByScoreWithScores() throws Exception {
        Set<ZSetOperations.TypedTuple<String>> tuples = redisZSetServiceImpl.zRevRangeByScoreWithScores(key, 82, 90);
        log.info("tuples: {}", JSONObject.toJSON(tuples));
    }

    @Test
    public void zRangeByScoreWithScores1() throws Exception {
        Set<ZSetOperations.TypedTuple<String>> tuples = redisZSetServiceImpl.zRangeByScoreWithScores(key, 82, 90, 2, 2);
        log.info("tuples: {}", JSONObject.toJSON(tuples));
    }

    @Test
    public void zRevRangeByScoreWithScores1() throws Exception {
        Set<ZSetOperations.TypedTuple<String>> tuples = redisZSetServiceImpl.zRevRangeByScoreWithScores(key, 82, 90, 2, 2);
        log.info("tuples: {}", JSONObject.toJSON(tuples));
    }

    @Test
    public void zRank() throws Exception {
        Long rank = redisZSetServiceImpl.zRank(key, "nanjing");
        log.info("rank: {}", JSONObject.toJSON(rank));
    }

    @Test
    public void zRevRank() throws Exception {
        Long revRank = redisZSetServiceImpl.zRevRank(key, "nanjing");
        log.info("revRank: {}", JSONObject.toJSON(revRank));
    }

    @Test
    public void zRem() throws Exception {
        Long rem = redisZSetServiceImpl.zRem(key, "nanjing", "nanchang", "test");
        log.info("rem: {}", JSONObject.toJSON(rem));
    }

    @Test
    public void zRemRangeByRank() throws Exception {
        Long rangeByRank = redisZSetServiceImpl.zRemRangeByRank(key, 2, 5);
        log.info("rangeByRank: {}", JSONObject.toJSON(rangeByRank));
    }

    @Test
    public void zRemRangeByScore() throws Exception {
        Long remRangeByScore = redisZSetServiceImpl.zRemRangeByScore(key, 82, 90);
        log.info("remRangeByScore: {}", JSONObject.toJSON(remRangeByScore));
    }

    @Test
    public void zScore() throws Exception {
        Double score = redisZSetServiceImpl.zScore(key, "nanjing");
        log.info("score: {}", JSONObject.toJSON(score));
    }

}