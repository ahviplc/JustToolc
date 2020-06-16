package com.lc.utils;

import java.util.Collection;
import java.util.Iterator;

/**
 * 集合相关工具类<p>
 * 此工具方法针对{@link Collection}及其实现类封装的工具。<p>
 * 由于{@link Collection} 实现了{@link Iterable}接口，因此部分工具此类不提供，而是在{@link UIterUtil} 中提供
 *
 * @author LC
 * @see UCollUtil
 */
public class UCollUtil {
    /**
     * 以 conjunction 为分隔符将集合转换为字符串<br>
     * 如果集合元素为数组、{@link Iterable}或{@link Iterator}，则递归组合其为字符串
     *
     * @param <T>         集合元素类型
     * @param iterable    {@link Iterable}
     * @param conjunction 分隔符
     * @return 连接后的字符串
     * @see UIterUtil#join(Iterable, CharSequence)
     */
    public static <T> String join(Iterable<T> iterable, CharSequence conjunction) {
        return UIterUtil.join(iterable, conjunction);
    }
}
