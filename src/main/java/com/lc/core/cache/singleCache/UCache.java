package com.lc.core.cache.singleCache;

import com.lc.core.cache.singleCache.impl.CacheManagerImpl;

/**
 * ICacheManager接口的具体实现子类
 * <p>
 * UCache
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
public class UCache extends CacheManagerImpl {
    // 实例化对象
    private volatile static UCache instance;

    private UCache() {
        super();
    }

    /**
     * 单例获取实例对象
     *
     * @return 对象
     */
    public static UCache getInstance() {
        if (instance == null) {
            synchronized (UCache.class) {
                if (instance == null) {
                    instance = new UCache();
                }
            }
        }
        return instance;
    }
}
