package com.lsm.springboot.service.impl;

import com.lsm.springboot.service.IRedisZSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RedisZSetServiceImpl implements IRedisZSetService {

    @Autowired
    private ZSetOperations<String, String> opsForZSet;

    @Override
    public Boolean zAdd(final String key, final String member, final double score) {
        return opsForZSet.add(key, member, score);
    }

    @Override
    public Long zAdd(final String key, final Set<ZSetOperations.TypedTuple<String>> tuples) {
        return opsForZSet.add(key, tuples);
    }

    @Override
    public Long zCard(final String key) {
        // opsForZSet.size(key)
        return opsForZSet.zCard(key);
    }

    @Override
    public Long zCount(final String key, final double min, final double max){
        return opsForZSet.count(key, min, max);
    }

    @Override
    public Double zIncrby(final String key, final String member, final double increment){
        return opsForZSet.incrementScore(key, member, increment);
    }

    @Override
    public Long zInterstore(final String key, final List<String> otherKeys, final String destKey){
        return opsForZSet.intersectAndStore(key, otherKeys, destKey);
    }

    @Override
    public Long zUnionStore(final String key, final List<String> otherKeys, final String destKey){
        return opsForZSet.unionAndStore(key, otherKeys, destKey);
    }

    @Override
    public Set<String> zRange(final String key, final long start, final long end){
        return opsForZSet.range(key, start, end);
    }

    @Override
    public Set<ZSetOperations.TypedTuple<String>> zRangeWithScores(final String key, final long start, final long end){
        return opsForZSet.rangeWithScores(key, start, end);
    }

    @Override
    public Set<String> zRangeByLex(final String key, final RedisZSetCommands.Range range, final RedisZSetCommands.Limit limit){

        return opsForZSet.rangeByLex(key, range, limit);
    }

    @Override
    public Set<String> zRangeByScore(final String key, final double min, final double max){
        return opsForZSet.rangeByScore(key, min, max);
    }

    @Override
    public Set<String> zRangeByScore(final String key, final double min, final double max, final long offset, final long count){
        return opsForZSet.rangeByScore(key, min, max, offset, count);
    }

    @Override
    public Set<ZSetOperations.TypedTuple<String>> zRangeByScoreWithScores(final String key, final double min, final double max){
        return opsForZSet.rangeByScoreWithScores(key, min, max);
    }

    @Override
    public Set<ZSetOperations.TypedTuple<String>> zRangeByScoreWithScores(final String key, final double min, final double max, final long offset, final long count){
        return opsForZSet.rangeByScoreWithScores(key, min, max, offset, count);
    }

    @Override
    public Long zRank(final String key, final String member){
        return opsForZSet.rank(key, member);
    }

    @Override
    public Long zRem(final String key, final String... members){
        return opsForZSet.remove(key, members);
    }

    @Override
    public Long zRemRangeByRank(final String key, final long start, final long end){
        return opsForZSet.removeRange(key, start, end);
    }

    @Override
    public Long zRemRangeByScore(final String key, final double min, final double max){
        return opsForZSet.removeRangeByScore(key, min, max);
    }

    @Override
    public Set<String> zRevRange(final String key, final long start, final long end){
        return opsForZSet.reverseRange(key, start, end);
    }

    @Override
    public Set<ZSetOperations.TypedTuple<String>> zRevRangeWithScores(final String key, final long start, final long end){
        return opsForZSet.reverseRangeWithScores(key, start, end);
    }

    @Override
    public Set<String> zRevRangeByScore(final String key, final double min, final double max){
        return opsForZSet.reverseRangeByScore(key, min, max);
    }

    @Override
    public Set<String> zRevRangeByScore(final String key, final double min, final double max, final long offset, final long count){
        return opsForZSet.reverseRangeByScore(key, min, max, offset, count);
    }

    @Override
    public Set<ZSetOperations.TypedTuple<String>> zRevRangeByScoreWithScores(final String key, final double min, final double max){
        return opsForZSet.reverseRangeByScoreWithScores(key, min, max);
    }

    @Override
    public Set<ZSetOperations.TypedTuple<String>> zRevRangeByScoreWithScores(final String key, final double min, final double max, final long offset, final long count){
        return opsForZSet.reverseRangeByScoreWithScores(key, min, max, offset, count);
    }

    @Override
    public Long zRevRank(final String key, final String member){
        return opsForZSet.reverseRank(key, member);
    }

    @Override
    public Double zScore(final String key, final String member){
        return opsForZSet.score(key, member);
    }

}
