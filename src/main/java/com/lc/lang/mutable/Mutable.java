package com.lc.lang.mutable;

/**
 * 提供可变值类型接口
 *
 * @param <T> 值得类型
 * @author LC
 * @since 0.1
 */
public interface Mutable<T> {

    /**
     * 获得原始值
     *
     * @return 原始值
     */
    T get();

    /**
     * 设置值
     *
     * @param value 值
     */
    void set(T value);
}
