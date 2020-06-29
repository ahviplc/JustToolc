package com.lc.tests;

import com.lc.core.cache.singleCache.CacheListener;
import com.lc.core.cache.singleCache.UCache;
import com.lc.core.cache.singleCache.impl.CacheManagerImpl;
import com.lc.lang.UConsole;
import com.lc.utils.UCacheUtil;

import java.util.concurrent.TimeUnit;

public class UCacheUtilTest {
    public static void main(String[] args) {
        // 测试单例缓存

        // 不可使用无参构造器new
        // UCache uCache = new UCache();

        // 新建单例实例化缓存对象 使用子类UCache接收 使用 UCacheUtil.getSingleCache()
        UCache singleCache = UCacheUtil.getSingleCache();
        singleCache.putCache("test", "test1", 10 * 1000L);
        UConsole.log(singleCache.isTimeOut("myTest"));
        UConsole.log("test的数据:{}", singleCache.getCacheDataByKey("test"));
        UCache singleCache2 = UCacheUtil.getSingleCache();
        singleCache2.putCache("test", "test2", 10 * 1000L);
        UConsole.log("test的数据:{}", singleCache2.getCacheDataByKey("test"));

        // 使用 UCache.getInstance() 获取的也是单例对象UCache
        UCache singleCache3 = UCache.getInstance();
        singleCache3.putCache("test", "test3", 10 * 1000L);
        UConsole.log("test的数据:{}", singleCache3.getCacheDataByKey("test"));

        // 新建单例实例化缓存对象 使用父类CacheManagerImpl接收 使用 UCacheUtil.getSingleCache()
        CacheManagerImpl cacheManagerImpl = UCacheUtil.getSingleCache();
        cacheManagerImpl.putCache("test", "test5", 10 * 1000L);
        UConsole.log("test的数据:{}", cacheManagerImpl.getCacheDataByKey("test"));

        // 输出是否过期
        UConsole.log(cacheManagerImpl.isTimeOut("test"));

        CacheManagerImpl cacheManagerImpl2 = UCacheUtil.getSingleCache();
        cacheManagerImpl2.putCache("myTest", "myTest", 15 * 1000L);
        UConsole.log("myTest的数据:{}", cacheManagerImpl2.getCacheDataByKey("myTest"));

        // 这个是new的新对象 不建议使用 推荐使用UCacheUtil.getSingleCache()生成单例对象
        // CacheManagerImpl cacheManagerImpl3 = new CacheManagerImpl();
        // cacheManagerImpl3.putCache("test", "test6", 10 * 1000L);
        // UConsole.log("test的数据:{}", cacheManagerImpl3.getCacheDataByKey("test"));

        // 睡眠12秒
        try {
            TimeUnit.SECONDS.sleep(12);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 输出是否过期
        UConsole.log(singleCache.isTimeOut("myTest"));
        UConsole.log(singleCache2.isTimeOut("test"));
        UConsole.log(cacheManagerImpl.isTimeOut("myTest"));
        UConsole.log(cacheManagerImpl2.isTimeOut("test"));

        // 输出全部key
        UConsole.log("全部key:{}", singleCache.getAllKeys());
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
        UCache singleCache = UCacheUtil.getSingleCache();

        // 写入数据
        cacheManagerImpl.putCache("test", "test7", 10 * 1000L);
        cacheManagerImpl.putCache("myTest", "myTest2", 15 * 1000L);

        // 新建CacheListener监听

        // 传入 CacheManagerImpl 推荐使用下面的 传入 UCache
        // CacheListener cacheListener = new CacheListener(cacheManagerImpl);
        // cacheListener.startListen();

        // 传入 UCache
        CacheListener cacheListener2 = new CacheListener(singleCache);
        cacheListener2.startListen();
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
