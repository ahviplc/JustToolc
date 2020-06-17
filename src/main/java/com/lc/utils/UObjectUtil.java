package com.lc.utils;

import com.lc.core.io.FastByteArrayOutputStream;
import com.lc.exception.UtilException;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 一些通用的函数
 *
 * @author LC
 * @since 0.1
 */
public class UObjectUtil {

    /**
     * 比较两个对象是否相等。<br>
     * 相同的条件有两个，满足其一即可：<br>
     * <ol>
     * <li>obj1 == null &amp;&amp; obj2 == null</li>
     * <li>obj1.equals(obj2)</li>
     * </ol>
     * 1. obj1 == null &amp;&amp; obj2 == null 2. obj1.equals(obj2)
     *
     * @param obj1 对象1
     * @param obj2 对象2
     * @return 是否相等
     */
    public static boolean equal(Object obj1, Object obj2) {
//		return (obj1 != null) ? (obj1.equals(obj2)) : (obj2 == null);
        return (obj1 == obj2) || (obj1 != null && obj1.equals(obj2));
    }

    /**
     * 检查对象是否为null<br>
     * 判断标准为：
     * <pre>
     * 1. == null
     * 2. equals(null)
     * </pre>
     *
     * @param obj 对象
     * @return 是否为null
     */
    public static boolean isNull(Object obj) {
        return null == obj || obj.equals(null);
    }

    /**
     * {@code null}安全的对象比较，{@code null}对象排在末尾
     *
     * @param <T> 被比较对象类型
     * @param c1  对象1，可以为{@code null}
     * @param c2  对象2，可以为{@code null}
     * @return 比较结果，如果c1 &lt; c2，返回数小于0，c1==c2返回0，c1 &gt; c2 大于0
     * @see java.util.Comparator#compare(Object, Object)
     */
    public static <T extends Comparable<? super T>> int compare(T c1, T c2) {
        return UCompareUtil.compare(c1, c2);
    }

    /**
     * 检查是否为有效的数字<br>
     * 检查Double和Float是否为无限大，或者Not a Number<br>
     * 非数字类型和Null将返回true
     *
     * @param obj 被检查类型
     * @return 检查结果，非数字类型和Null将返回true
     */
    public static boolean isValidIfNumber(Object obj) {
        if (obj != null && obj instanceof Number) {
            if (obj instanceof Double) {
                if (((Double) obj).isInfinite() || ((Double) obj).isNaN()) {
                    return false;
                }
            } else if (obj instanceof Float) {
                if (((Float) obj).isInfinite() || ((Float) obj).isNaN()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 序列化后拷贝流的方式克隆<br>
     * 对象必须实现Serializable接口
     *
     * @param <T> 对象类型
     * @param obj 被克隆对象
     * @return 克隆后的对象
     * @throws UtilException IO异常和ClassNotFoundException封装
     */
    @SuppressWarnings("unchecked")
    public static <T> T cloneByStream(T obj) {
        if (null == obj || false == (obj instanceof Serializable)) {
            return null;
        }
        final FastByteArrayOutputStream byteOut = new FastByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(byteOut);
            out.writeObject(obj);
            out.flush();
            final ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(byteOut.toByteArray()));
            return (T) in.readObject();
        } catch (Exception e) {
            throw new UtilException(e);
        } finally {
            UIoUtil.close(out);
        }
    }

    /**
     * 克隆对象<br>
     * 如果对象实现Cloneable接口，调用其clone方法<br>
     * 如果实现Serializable接口，执行深度克隆<br>
     * 否则返回<code>null</code>
     *
     * @param <T> 对象类型
     * @param obj 被克隆对象
     * @return 克隆后的对象
     */
    public static <T> T clone(T obj) {
        T result = UArrayUtil.clone(obj);
        if (null == result) {
            if (obj instanceof Cloneable) {
                result = UReflectUtil.invoke(obj, "clone");
            } else {
                result = cloneByStream(obj);
            }
        }
        return result;
    }

    /**
     * 如果给定对象为{@code null}返回默认值
     *
     * <pre>
     * ObjectUtil.defaultIfNull(null, null)      = null
     * ObjectUtil.defaultIfNull(null, "")        = ""
     * ObjectUtil.defaultIfNull(null, "zz")      = "zz"
     * ObjectUtil.defaultIfNull("abc", *)        = "abc"
     * ObjectUtil.defaultIfNull(Boolean.TRUE, *) = Boolean.TRUE
     * </pre>
     *
     * @param <T>          对象类型
     * @param object       被检查对象，可能为{@code null}
     * @param defaultValue 被检查对象为{@code null}返回的默认值，可以为{@code null}
     * @return 被检查对象为{@code null}返回默认值，否则返回原值
     */
    public static <T> T defaultIfNull(final T object, final T defaultValue) {
        return (null != object) ? object : defaultValue;
    }
}
