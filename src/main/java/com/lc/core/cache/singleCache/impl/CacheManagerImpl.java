package com.lc.core.cache.singleCache.impl;

import com.lc.core.cache.singleCache.EntityCache;
import com.lc.core.cache.singleCache.ICacheManager;
import com.lc.core.cache.singleCache.UCache;
import com.lc.utils.UCacheUtil;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ICacheManager接口的具体实现
 *
 * @author LC
 * @since 0.2.0
 */
public class CacheManagerImpl implements ICacheManager {
    private static Map<String, EntityCache> caches = new ConcurrentHashMap<String, EntityCache>();

    // 无参构造器
    public CacheManagerImpl() {
    }

    /**
     * 存入缓存
     *
     * @param key   键
     * @param cache cache对象
     */
    public void putCache(String key, EntityCache cache) {
        caches.put(key, cache);
    }

    /**
     * 存入缓存
     *
     * @param key     键
     * @param datas   输入数据
     * @param timeOut 过期时间 单位:毫秒
     */
    public void putCache(String key, Object datas, long timeOut) {
        timeOut = timeOut > 0 ? timeOut : 0L;
        putCache(key, new EntityCache(datas, timeOut, System.currentTimeMillis()));
    }

    /**
     * 获取对应缓存
     *
     * @param key 键
     * @return 对象
     */
    public EntityCache getCacheByKey(String key) {
        if (this.isContains(key)) {
            return caches.get(key);
        }
        return null;
    }

    /**
     * 获取对应缓存
     *
     * @param key 键
     * @return 对象
     */
    public Object getCacheDataByKey(String key) {
        if (this.isContains(key)) {
            return caches.get(key).getDatas();
        }
        return null;
    }

    /**
     * 获取所有缓存
     *
     * @return 对象
     */
    public Map<String, EntityCache> getCacheAll() {
        return caches;
    }

    /**
     * 判断是否在缓存中
     *
     * @param key 键
     * @return 布尔值
     */
    public boolean isContains(String key) {
        return caches.containsKey(key);
    }

    /**
     * 清除所有缓存
     */
    public void clearAll() {
        caches.clear();
    }

    /**
     * 清除对应缓存
     *
     * @param key 键
     */
    public void clearByKey(String key) {
        if (this.isContains(key)) {
            caches.remove(key);
        }
    }

    /**
     * 缓存是否超时失效
     *
     * @param key 键
     * @return 布尔值
     */
    public boolean isTimeOut(String key) {
        if (!caches.containsKey(key)) {
            return true;
        }
        EntityCache cache = caches.get(key);
        long timeOut = cache.getTimeOut();
        long lastRefreshTime = cache.getLastRefeshTime();
        if (timeOut == 0 || System.currentTimeMillis() - lastRefreshTime >= timeOut) {
            return true;
        }
        return false;
    }

    /**
     * 获取所有key
     *
     * @return 对象
     */
    public Set<String> getAllKeys() {
        return caches.keySet();
    }
}