package com.lc.utils;

/**
 * 异常工具类
 *
 * @author LC
 */
public class UExceptionUtil {
    private static final String NULL = "null";

    /**
     * 获得完整消息，包括异常名
     *
     * @param e 异常
     * @return 完整消息
     */
    public static String getMessage(Throwable e) {
        if (null == e) {
            return NULL;
        }
        return UStringUtil.format("{}: {}", e.getClass().getSimpleName(), e.getMessage());
    }
}
