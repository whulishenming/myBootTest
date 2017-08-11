package com.lsm.springboot.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * redis字符串
 */
public interface IRedisStringService {

    /**
     * 设置指定 key 的值
     */
    void set(String key, String value);

    /**
     * 为指定的 key 设置值及其过期时间
     *  1. 如果 key 已经存在， SETEX 命令将会替换旧的值
     */
    void setEx(String key, String value, long expire, TimeUnit unit);

    /**
     * Setnx（SET if Not eXists） 命令在指定的 key 不存在时，为 key 设置指定的值
     */
    Boolean setNx(String key, String value);

    /**
     * 用于同时设置一个或多个 key-value 对。
     */
    void mSet(Map<String, String> map);

    /**
     * 所有给定 key 都不存在时，同时设置一个或多个 key-value 对
     */
    Boolean mSetNx(Map<String, String> map);

    /**
     * 获取指定 key 的值。
     */
    String get(String key);

    /**
     * 设置指定 key 的值，并返回 key 的旧值
     *  1. key 不存在时，返回 null
     */
    String getSet(String key, String newValue);

    /**
     * 返回所有(一个或多个)给定 key 的值
     *  1. 如果给定的 key 里面，有某个 key 不存在，那么这个 key 返回特殊值 null
     */
    List<String> mGet(List<String> keys);

    /**
     * 将 key 中储存的数字加上指定的增量值
     *  1. 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCRBY 命令
     *  2. 返回加上指定的增量值之后， key 的值
     */
    Long incrBy(String key, long delta);

    Double incrBy(String key, double delta);

    /**
     * 将 value 追加到 key 原来的值的末尾
     *  1. 如果 key 不存在， APPEND 就简单地将给定 key 设为 value
     *  2. 返回append之后的字符串值的长度
     */
    Integer append(String key, String value);

    /**
     * 返回 key 中字符串值的子字符
     *  1. start 和 end 从0开始
     *  2. -1代表最后一位
     */
    String getRange(String key, long start, long end);

    /**
     * 用指定的字符串覆盖给定 key 所储存的字符串值，覆盖的位置从偏移量 offset 开始
     */
    void setRange(String key, String value, long offset);

    /**
     * 用于获取指定 key 所储存的字符串值的长度
     *  1. 当 key 不存在时，返回 0
     */
    Long strLen(String key);

}
