package com.lc.utils;

import com.lc.core.collection.ArrayIter;
import com.lc.core.collection.EnumerationIter;
import com.lc.core.convert.ConverterRegistry;
import com.lc.exception.UtilException;

import java.lang.reflect.Type;
import java.util.*;

/**
 * 集合相关工具类<p>
 * 此工具方法针对{@link Collection}及其实现类封装的工具。<p>
 * 由于{@link Collection} 实现了{@link Iterable}接口，因此部分工具此类不提供，而是在{@link UIterUtil} 中提供
 *
 * @author LC
 * @see UCollUtil
 * @since 0.1
 */
public class UCollUtil {

    /**
     * 创建新的集合对象
     *
     * @param <T>            集合类型
     * @param collectionType 集合类型
     * @return 集合类型对应的实例
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> Collection<T> create(Class<?> collectionType) {
        Collection<T> list = null;
        if (collectionType.isAssignableFrom(AbstractCollection.class)) {
            // 抽象集合默认使用ArrayList
            list = new ArrayList<>();
        }

        // Set
        else if (collectionType.isAssignableFrom(HashSet.class)) {
            list = new HashSet<>();
        } else if (collectionType.isAssignableFrom(LinkedHashSet.class)) {
            list = new LinkedHashSet<>();
        } else if (collectionType.isAssignableFrom(TreeSet.class)) {
            list = new TreeSet<>();
        } else if (collectionType.isAssignableFrom(EnumSet.class)) {
            list = (Collection<T>) EnumSet.noneOf((Class<Enum>) UClassUtil.getTypeArgument(collectionType));
        }

        // List
        else if (collectionType.isAssignableFrom(ArrayList.class)) {
            list = new ArrayList<>();
        } else if (collectionType.isAssignableFrom(LinkedList.class)) {
            list = new LinkedList<>();
        }

        // Others，直接实例化
        else {
            try {
                list = (Collection<T>) UReflectUtil.newInstance(collectionType);
            } catch (Exception e) {
                throw new UtilException(e);
            }
        }
        return list;
    }

    /**
     * 判断指定集合是否包含指定值，如果集合为空（null或者空），返回{@code false}，否则找到元素返回{@code true}
     *
     * @param collection 集合
     * @param value      需要查找的值
     * @return 如果集合为空（null或者空），返回{@code false}，否则找到元素返回{@code true}
     */
    public static boolean contains(final Collection<?> collection, Object value) {
        return isNotEmpty(collection) && collection.contains(value);
    }

    /**
     * 新建一个HashSet
     *
     * @param <T>      集合元素类型
     * @param isSorted 是否有序，有序返回 {@link LinkedHashSet}，否则返回 {@link HashSet}
     * @param ts       元素数组
     * @return HashSet对象
     */
    @SafeVarargs
    public static <T> HashSet<T> newHashSet(boolean isSorted, T... ts) {
        if (null == ts) {
            return isSorted ? new LinkedHashSet<T>() : new HashSet<T>();
        }
        int initialCapacity = Math.max((int) (ts.length / .75f) + 1, 16);
        HashSet<T> set = isSorted ? new LinkedHashSet<T>(initialCapacity) : new HashSet<T>(initialCapacity);
        for (T t : ts) {
            set.add(t);
        }
        return set;
    }

    /**
     * 新建一个HashSet
     *
     * @param <T> 集合元素类型
     * @param ts  元素数组
     * @return HashSet对象
     */
    @SafeVarargs
    public static <T> HashSet<T> newHashSet(T... ts) {
        return newHashSet(false, ts);
    }

    /**
     * 以 conjunction 为分隔符将集合转换为字符串<br>
     * 如果集合元素为数组、{@link Iterable}或{@link Iterator}，则递归组合其为字符串
     *
     * @param <T>         集合元素类型
     * @param iterable    {@link Iterable}
     * @param conjunction 分隔符
     * @return 连接后的字符串
     * @see UIterUtil#join(Iterable, CharSequence)
     */
    public static <T> String join(Iterable<T> iterable, CharSequence conjunction) {
        return UIterUtil.join(iterable, conjunction);
    }

    /**
     * Iterable是否为空
     *
     * @param iterable Iterable对象
     * @return 是否为空
     * @see UIterUtil#isNotEmpty(Iterable)
     */
    public static boolean isNotEmpty(Iterable<?> iterable) {
        return UIterUtil.isNotEmpty(iterable);
    }

    /**
     * 新建一个ArrayList
     *
     * @param <T>    集合元素类型
     * @param values 数组
     * @return ArrayList对象
     */
    @SafeVarargs
    public static <T> ArrayList<T> newArrayList(T... values) {
        return (ArrayList<T>) list(false, values);
    }

    /**
     * 新建一个List
     *
     * @param <T>      集合元素类型
     * @param isLinked 是否新建LinkedList
     * @param values   数组
     * @return List对象
     */
    @SafeVarargs
    public static <T> List<T> list(boolean isLinked, T... values) {
        if (UArrayUtil.isEmpty(values)) {
            return list(isLinked);
        }
        List<T> arrayList = isLinked ? new LinkedList<T>() : new ArrayList<T>(values.length);
        for (T t : values) {
            arrayList.add(t);
        }
        return arrayList;
    }

    /**
     * 将指定对象全部加入到集合中<br>
     * 提供的对象如果为集合类型，会自动转换为目标元素类型<br>
     *
     * @param <T>         元素类型
     * @param collection  被加入的集合
     * @param value       对象，可能为Iterator、Iterable、Enumeration、Array，或者与集合元素类型一致
     * @param elementType 元素类型，为空时，使用Object类型来接纳所有类型
     * @return 被加入集合
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> Collection<T> addAll(Collection<T> collection, Object value, Type elementType) {
        if (null == collection || null == value) {
            return collection;
        }
        if (null == elementType) {
            // 元素类型为空时，使用Object类型来接纳所有类型
            elementType = Object.class;
        } else {
            final Class<?> elementRowType = UTypeUtil.getClass(elementType);
            if (elementRowType.isInstance(value) && false == Iterable.class.isAssignableFrom(elementRowType)) {
                // 其它类型按照单一元素处理
                collection.add((T) value);
                return collection;
            }
        }

        Iterator iter;
        if (value instanceof Iterator) {
            iter = (Iterator) value;
        } else if (value instanceof Iterable) {
            iter = ((Iterable) value).iterator();
        } else if (value instanceof Enumeration) {
            iter = new EnumerationIter<>((Enumeration) value);
        } else if (UArrayUtil.isArray(value)) {
            iter = new ArrayIter<>(value);
        } else {
            throw new UtilException("Unsupport value type [] !", value.getClass());
        }

        final ConverterRegistry convert = ConverterRegistry.getInstance();
        while (iter.hasNext()) {
            try {
                collection.add((T) convert.convert(elementType, iter.next()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return collection;
    }
}
