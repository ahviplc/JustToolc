package com.lc.core.convert.impl;

import com.lc.core.convert.AbstractConverter;
import com.lc.utils.UBooleanUtil;

/**
 * 波尔转换器
 *
 * @author LC
 */
public class BooleanConverter extends AbstractConverter<Boolean> {

    @Override
    protected Boolean convertInternal(Object value) {
        if (boolean.class == value.getClass()) {
            return Boolean.valueOf((boolean) value);
        }
        String valueStr = convertToStr(value);
        return Boolean.valueOf(UBooleanUtil.toBoolean(valueStr));
    }
}

