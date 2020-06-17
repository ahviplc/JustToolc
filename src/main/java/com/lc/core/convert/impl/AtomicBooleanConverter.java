package com.lc.core.convert.impl;

import com.lc.core.convert.AbstractConverter;
import com.lc.utils.UBooleanUtil;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * {@link AtomicBoolean}转换器
 *
 * @author LC
 * @since 0.0.1
 */
public class AtomicBooleanConverter extends AbstractConverter<AtomicBoolean> {

    @Override
    protected AtomicBoolean convertInternal(Object value) {
        if (boolean.class == value.getClass()) {
            return new AtomicBoolean((boolean) value);
        }
        if (value instanceof Boolean) {
            return new AtomicBoolean((Boolean) value);
        }
        final String valueStr = convertToStr(value);
        return new AtomicBoolean(UBooleanUtil.toBoolean(valueStr));
    }
}

