package com.lc.core.convert.impl;

import com.lc.core.convert.AbstractConverter;

import java.util.UUID;

/**
 * UUID对象转换器转换器
 *
 * @author LC
 * @since 0.0.1
 */
public class UUIDConverter extends AbstractConverter<UUID> {

    @Override
    protected UUID convertInternal(Object value) {
        return UUID.fromString(convertToStr(value));
    }
}

