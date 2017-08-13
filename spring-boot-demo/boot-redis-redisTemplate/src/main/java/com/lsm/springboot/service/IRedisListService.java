package com.lsm.springboot.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 对列表的操作
 * Redis list的实现为一个双向链表，即可以支持反向查找和遍历
 */
public interface IRedisListService {

    /**
     * 向列表左边添加元素
     *  1. 返回list的长度
     */
    long lPush(String key, String... value);

    /**
     * 将一个值插入到已存在的列表头部，列表不存在时操作无效
     */
    long lPushX(String key, String value);

    /**
     * 向列表右边添加元素
     *  1. 返回list的长度
     */
    long rPush(String key, String... value);

    /**
     * 从左弹出
     */
    String lPop(String key);

    /**
     * 移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     */
    String bLPop(String key, long timeout, TimeUnit unit);

    /**
     * 从右弹出
     */
    String rPop(String key);

    /**
     * 移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     *  1. 超时返回 null
     */
    String bRPop (String key, long timeout, TimeUnit unit);

    /**
     * 通过索引来设置元素的值
     */
    void lSet(String key, long index, String value);

    /**
     * 用于在列表的元素 pivot 前(从表头开始向表尾第一个)插入元素 value
     *  1. 当指定元素不存在于列表中时，不执行任何操作
     *  2. 当列表不存在时，被视为空列表，不执行任何操作
     */
    Long lInsert(String key, String pivot, String value);

    /**
     * 根据参数 COUNT 的值，移除列表中与参数 VALUE 相等的元素
     *  1. count > 0 : 从表头开始向表尾搜索，移除与 VALUE 相等的元素，数量为 COUNT
     *  2. count < 0 : 从表尾开始向表头搜索，移除与 VALUE 相等的元素，数量为 COUNT 的绝对值
     *  3. count = 0 : 移除表中所有与 VALUE 相等的值
     *  4. 返回移除元素的数量
     */
    Long lRem(String key, long count, String value);

    /**
     * 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除
     */
    void lTrim(String key, long start, long end);

    /**
     * 获取列表中元素的个数
     */
    Long lLen(String key);

    /**
     * 通过索引(下标)获取列表中的元素
     * 1. 如果指定索引值不在列表的区间范围内，返回 null
     */
    String lIndex(String key, long index);

    /**
     * 获取列表片段, 是个闭区间
     *  1. start 和 end 都以 0 为底
     */
    List<String> lRange(String key, long start, long end);

    /**
     * 从 sourceKey 列表中弹出位于最左端的元素，然后将这个元素推入 destKey 列表的最左端，并返回这个元素
     */
    String rPopLPush(String sourceKey, String destKey);

    /**
     * 从 sourceKey 列表中弹出位于最左端的元素，然后将这个元素推入 destKey 列表的最左端，并返回这个元素； 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     */
    String bRPopLPush (String sourceKey, String destKye, long timeout, TimeUnit unit);
}
