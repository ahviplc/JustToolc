package com.lc.core.bean.copier.provider;

import com.lc.core.bean.copier.ValueProvider;
import com.lc.core.bean.BeanDesc.PropDesc;
import com.lc.exception.UtilException;
import com.lc.utils.UBeanUtil;
import com.lc.utils.UStringUtil;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Bean的值提供者
 *
 * @author looly
 */
public class BeanValueProvider implements ValueProvider<String> {

    private Object source;
    private boolean ignoreError;
    final Map<String, PropDesc> sourcePdMap;

    /**
     * 构造
     *
     * @param bean        Bean
     * @param ignoreCase  是否忽略字段大小写
     * @param ignoreError 是否忽略字段值读取错误
     */
    public BeanValueProvider(Object bean, boolean ignoreCase, boolean ignoreError) {
        this.source = bean;
        this.ignoreError = ignoreError;
        sourcePdMap = UBeanUtil.getBeanDesc(source.getClass()).getPropMap(ignoreCase);
    }

    @Override
    public Object value(String key, Type valueType) {
        PropDesc sourcePd = sourcePdMap.get(key);
        if (null == sourcePd && (Boolean.class == valueType || boolean.class == valueType)) {
            //boolean类型字段字段名支持两种方式
            sourcePd = sourcePdMap.get(UStringUtil.upperFirstAndAddPre(key, "is"));
        }

        if (null != sourcePd) {
            final Method getter = sourcePd.getGetter();
            if (null != getter) {
                try {
                    return getter.invoke(source);
                } catch (Exception e) {
                    if (false == ignoreError) {
                        throw new UtilException(e, "Inject [{}] error!", key);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(String key) {
        return sourcePdMap.containsKey(key) || sourcePdMap.containsKey(UStringUtil.upperFirstAndAddPre(key, "is"));
    }
}
