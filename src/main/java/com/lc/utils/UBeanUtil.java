package com.lc.utils;

import com.lc.core.bean.BeanDesc;
import com.lc.core.bean.BeanDesc.PropDesc;
import com.lc.core.bean.BeanDescCache;
import com.lc.core.bean.copier.BeanCopier;
import com.lc.core.bean.copier.CopyOptions;
import com.lc.lang.Editor;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Bean工具类
 *
 * <p>
 * 把一个拥有对属性进行set和get方法的类，我们就可以称之为JavaBean。
 * </p>
 *
 * @author LC
 * @since 0.01
 */
public class UBeanUtil {

    /**
     * 判断是否为Bean对象<br>
     * 判定方法是是否存在只有一个参数的setXXX方法
     *
     * @param clazz 待测试类
     * @return 是否为Bean对象
     */
    public static boolean isBean(Class<?> clazz) {
        if (UClassUtil.isNormalClass(clazz)) {
            final Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (method.getParameterTypes().length == 1 && method.getName().startsWith("set")) {
                    // 检测包含标准的setXXX方法即视为标准的JavaBean
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 使用Map填充Bean对象
     *
     * @param <T>           Bean类型
     * @param map           Map
     * @param bean          Bean
     * @param isToCamelCase 是否将Map中的下划线风格key转换为驼峰风格
     * @param copyOptions   属性复制选项 {@link CopyOptions}
     * @return Bean
     */
    public static <T> T fillBeanWithMap(Map<?, ?> map, T bean, boolean isToCamelCase, CopyOptions copyOptions) {
        if (UMapUtil.isEmpty(map)) {
            return bean;
        }
        if (isToCamelCase) {
            map = UMapUtil.toCamelCaseMap(map);
        }
        return BeanCopier.create(map, bean, copyOptions).copy();
    }

    /**
     * 使用Map填充Bean对象
     *
     * @param <T>         Bean类型
     * @param map         Map
     * @param bean        Bean
     * @param copyOptions 属性复制选项 {@link CopyOptions}
     * @return Bean
     */
    public static <T> T fillBeanWithMap(Map<?, ?> map, T bean, CopyOptions copyOptions) {
        return fillBeanWithMap(map, bean, false, copyOptions);
    }

    /**
     * 使用Map填充Bean对象，忽略大小写
     *
     * @param <T>           Bean类型
     * @param map           Map
     * @param bean          Bean
     * @param isIgnoreError 是否忽略注入错误
     * @return Bean
     */
    public static <T> T fillBeanWithMapIgnoreCase(Map<?, ?> map, T bean, boolean isIgnoreError) {
        return fillBeanWithMap(map, bean, CopyOptions.create().setIgnoreCase(true).setIgnoreError(isIgnoreError));
    }

    /**
     * 获取{@link BeanDesc} Bean描述信息
     *
     * @param clazz Bean类
     * @return {@link BeanDesc}
     */
    public static BeanDesc getBeanDesc(Class<?> clazz) {
        BeanDesc beanDesc = BeanDescCache.INSTANCE.getBeanDesc(clazz);
        if (null == beanDesc) {
            beanDesc = new BeanDesc(clazz);
            BeanDescCache.INSTANCE.putBeanDesc(clazz, beanDesc);
        }
        return beanDesc;
    }

    // --------------------------------------------------------------------------------------------- beanToMap

    /**
     * 对象转Map<br>
     * 通过实现{@link Editor} 可以自定义字段值，如果这个Editor返回null则忽略这个字段，以便实现：
     *
     * <pre>
     * 1. 字段筛选，可以去除不需要的字段
     * 2. 字段变换，例如实现驼峰转下划线
     * 3. 自定义字段前缀或后缀等等
     * </pre>
     *
     * @param bean            bean对象
     * @param targetMap       目标的Map
     * @param ignoreNullValue 是否忽略值为空的字段
     * @param keyEditor       属性字段（Map的key）编辑器，用于筛选、编辑key
     * @return Map
     */
    public static Map<String, Object> beanToMap(Object bean, Map<String, Object> targetMap, boolean ignoreNullValue, Editor<String> keyEditor) {
        if (bean == null) {
            return null;
        }

        final Collection<PropDesc> props = UBeanUtil.getBeanDesc(bean.getClass()).getProps();

        String key;
        Method getter;
        Object value;
        for (PropDesc prop : props) {
            key = prop.getFieldName();
            // 过滤class属性
            // 得到property对应的getter方法
            getter = prop.getGetter();
            if (null != getter) {
                // 只读取有getter方法的属性
                try {
                    value = getter.invoke(bean);
                } catch (Exception ignore) {
                    continue;
                }
                if (false == ignoreNullValue || (null != value && false == value.equals(bean))) {
                    key = keyEditor.edit(key);
                    if (null != key) {
                        targetMap.put(key, value);
                    }
                }
            }
        }
        return targetMap;
    }

    /**
     * 对象转Map
     *
     * @param bean              bean对象
     * @param targetMap         目标的Map
     * @param isToUnderlineCase 是否转换为下划线模式
     * @param ignoreNullValue   是否忽略值为空的字段
     * @return Map
     */
    public static Map<String, Object> beanToMap(Object bean, Map<String, Object> targetMap, final boolean isToUnderlineCase, boolean ignoreNullValue) {
        if (bean == null) {
            return null;
        }

        return beanToMap(bean, targetMap, ignoreNullValue, new Editor<String>() {

            @Override
            public String edit(String key) {
                return isToUnderlineCase ? UStringUtil.toUnderlineCase(key) : key;
            }
        });
    }

    /**
     * 对象转Map
     *
     * @param bean              bean对象
     * @param isToUnderlineCase 是否转换为下划线模式
     * @param ignoreNullValue   是否忽略值为空的字段
     * @return Map
     */
    public static Map<String, Object> beanToMap(Object bean, boolean isToUnderlineCase, boolean ignoreNullValue) {
        return beanToMap(bean, new HashMap<String, Object>(), isToUnderlineCase, ignoreNullValue);
    }

    /**
     * 对象转Map，不进行驼峰转下划线，不忽略值为空的字段
     *
     * @param bean bean对象
     * @return Map
     */
    public static Map<String, Object> beanToMap(Object bean) {
        return beanToMap(bean, false, false);
    }

    /**
     * Map转换为Bean对象<br>
     * 忽略大小写
     *
     * @param <T>           Bean类型
     * @param map           Map
     * @param beanClass     Bean Class
     * @param isIgnoreError 是否忽略注入错误
     * @return Bean
     */
    public static <T> T mapToBeanIgnoreCase(Map<?, ?> map, Class<T> beanClass, boolean isIgnoreError) {
        return fillBeanWithMapIgnoreCase(map, UReflectUtil.newInstance(beanClass), isIgnoreError);
    }

    /**
     * 使用Map填充Bean对象，可配置将下划线转换为驼峰
     *
     * @param <T>           Bean类型
     * @param map           Map
     * @param bean          Bean
     * @param isToCamelCase 是否将下划线模式转换为驼峰模式
     * @param isIgnoreError 是否忽略注入错误
     * @return Bean
     */
    public static <T> T fillBeanWithMap(Map<?, ?> map, T bean, boolean isToCamelCase, boolean isIgnoreError) {
        return fillBeanWithMap(map, bean, isToCamelCase, CopyOptions.create().setIgnoreError(isIgnoreError));
    }

    // --------------------------------------------------------------------------------------------- mapToBean

    /**
     * Map转换为Bean对象
     *
     * @param <T>           Bean类型
     * @param map           {@link Map}
     * @param beanClass     Bean Class
     * @param isIgnoreError 是否忽略注入错误
     * @return Bean
     */
    public static <T> T mapToBean(Map<?, ?> map, Class<T> beanClass, boolean isIgnoreError) {
        return fillBeanWithMap(map, UReflectUtil.newInstance(beanClass), isIgnoreError);
    }

    // --------------------------------------------------------------------------------------------- fillBeanWithMap

    /**
     * 使用Map填充Bean对象
     *
     * @param <T>           Bean类型
     * @param map           Map
     * @param bean          Bean
     * @param isIgnoreError 是否忽略注入错误
     * @return Bean
     */
    public static <T> T fillBeanWithMap(Map<?, ?> map, T bean, boolean isIgnoreError) {
        return fillBeanWithMap(map, bean, false, isIgnoreError);
    }
}
