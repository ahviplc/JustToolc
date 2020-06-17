package com.lc.core.convert.impl;

import com.lc.core.convert.AbstractConverter;
import com.lc.core.convert.ConverterRegistry;
import com.lc.utils.UBeanUtil;
import com.lc.utils.UMapUtil;
import com.lc.utils.UStringUtil;
import com.lc.utils.UTypeUtil;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Map.Entry;

/**
 * {@link Map} 转换器
 *
 * @author LC
 * @since 0.0.1
 */
public class MapConverter extends AbstractConverter<Map<?, ?>> {

    /**
     * Map类型
     */
    private final Type mapType;
    /**
     * 键类型
     */
    private final Type keyType;
    /**
     * 值类型
     */
    private final Type valueType;

    /**
     * 构造，Map的key和value泛型类型自动获取
     *
     * @param mapType Map类型
     */
    public MapConverter(Type mapType) {
        this(mapType, UTypeUtil.getTypeArgument(mapType, 0), UTypeUtil.getTypeArgument(mapType, 1));
    }

    /**
     * 构造
     *
     * @param mapType   Map类型
     * @param keyType   键类型
     * @param valueType 值类型
     */
    public MapConverter(Type mapType, Type keyType, Type valueType) {
        this.mapType = mapType;
        this.keyType = keyType;
        this.valueType = valueType;
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected Map<?, ?> convertInternal(Object value) {
        Map map = null;
        if (value instanceof Map) {
            map = UMapUtil.createMap(UTypeUtil.getClass(this.mapType));
            convertMapToMap((Map) value, map);
        } else if (UBeanUtil.isBean(value.getClass())) {
            map = UBeanUtil.beanToMap(value);
        } else {
            throw new UnsupportedOperationException(UStringUtil.format("Unsupport toMap value type: {}", value.getClass().getName()));
        }
        return map;
    }

    /**
     * Map转Map
     *
     * @param srcMap    源Map
     * @param targetMap 目标Map
     */
    private void convertMapToMap(Map<?, ?> srcMap, Map<Object, Object> targetMap) {
        final ConverterRegistry convert = ConverterRegistry.getInstance();
        Object key;
        Object value;
        for (Entry<?, ?> entry : srcMap.entrySet()) {
            key = (null == this.keyType) ? entry.getKey() : convert.convert(this.keyType, entry.getKey());
            value = (null == this.valueType) ? entry.getValue() : convert.convert(this.keyType, entry.getValue());
            targetMap.put(key, value);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class<Map<?, ?>> getTargetType() {
        return (Class<Map<?, ?>>) UTypeUtil.getClass(this.mapType);
    }
}