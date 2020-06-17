package com.lc.core.convert.impl;

import com.lc.core.convert.AbstractConverter;
import com.lc.utils.UDateUtil;
import com.lc.utils.UStringUtil;

import java.util.Calendar;
import java.util.Date;

/**
 * 日期转换器
 *
 * @author LC
 */
public class CalendarConverter extends AbstractConverter<Calendar> {

    /**
     * 日期格式化
     */
    private String format;

    /**
     * 获取日期格式
     *
     * @return 设置日期格式
     */
    public String getFormat() {
        return format;
    }

    /**
     * 设置日期格式
     *
     * @param format 日期格式
     */
    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    protected Calendar convertInternal(Object value) {
        // Handle Date
        if (value instanceof Date) {
            return UDateUtil.calendar((Date) value);
        }

        // Handle Long
        if (value instanceof Long) {
            //此处使用自动拆装箱
            return UDateUtil.calendar((Long) value);
        }

        final String valueStr = convertToStr(value);
        return UDateUtil.calendar(UStringUtil.isBlank(format) ? UDateUtil.parse(valueStr) : UDateUtil.parse(valueStr, format));
    }
}

