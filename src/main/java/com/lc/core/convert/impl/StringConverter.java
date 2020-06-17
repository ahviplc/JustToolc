package com.lc.core.convert.impl;

import com.lc.core.convert.AbstractConverter;

/**
 * 字符串转换器
 *
 * @author LC
 */
public class StringConverter extends AbstractConverter<String> {

    @Override
    protected String convertInternal(Object value) {
        return convertToStr(value);
    }
}

