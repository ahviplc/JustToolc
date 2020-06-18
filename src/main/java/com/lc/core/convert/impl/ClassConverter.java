package com.lc.core.convert.impl;

import com.lc.core.convert.AbstractConverter;
import com.lc.utils.UClassUtil;

/**
 * 类转换器<br>
 * 将类名转换为类
 *
 * @author LC
 */
public class ClassConverter extends AbstractConverter<Class<?>> {

    @Override
    protected Class<?> convertInternal(Object value) {
        String valueStr = convertToStr(value);
        try {
            return UClassUtil.getClassLoader().loadClass(valueStr);
        } catch (Exception e) {
            // Ignore Exception
        }
        return null;
    }
}

