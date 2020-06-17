package com.lc.core.convert.impl;

import com.lc.core.convert.AbstractConverter;
import com.lc.utils.UCharsetUtil;

import java.nio.charset.Charset;

/**
 * 编码对象转换器
 *
 * @author LC
 */
public class CharsetConverter extends AbstractConverter<Charset> {
    @Override
    protected Charset convertInternal(Object value) {
        return UCharsetUtil.charset(convertToStr(value));
    }
}

