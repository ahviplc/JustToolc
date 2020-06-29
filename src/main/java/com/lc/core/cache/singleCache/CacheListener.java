package com.lc.core.cache.singleCache;

import com.lc.core.cache.singleCache.impl.CacheManagerImpl;
import com.lc.lang.UConsole;

/**
 * CacheListener 缓存监听
 *
 * @author LC
 * @since 0.2.0
 */
public class CacheListener {
    // cacheManagerImpl对象
    private CacheManagerImpl cacheManagerImpl;

    public CacheListener(CacheManagerImpl cacheManagerImpl) {
        this.cacheManagerImpl = cacheManagerImpl;
    }

    public void startListen() {
        new Thread() {
            public void run() {
                while (true) {
                    for (String key : cacheManagerImpl.getAllKeys()) {
                        if (cacheManagerImpl.isTimeOut(key)) {
                            cacheManagerImpl.clearByKey(key);
                            UConsole.log(key + "缓存被清除");
                        }
                    }
                }
            }
        }.start();
    }
}
