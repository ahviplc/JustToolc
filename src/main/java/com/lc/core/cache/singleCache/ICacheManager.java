package com.lc.core.cache.singleCache;

import java.util.Map;
import java.util.Set;

/**
 * 定义缓存操作接口
 *
 * @author LC
 * @since 0.2.0
 */
public interface ICacheManager {
    /**
     * 存入缓存
     *
     * @param key   键
     * @param cache cache实体
     */
    void putCache(String key, EntityCache cache);

    /**
     * 存入缓存
     *
     * @param key     键
     * @param datas   值数据
     * @param timeOut 过期时间
     */
    void putCache(String key, Object datas, long timeOut);

    /**
     * 获取对应缓存
     *
     * @param key 键
     * @return 对象
     */
    EntityCache getCacheByKey(String key);

    /**
     * 获取对应缓存
     *
     * @param key 键
     * @return 对象
     */
    Object getCacheDataByKey(String key);

    /**
     * 获取所有缓存
     *
     * @return 对象
     */
    Map<String, EntityCache> getCacheAll();

    /**
     * 判断是否在缓存中
     *
     * @param key 键
     * @return 布尔
     */
    boolean isContains(String key);

    /**
     * 清除所有缓存
     */
    void clearAll();

    /**
     * 清除对应缓存
     *
     * @param key 键
     */
    void clearByKey(String key);

    /**
     * 缓存是否超时失效
     *
     * @param key 键
     * @return 布尔
     */
    boolean isTimeOut(String key);

    /**
     * 获取所有key
     *
     * @return 对象
     */
    Set<String> getAllKeys();
}
