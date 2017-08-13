package com.lsm.springboot.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 对Hash的操作
 * 内部存储的Value为一个HashMap，并提供了直接存取这个Map成员的接口
 */
public interface IRedisHashService {
    /**
     * 将哈希表 key 中的字段 field 的值设为 value
     */
    void hSet(String key, String field, String value);

    /**
     * 同时将多个 field-value (域-值)对设置到哈希表 key 中
     */
    void hMSet(String key, Map<String, String> map);

    /**
     * 只有在字段 field 不存在时，设置哈希表字段的值
     */
    Boolean hSetNX(String key, String field, String value);

    /**
     * 删除一个或多个哈希表字段
     */
    Long hDel(String key, String... field);

    /**
     * 查看哈希表 key 中，指定的字段 field 是否存在
     */
    Boolean hExists(String key, String field);

    /**
     * 获取存储在哈希表中指定字段 field 的值
     */
    String hGet(String key, String field);

    /**
     * 获取所有给定字段的值
     */
    List<String> hMGet(String key, List<String> fields);

    /**
     * 为哈希表 key 中的指定字段的整数值加上增量 incremen
     */
    Long hIncrBy(String key, String field, long increment);

    /**
     * 为哈希表 key 中的指定字段的整数值加上增量 incremen
     */
    Double hIncrBy(String key, String field, double increment);

    /**
     * 获取所有哈希表中的字段
     */
    Set<String> hKeys(String key);

    /**
     * 获取哈希表中字段的数量
     */
    Long hLen(String key);

    /**
     * 获取哈希表中所有值
     */
    List<String> hVals(String key);

    /**
     * 获取在哈希表中指定 key 的所有字段和值
     */
    Map<String, String> hGetAll(String key);
}
