package com.lc.utils;

import com.lc.core.map.CamelCaseLinkedMap;
import com.lc.core.map.CamelCaseMap;
import com.lc.exception.UtilException;
import com.lc.lang.Editor;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Map相关工具类
 *
 * @author LC
 * @since 0.1
 */
public class UMapUtil {
    /**
     * 默认初始大小
     */
    public static final int DEFAULT_INITIAL_CAPACITY = 16;
    /**
     * 默认增长因子，当Map的size达到 容量*增长因子时，开始扩充Map
     */
    public static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * Map是否为空
     *
     * @param map 集合
     * @return 是否为空
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return null == map || map.isEmpty();
    }

    /**
     * Map是否为非空
     *
     * @param map 集合
     * @return 是否为非空
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return null != map && false == map.isEmpty();
    }

    // ----------------------------------------------------------------------------------------------- filter

    /**
     * 过滤<br>
     * 过滤过程通过传入的Editor实现来返回需要的元素内容，这个Editor实现可以实现以下功能：
     *
     * <pre>
     * 1、过滤出需要的对象，如果返回null表示这个元素对象抛弃
     * 2、修改元素对象，返回集合中为修改后的对象
     * </pre>
     *
     * @param <K>    Key类型
     * @param <V>    Value类型
     * @param map    Map
     * @param editor 编辑器接口
     * @return 过滤后的Map
     */
    public static <K, V> Map<K, V> filter(Map<K, V> map, Editor<Map.Entry<K, V>> editor) {
        final Map<K, V> map2 = UObjectUtil.clone(map);
        if (isEmpty(map2)) {
            return map2;
        }

        map2.clear();
        Map.Entry<K, V> modified;
        for (Map.Entry<K, V> entry : map.entrySet()) {
            modified = editor.edit(entry);
            if (null != modified) {
                map2.put(modified.getKey(), modified.getValue());
            }
        }
        return map2;
    }

    /**
     * Map的键和值互换
     *
     * @param <T> 键和值类型
     * @param map Map对象，键值类型必须一致
     * @return 互换后的Map
     */
    public static <T> Map<T, T> reverse(Map<T, T> map) {
        return filter(map, new Editor<Map.Entry<T, T>>() {
            @Override
            public Map.Entry<T, T> edit(final Map.Entry<T, T> t) {
                return new Map.Entry<T, T>() {

                    @Override
                    public T getKey() {
                        return t.getValue();
                    }

                    @Override
                    public T getValue() {
                        return t.getKey();
                    }

                    @Override
                    public T setValue(T value) {
                        throw new UnsupportedOperationException("Unsupported setValue method !");
                    }
                };
            }
        });
    }

    /**
     * 将已知Map转换为key为驼峰风格的Map<br>
     * 如果KEY为非String类型，保留原值
     *
     * @param map 原Map
     * @return 驼峰风格Map
     */
    public static <K, V> Map<K, V> toCamelCaseMap(Map<K, V> map) {
        return (map instanceof LinkedHashMap) ? new CamelCaseLinkedMap<>(map) : new CamelCaseMap<>(map);
    }

    // ----------------------------------------------------------------------------------------------- new HashMap

    /**
     * 新建一个HashMap
     *
     * @param <K> Key类型
     * @param <V> Value类型
     * @return HashMap对象
     */
    public static <K, V> HashMap<K, V> newHashMap() {
        return new HashMap<K, V>();
    }

    /**
     * 新建一个HashMap
     *
     * @param <K>     Key类型
     * @param <V>     Value类型
     * @param isOrder Map的Key是否有序，有序返回 {@link LinkedHashMap}，否则返回 {@link HashMap}
     * @return HashMap对象
     */
    public static <K, V> HashMap<K, V> newHashMap(boolean isOrder) {
        return newHashMap(DEFAULT_INITIAL_CAPACITY, isOrder);
    }

    /**
     * 新建一个HashMap
     *
     * @param <K>     Key类型
     * @param <V>     Value类型
     * @param size    初始大小，由于默认负载因子0.75，传入的size会实际初始大小为size / 0.75
     * @param isOrder Map的Key是否有序，有序返回 {@link LinkedHashMap}，否则返回 {@link HashMap}
     * @return HashMap对象
     */
    public static <K, V> HashMap<K, V> newHashMap(int size, boolean isOrder) {
        int initialCapacity = (int) (size / DEFAULT_LOAD_FACTOR);
        return isOrder ? new LinkedHashMap<K, V>(initialCapacity) : new HashMap<K, V>(initialCapacity);
    }

    /**
     * 创建Map<br>
     * 传入抽象Map{@link AbstractMap}和{@link Map}类将默认创建{@link HashMap}
     *
     * @param <K>     map键类型
     * @param <V>     map值类型
     * @param mapType map类型
     * @return {@link Map}实例
     */
    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> createMap(Class<?> mapType) {
        if (mapType.isAssignableFrom(AbstractMap.class)) {
            return new HashMap<>();
        } else {
            try {
                return (Map<K, V>) UReflectUtil.newInstance(mapType);
            } catch (Exception e) {
                throw new UtilException(e);
            }
        }
    }
}
