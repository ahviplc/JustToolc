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
    // 线程是否运行标识
    // volatile boolean isRunning = true;
    // cacheManagerImpl对象
    private CacheManagerImpl cacheManagerImpl;

    public CacheListener(CacheManagerImpl cacheManagerImpl) {
        this.cacheManagerImpl = cacheManagerImpl;
    }

    public void startListen() {
        new Thread() {
            public void run() {
                UConsole.log("isInterrupted():{}",isInterrupted());
                while (!isInterrupted()) { // while (isRunning) 使用isRunning也可
                    if( cacheManagerImpl.getAllKeys().size()>0){
                        for (String key : cacheManagerImpl.getAllKeys()) {
                            if (cacheManagerImpl.isTimeOut(key)) {
                                cacheManagerImpl.clearByKey(key);
                                UConsole.log(key + "缓存被清除");
                            }
                        }
                    }else {
                        UConsole.log("{}-所有的key都被清除了,线程停止......", Thread.currentThread().getName());
                        // 线程任务执行完毕 进行中断处理
                        // 将 isRunning 置为 false
                        // isRunning = false;
                        // 通知线程可以停止了
                        UConsole.log("isInterrupted()-interrupt()调用之前:{}",isInterrupted());
                        Thread.currentThread().interrupt();
                        UConsole.log("isInterrupted()-interrupt()调用之后:{}",isInterrupted());
                    }
                }
            }
        }.start();
    }
}
