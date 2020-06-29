package com.lc.core.cache.singleCache.impl;

import com.lc.core.cache.singleCache.EntityCache;
import com.lc.core.cache.singleCache.ICacheManager;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ICacheManager接口的具体实现
 * <p>
 * 懒汉单例模式-双重锁模式
 * <p>
 * 线程安全，延迟初始化。这种方式采用双锁机制，安全且在多线程情况下能保持高性能。
 * <p>
 * 设计模式之单例模式 - 简书<br>
 * https://www.jianshu.com/p/3bfd916f2bb2
 *
 * @author LC
 * @since 0.2.0
 */
public class CacheManagerImpl implements ICacheManager {
    private static Map<String, EntityCache> caches = new ConcurrentHashMap<String, EntityCache>();
    // 实例化对象
    private volatile static CacheManagerImpl instance;

    // 无参构造器私有化
    private CacheManagerImpl() {
    }

    /**
     * 单例获取实例对象
     *
     * @return 对象
     */
    public static CacheManagerImpl getInstance() {
        if (instance == null) {
            synchronized (CacheManagerImpl.class) {
                if (instance == null) {
                    instance = new CacheManagerImpl();
                }
            }
        }
        return instance;
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