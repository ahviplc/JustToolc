package com.lc.core.convert.impl;

import com.lc.core.convert.AbstractConverter;
import com.lc.utils.UBooleanUtil;
import com.lc.utils.UStringUtil;

/**
 * 字符转换器
 *
 * @author LC
 */
public class CharacterConverter extends AbstractConverter<Character> {

    @Override
    protected Character convertInternal(Object value) {
        if (char.class == value.getClass()) {
            return Character.valueOf((char) value);
        } else if (value instanceof Boolean) {
            return UBooleanUtil.toCharacter((Boolean) value);
        } else if (boolean.class == value.getClass()) {
            return UBooleanUtil.toCharacter((boolean) value);
        } else {
            final String valueStr = convertToStr(value);
            if (UStringUtil.isNotBlank(valueStr)) {
                return Character.valueOf(valueStr.charAt(0));
            }
        }
        return null;
    }
}
