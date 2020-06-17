package com.lc.core.convert.impl;

import com.lc.core.convert.AbstractConverter;
import com.lc.core.convert.ConverterRegistry;
import com.lc.utils.UStringUtil;
import com.lc.utils.UTypeUtil;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;

/**
 * {@link Reference}转换器
 *
 * @author LC
 * @since 0.0.1
 */
@SuppressWarnings("rawtypes")
public class ReferenceConverter extends AbstractConverter<Reference> {

    private Class<? extends Reference> targetType;

    /**
     * 构造
     *
     * @param targetType {@link Reference}实现类型
     */
    public ReferenceConverter(Class<? extends Reference> targetType) {
        this.targetType = targetType;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Reference<?> convertInternal(Object value) {

        //尝试将值转换为Reference泛型的类型
        Object targetValue = null;
        final Type paramType = UTypeUtil.getTypeArgument(targetType);
        if (null != paramType) {
            targetValue = ConverterRegistry.getInstance().convert(paramType, value);
        }
        if (null == targetValue) {
            targetValue = value;
        }

        if (this.targetType == WeakReference.class) {
            return new WeakReference(targetValue);
        } else if (this.targetType == SoftReference.class) {
            return new SoftReference(targetValue);
        }

        throw new UnsupportedOperationException(UStringUtil.format("Unsupport Reference type: {}", this.targetType.getName()));
    }
}

