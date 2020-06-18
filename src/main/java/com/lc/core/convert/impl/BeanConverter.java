package com.lc.core.convert.impl;

import com.lc.core.bean.copier.BeanCopier;
import com.lc.core.bean.copier.CopyOptions;
import com.lc.core.bean.copier.ValueProvider;
import com.lc.core.convert.AbstractConverter;
import com.lc.utils.UBeanUtil;
import com.lc.utils.UReflectUtil;

import java.util.Map;

/**
 * Bean转换器<br>
 * <p>
 * 支持：
 * <pre>
 * Map =》 Bean
 * Bean =》 Bean
 * ValueProvider =》 Bean
 * </pre>
 *
 * @param <T> Bean类型
 * @author LC
 * @since 0.0.1
 */
public class BeanConverter<T> extends AbstractConverter<T> {

    private Class<T> beanClass;
    private CopyOptions copyOptions;

    /**
     * 构造，默认转换选项，注入失败的字段忽略
     *
     * @param beanClass 转换成的目标Bean类
     */
    public BeanConverter(Class<T> beanClass) {
        this(beanClass, CopyOptions.create().setIgnoreError(true));
        this.beanClass = beanClass;
    }

    /**
     * 构造
     *
     * @param beanClass   转换成的目标Bean类
     * @param copyOptions Bean转换选项参数
     */
    public BeanConverter(Class<T> beanClass, CopyOptions copyOptions) {
        this.beanClass = beanClass;
        this.copyOptions = copyOptions;
    }

    @Override
    protected T convertInternal(Object value) {
        if (value instanceof Map || value instanceof ValueProvider || UBeanUtil.isBean(value.getClass())) {
            //限定被转换对象类型
            return BeanCopier.create(value, UReflectUtil.newInstanceIfPossible(this.beanClass), copyOptions).copy();
        }
        return null;
    }

    @Override
    public Class<T> getTargetType() {
        return this.beanClass;
    }
}