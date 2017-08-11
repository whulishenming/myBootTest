package com.lsm.springboot.service;

import org.springframework.data.redis.core.ZSetOperations;

import java.util.List;
import java.util.Set;

/**
 * 对有序集合的操作
 *  1. Redis中的有序集合类型，实际上是在集合类型上，为每个元素都关联一个分数，有序实际上说的是分数有序，我们根据分数的范围获取集合及其他操作
 *  2. 有序集合类型使用的散列表和跳跃表（Skip list）实现
 *  3. 有序集合可以（通过改变分数）调整元素的位置
 */
public interface IRedisZSetService {

    /**
     * 增加元素,如果member存在，则score会覆盖原有的分数
     *  1. 返回是否成功
     */
    Boolean zAdd(String key, String member, double score);

    /**
     * 增加多个元素,如果member存在，则score会覆盖原有的分数
     *  1. 返回成功增加的元素的个数
     */
    Long zAdd(String key, Set<ZSetOperations.TypedTuple<String>> tuples);

    /**
     * 计算集合中元素的数量
     *  1. 当 key 存在时，返回有序集的基数
     *  2. 当 key 不存在时，返回 0
     */
    Long zCard(String key);

    /**
     * 计算有序集合中指定分数区间的成员数量
     * min 和 max 都是闭区间
     */
    Long zCount (String key, double min, double max);

    /**
     * 对有序集合中指定成员的分数加上增量 increment
     *  1. 可以是一个负数值 increment ，让分数减去相应的值
     *  2. 当 key 不存在或者 member 不存在时，相当于zAdd(key, member, score)
     *  3. 返回新的分数
     */
    Double zIncrby(String key, String member, double increment);

    /**
     * 计算 key 和 otherKeys 的交集，并储存到 destKey， 结果集中某个成员的分数值是所有给定集下该成员分数值之和
     * 返回交集成员的数量
     */
    Long zInterstore(String key, List<String> otherKeys, String destKey);

    /**
     * 计算 key 和 otherKeys 的并集，并储存到 destKey， 结果集中某个成员的分数值是所有给定集下该成员分数值之和
     * 返回并集成员的数量
     */
    Long zUnionStore (String key, List<String> otherKeys, String destKey);

    /**
     * 返回有序集中，指定区间内的成员
     *  1. 成员的位置按分数值递增(从小到大)来排序
     *  2. start 和 stop 都以 0 为底
     *  3.  -1 表示最后一个成员， -2 表示倒数第二个成员
     */
    Set<String> zRange(String key, long start, long end);

    /**
     * 返回有序集中，指定区间内的成员
     *  1. 成员的位置按分数值递减(从大到小)来排列
     *  2. start 和 stop 都是倒序的序号
     */
    Set<String> zRevRange(String key, long start, long end);

    /**
     * 返回有序集中包含分数，其他同 zRange(String key, long start, long end)
     */
    Set<ZSetOperations.TypedTuple<String>> zRangeWithScores(String key, long start, long end);

    Set<ZSetOperations.TypedTuple<String>> zRevRangeWithScores(String key, long start, long end);

    /**
     * 返回有序集合中指定分数区间的成员列表。有序集成员按分数值递增(从小到大)次序排列
     *  1. 区间的取值使用闭区间
     */
    Set<String> zRangeByScore(String key, double min, double max);

    Set<String> zRevRangeByScore(String key, double min, double max);

    /**
     * offset 表示从第 offset 个开始，查找 count 个
     *  1. offset从0开始
     */
    Set<String> zRangeByScore(String key, double min, double max, long offset, long count);

    Set<String> zRevRangeByScore(String key, double min, double max, long offset, long count);

    Set<ZSetOperations.TypedTuple<String>> zRangeByScoreWithScores(String key, double min, double max);

    Set<ZSetOperations.TypedTuple<String>> zRevRangeByScoreWithScores(String key, double min, double max);

    Set<ZSetOperations.TypedTuple<String>> zRangeByScoreWithScores(String key, double min, double max, long offset, long count);

    Set<ZSetOperations.TypedTuple<String>> zRevRangeByScoreWithScores(String key, double min, double max, long offset, long count);

    /**
     * 返回有序集中指定成员的排名,其中有序集成员按分数值递增(从小到大)顺序排列
     *  1. 排名以 0 为底
     */
    Long zRank(String key, String member);

    /**
     * 返回有序集中成员的排名。其中有序集成员按分数值递减(从大到小)排序
     *  1. 排名以 0 为底
     */
    Long zRevRank (String key, String member);

    /**
     * 用于移除有序集中的一个或多个成员，不存在的成员将被忽略
     */
    Long zRem(String key, String... members);

    /**
     * 移除有序集中，指定排名(rank)区间内的所有成员
     */
    Long zRemRangeByRank(String key, long start, long end);

    /**
     * 移除有序集中，指定分数（score）区间内的所有成员
     */
    Long zRemRangeByScore (String key, double min, double max);

    /**
     * 返回有序集中，成员的分数值
     */
    Double zScore (String key, String member);
}
