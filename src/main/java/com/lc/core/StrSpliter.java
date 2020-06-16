package com.lc.core;

import com.lc.utils.UNumberUtil;
import com.lc.utils.UStringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串切分器
 *
 * @author LC
 */
public class StrSpliter {
    /**
     * 切分字符串，大小写敏感
     *
     * @param str         被切分的字符串
     * @param separator   分隔符字符
     * @param limit       限制分片数，-1不限制
     * @param isTrim      是否去除切分字符串后每个元素两边的空格
     * @param ignoreEmpty 是否忽略空串
     * @return 切分后的集合
     */
    public static List<String> split(String str, char separator, int limit, boolean isTrim, boolean ignoreEmpty) {
        return split(str, separator, limit, isTrim, ignoreEmpty, false);
    }

    /**
     * 切分字符串
     *
     * @param str         被切分的字符串
     * @param separator   分隔符字符
     * @param limit       限制分片数，-1不限制
     * @param isTrim      是否去除切分字符串后每个元素两边的空格
     * @param ignoreEmpty 是否忽略空串
     * @param ignoreCase  是否忽略大小写
     * @return 切分后的集合
     */
    public static List<String> split(String str, char separator, int limit, boolean isTrim, boolean ignoreEmpty, boolean ignoreCase) {
        if (UStringUtil.isEmpty(str)) {
            return new ArrayList<String>(0);
        }
        if (limit == 1) {
            return addToList(new ArrayList<String>(1), str, isTrim, ignoreEmpty);
        }

        final ArrayList<String> list = new ArrayList<>(limit > 0 ? limit : 16);
        int len = str.length();
        int start = 0;//切分后每个部分的起始
        for (int i = 0; i < len; i++) {
            if (UNumberUtil.equals(separator, str.charAt(i), ignoreCase)) {
                addToList(list, str.substring(start, i), isTrim, ignoreEmpty);
                start = i + 1;//i+1同时将start与i保持一致

                //检查是否超出范围（最大允许limit-1个，剩下一个留给末尾字符串）
                if (limit > 0 && list.size() > limit - 2) {
                    break;
                }
            }
        }
        return addToList(list, str.substring(start, len), isTrim, ignoreEmpty);//收尾
    }

    /**
     * 将字符串加入List中
     *
     * @param list        列表
     * @param part        被加入的部分
     * @param isTrim      是否去除两端空白符
     * @param ignoreEmpty 是否略过空字符串（空字符串不做为一个元素）
     * @return 列表
     */
    private static List<String> addToList(List<String> list, String part, boolean isTrim, boolean ignoreEmpty) {
        part = part.toString();
        if (isTrim) {
            part = part.trim();
        }
        if (false == ignoreEmpty || false == part.isEmpty()) {
            list.add(part);
        }
        return list;
    }
}
