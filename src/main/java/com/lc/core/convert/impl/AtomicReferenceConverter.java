package com.lc.core.convert.impl;

import com.lc.core.convert.AbstractConverter;
import com.lc.core.convert.ConverterRegistry;
import com.lc.utils.UTypeUtil;

import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicReference;

/**
 * {@link AtomicReference}转换器
 *
 * @author LC
 * @since 0.0.1
 */
@SuppressWarnings("rawtypes")
public class AtomicReferenceConverter extends AbstractConverter<AtomicReference> {

    @Override
    protected AtomicReference<?> convertInternal(Object value) {

        //尝试将值转换为Reference泛型的类型
        Object targetValue = null;
        final Type paramType = UTypeUtil.getTypeArgument(AtomicReference.class);
        if (null != paramType) {
            targetValue = ConverterRegistry.getInstance().convert(paramType, value);
        }
        if (null == targetValue) {
            targetValue = value;
        }
        return new AtomicReference<>(targetValue);
    }
}

