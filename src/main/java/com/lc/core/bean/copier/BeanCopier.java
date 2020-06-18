package com.lc.core.bean.copier;

import com.lc.core.bean.BeanDesc.PropDesc;
import com.lc.core.bean.copier.provider.BeanValueProvider;
import com.lc.core.bean.copier.provider.MapValueProvider;
import com.lc.core.convert.Convert;
import com.lc.exception.UtilException;
import com.lc.lang.Copier;
import com.lc.utils.*;


import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;


/**
 * Bean拷贝
 *
 * @param <T> 目标对象类型
 * @author LC
 * @since 0.1
 */
public class BeanCopier<T> implements Copier<T> {

    private Object source;
    private T dest;
    private CopyOptions copyOptions;

    /**
     * 创建BeanCopier
     *
     * @param <T>         目标Bean类型
     * @param source      来源对象，可以是Bean或者Map
     * @param dest        目标Bean对象
     * @param copyOptions 拷贝属性选项
     * @return BeanCopier
     */
    public static <T> BeanCopier<T> create(Object source, T dest, CopyOptions copyOptions) {
        return new BeanCopier<>(source, dest, copyOptions);
    }

    /**
     * 构造
     *
     * @param source      来源对象，可以是Bean或者Map
     * @param dest        目标Bean对象
     * @param copyOptions 拷贝属性选项
     */
    public BeanCopier(Object source, T dest, CopyOptions copyOptions) {
        this.source = source;
        this.dest = dest;
        this.copyOptions = copyOptions;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T copy() {
        if (null != this.source) {
            if (this.source instanceof ValueProvider) {
                valueProviderToBean((ValueProvider<String>) this.source, this.dest);
            } else if (this.source instanceof Map) {
                mapToBean((Map<?, ?>) this.source, this.dest);
            } else {
                beanToBean(this.source, this.dest);
            }
        }
        return this.dest;
    }

    /**
     * Bean和Bean之间属性拷贝
     *
     * @param providerBean 来源Bean
     * @param destBean     目标Bean
     */
    private void beanToBean(Object providerBean, Object destBean) {
        valueProviderToBean(new BeanValueProvider(providerBean, this.copyOptions.ignoreCase, this.copyOptions.ignoreError), destBean);
    }

    /**
     * Map转Bean属性拷贝
     *
     * @param map  Map
     * @param bean Bean
     */
    private void mapToBean(Map<?, ?> map, Object bean) {
        valueProviderToBean(new MapValueProvider(map, this.copyOptions.ignoreCase), bean);
    }

    /**
     * 值提供器转Bean
     *
     * @param valueProvider 值提供器
     * @param bean          Bean
     */
    private void valueProviderToBean(ValueProvider<String> valueProvider, Object bean) {
        if (null == valueProvider) {
            return;
        }

        Class<?> actualEditable = bean.getClass();
        if (copyOptions.editable != null) {
            // 检查限制类是否为target的父类或接口
            if (false == copyOptions.editable.isInstance(bean)) {
                throw new IllegalArgumentException(UStringUtil.format("Target class [{}] not assignable to Editable class [{}]", bean.getClass().getName(), copyOptions.editable.getName()));
            }
            actualEditable = copyOptions.editable;
        }
        final HashSet<String> ignoreSet = (null != copyOptions.ignoreProperties) ? UCollUtil.newHashSet(copyOptions.ignoreProperties) : null;
        final Map<String, String> fieldReverseMapping = copyOptions.getReversedMapping();

        final Collection<PropDesc> props = UBeanUtil.getBeanDesc(actualEditable).getProps();
        String fieldName;
        Object value;
        Method setterMethod;
        Class<?> propClass;
        for (PropDesc prop : props) {
            // 获取值
            fieldName = prop.getFieldName();
            if (UCollUtil.contains(ignoreSet, fieldName)) {
                // 目标属性值被忽略或值提供者无此key时跳过
                continue;
            }
            final String providerKey = mappingKey(fieldReverseMapping, fieldName);
            if (false == valueProvider.containsKey(providerKey)) {
                //无对应值可提供
                continue;
            }
            setterMethod = prop.getSetter();
            if (null == setterMethod) {
                //Setter方法不存在跳过
                continue;
            }
            value = valueProvider.value(providerKey, UTypeUtil.getFirstParamType(setterMethod));
            if (null == value && copyOptions.ignoreNullValue) {
                continue;// 当允许跳过空时，跳过
            }

            try {
                // valueProvider在没有对值做转换且当类型不匹配的时候，执行默认转换
                propClass = prop.getFieldClass();
                if (false == propClass.isInstance(value)) {
                    value = Convert.convert(propClass, value);
                    if (null == value && copyOptions.ignoreNullValue) {
                        continue;// 当允许跳过空时，跳过
                    }
                }

                // 执行set方法注入值
                setterMethod.invoke(bean, value);
            } catch (Exception e) {
                if (copyOptions.ignoreError) {
                    continue;// 忽略注入失败
                } else {
                    throw new UtilException(e, "Inject [{}] error!", prop.getFieldName());
                }
            }
        }
    }

    /**
     * 获取指定字段名对应的映射值
     *
     * @param fieldReverseMapping 反向映射Map
     * @param fieldName           字段名
     * @return 映射值，无对应值返回字段名
     */
    private static String mappingKey(Map<String, String> fieldReverseMapping, String fieldName) {
        if (UMapUtil.isEmpty(fieldReverseMapping)) {
            return fieldName;
        }
        return UObjectUtil.defaultIfNull(fieldReverseMapping.get(fieldName), fieldName);
    }
}