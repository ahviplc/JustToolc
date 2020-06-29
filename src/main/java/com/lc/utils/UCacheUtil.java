package com.lc.utils;

import com.lc.core.cache.singleCache.UCache;

/**
 * 单例缓存工具类
 * <p>
 * 单例缓存对象
 *
 * @author LC
 * @since 0.2.0
 */
public class UCacheUtil {

    /**
     * getSingleCache
     * <p>
     * 获取单例缓存对象
     *
     * @return 获取到的单例缓存对象
     */
    public static UCache getSingleCache() {
        return UCache.getInstance();
    }
}
