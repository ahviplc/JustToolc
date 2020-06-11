package com.lc.utils;

/**
 * 字符串工具类
 *
 * @author LC
 */
public class UStringUtils {

    /**
     * @param oldData 原字符串数据
     * @return
     */
    public static String toUpper(String oldData) {
        return oldData.trim().toUpperCase();
    }

    /**
     * @param oldData 原字符串数据
     * @return
     */
    public static String toLower(String oldData) {
        return oldData.trim().toLowerCase();
    }
}
