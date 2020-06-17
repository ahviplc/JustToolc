package com.lc.core.convert.impl;

import java.util.TimeZone;

import com.lc.core.convert.AbstractConverter;

/**
 * TimeZone转换器
 *
 * @author LC
 */
public class TimeZoneConverter extends AbstractConverter<TimeZone> {

    @Override
    protected TimeZone convertInternal(Object value) {
        return TimeZone.getTimeZone(convertToStr(value));
    }
}

