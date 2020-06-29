package com.lc.tests;

import com.lc.core.cache.singleCache.CacheListener;
import com.lc.core.cache.singleCache.impl.CacheManagerImpl;
import com.lc.lang.UConsole;
import com.lc.utils.UCacheUtil;

import java.util.concurrent.TimeUnit;

public class UCacheUtilTest {
    public static void main(String[] args) {
        // 测试单例缓存

        // 新建单例实例化缓存对象
        CacheManagerImpl cacheManagerImpl = UCacheUtil.getSingleCache();
        CacheManagerImpl cacheManagerImpl2 = UCacheUtil.getSingleCache();
        cacheManagerImpl.putCache("test", "test", 10 * 1000L);

        // 输出是否过期
        UConsole.log(cacheManagerImpl.isTimeOut("test"));

        cacheManagerImpl2.putCache("myTest", "myTest", 15 * 1000L);

        // 睡眠12秒
        try {
            TimeUnit.SECONDS.sleep(12);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 输出是否过期
        UConsole.log(cacheManagerImpl.isTimeOut("myTest"));
        UConsole.log(cacheManagerImpl2.isTimeOut("test"));

        // 输出全部key
        UConsole.log("全部key:{}", cacheManagerImpl.getAllKeys());

        // 测试CacheListener监听
        testCacheListener();
    }

    /**
     * testCacheListener()
     */
    public static void testCacheListener() {
        // 新建单例实例化缓存对象
        CacheManagerImpl cacheManagerImpl = UCacheUtil.getSingleCache();

        // 写入数据
        cacheManagerImpl.putCache("test", "test2", 10 * 1000L);
        cacheManagerImpl.putCache("myTest", "myTest2", 15 * 1000L);

        // 新建CacheListener监听
        CacheListener cacheListener = new CacheListener(cacheManagerImpl);
        cacheListener.startListen();
        UConsole.log("test:" + cacheManagerImpl.getCacheByKey("test").getDatas());
        UConsole.log("myTest:" + cacheManagerImpl.getCacheByKey("myTest").getDatas());

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        UConsole.log("test:" + cacheManagerImpl.getCacheByKey("test"));
        UConsole.log("myTest:" + cacheManagerImpl.getCacheByKey("myTest"));
    }
}
