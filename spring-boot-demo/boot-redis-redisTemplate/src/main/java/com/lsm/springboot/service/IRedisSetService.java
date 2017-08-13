package com.lsm.springboot.service;

import java.util.List;
import java.util.Set;

/**
 * 对集合的操作
 * 集合里的元素是不可重复的
 * set 的内部实现是一个 value永远为null的HashMap，实际就是通过计算hash的方式来快速排重的
 */
public interface IRedisSetService {

    /**
     * 向集合添加一个或多个成员
     */
    Long sAdd(String key, String... values);

    /**
     * 移除集合中一个或多个成员
     */
    Long sRem(String key, String... values);

    /**
     * 移除并返回集合中的一个随机元素
     */
    String sPop(String key);

    /**
     * 返回集合中一个或多个随机数, 可能重复
     */
    List<String> sRandMember(String key, long count);

    /**
     * 返回集合中一个或多个随机数，不重复
     */
    Set<String> sRandMember(long count, String key);

    /**
     * 将 member 元素从 sourceKey 集合移动到 destKey 集合
     */
    Boolean sMove(String sourceKey, String member, String destKey);

    /**
     * 获取集合的成员数
     */
    Long sCard(String key);

    /**
     * 判断 member 元素是否是集合 key 的成员
     */
    Boolean sIsMember(String key, String member);

    /**
     * 返回给定所有集合的交集
     */
    Set<String> sInter(String key, List<String> otherKeys);

    /**
     * 返回给定所有集合的交集并存储在 destKey 中
     */
    Long sInterStore(String key, List<String> otherKeys, String destKey);

    /**
     * 返回所有给定集合的并集
     */
    Set<String> sUnion(String key, List<String> otherKeys);

    /**
     * 所有给定集合的并集存储在 destKey 集合中
     */
    Long sUnionStore(String key, List<String> otherKeys, String destKey);

    /**
     * 返回给定所有集合的差集
     */
    Set<String> sDiff(String key, List<String> otherKeys);

    /**
     * 返回给定所有集合的差集并存储在 destKey 中
     */
    Long sDiffStore(String key, List<String> otherKeys, String destKey);

    /**
     * 返回集合中的所有成员
     */
    Set<String> sMembers(String key);

}
