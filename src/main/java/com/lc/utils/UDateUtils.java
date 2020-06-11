package com.lc.utils;

import java.util.Date;

/**
 * 日期工具类
 *
 * @author LC
 */
public class UDateUtils {

    /**
     * @return 默认到毫秒ms
     */
    public static Long getTimeStamp() {
        return new Date().getTime();
    }

    /**
     * @param isSeconds 是否到秒s true到秒s false到毫秒ms
     * @return
     */
    public static Long getTimeStamp(Boolean isSeconds) {
        if (isSeconds) {
            return new Date().getTime() / 1000;
        }
        return new Date().getTime();
    }
}