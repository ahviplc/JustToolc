package com.lc.utils;

import com.lc.core.date.DateTime;
import com.lc.exception.UtilException;
import com.lc.lang.UUID;
import com.lc.lang.WeightRandom;
import com.lc.lang.WeightRandom.WeightObj;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机工具类
 *
 * @author LC
 */
public class URandomUtil {

    /**
     * 用于随机选的数字
     */
    public static final String BASE_NUMBER = "0123456789";
    /**
     * 用于随机选的字符
     */
    public static final String BASE_CHAR = "abcdefghijklmnopqrstuvwxyz";
    /**
     * 用于随机选的字符和数字
     */
    public static final String BASE_CHAR_NUMBER = BASE_CHAR + BASE_NUMBER;

    /**
     * 获取随机数生成器对象<br>
     * ThreadLocalRandom是JDK 7之后提供并发产生随机数，能够解决多个线程发生的竞争争夺。
     *
     * @return {@link ThreadLocalRandom}
     */
    public static ThreadLocalRandom getRandom() {
        return ThreadLocalRandom.current();
    }

    /**
     * 获取{@link SecureRandom}，类提供加密的强随机数生成器 (RNG)
     *
     * @return {@link SecureRandom}
     */
    public static SecureRandom getSecureRandom() {
        try {
            return SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            throw new UtilException(e);
        }
    }

    /**
     * 获取随机数产生器
     *
     * @param isSecure 是否为强随机数生成器 (RNG)
     * @return {@link Random}
     * @see #getSecureRandom()
     * @see #getRandom()
     */
    public static Random getRandom(boolean isSecure) {
        return isSecure ? getSecureRandom() : getRandom();
    }

    /**
     * 获得指定范围内的随机数
     *
     * @param min 最小数（包含）
     * @param max 最大数（不包含）
     * @return 随机数
     */
    public static int randomInt(int min, int max) {
        return getRandom().nextInt(min, max);
    }

    /**
     * 获得随机数[0, 1)
     *
     * @return 随机数
     */
    public static int randomInt() {
        return getRandom().nextInt();
    }

    /**
     * 获得指定范围内的随机数 [0,limit)
     *
     * @param limit 限制随机数的范围，不包括这个数
     * @return 随机数
     */
    public static int randomInt(int limit) {
        return getRandom().nextInt(limit);
    }

    /**
     * 获得指定范围内的随机数
     *
     * @param min 最小数（包含）
     * @param max 最大数（不包含）
     * @return 随机数
     */
    public static long randomLong(long min, long max) {
        return getRandom().nextLong(min, max);
    }

    /**
     * 获得随机数
     *
     * @return 随机数
     */
    public static long randomLong() {
        return getRandom().nextLong();
    }

    /**
     * 获得指定范围内的随机数 [0,limit)
     *
     * @param limit 限制随机数的范围，不包括这个数
     * @return 随机数
     */
    public static long randomLong(long limit) {
        return getRandom().nextLong(limit);
    }

    /**
     * 获得指定范围内的随机数
     *
     * @param min 最小数（包含）
     * @param max 最大数（不包含）
     * @return 随机数
     */
    public static double randomDouble(double min, double max) {
        return getRandom().nextDouble(min, max);
    }

    /**
     * 获得指定范围内的随机数
     *
     * @param min          最小数（包含）
     * @param max          最大数（不包含）
     * @param scale        保留小数位数
     * @param roundingMode 保留小数的模式 {@link RoundingMode}
     * @return 随机数
     */
    public static double randomDouble(double min, double max, int scale, RoundingMode roundingMode) {
        return UNumberUtil.round(randomDouble(min, max), scale, roundingMode).doubleValue();
    }

    /**
     * 获得随机数[0, 1)
     *
     * @return 随机数
     */
    public static double randomDouble() {
        return getRandom().nextDouble();
    }

    /**
     * 获得指定范围内的随机数
     *
     * @param scale        保留小数位数
     * @param roundingMode 保留小数的模式 {@link RoundingMode}
     * @return 随机数
     */
    public static double randomDouble(int scale, RoundingMode roundingMode) {
        return UNumberUtil.round(randomDouble(), scale, roundingMode).doubleValue();
    }

    /**
     * 获得指定范围内的随机数 [0,limit)
     *
     * @param limit 限制随机数的范围，不包括这个数
     * @return 随机数
     */
    public static double randomDouble(double limit) {
        return getRandom().nextDouble(limit);
    }

    /**
     * 获得指定范围内的随机数
     *
     * @param limit        限制随机数的范围，不包括这个数
     * @param scale        保留小数位数
     * @param roundingMode 保留小数的模式 {@link RoundingMode}
     * @return 随机数
     */
    public static double randomDouble(double limit, int scale, RoundingMode roundingMode) {
        return UNumberUtil.round(randomDouble(limit), scale, roundingMode).doubleValue();
    }

    /**
     * 获得指定范围内的随机数[0, 1)
     *
     * @return 随机数
     */
    public static BigDecimal randomBigDecimal() {
        return UNumberUtil.toBigDecimal(getRandom().nextDouble());
    }

    /**
     * 获得指定范围内的随机数 [0,limit)
     *
     * @param limit 最大数（不包含）
     * @return 随机数
     */
    public static BigDecimal randomBigDecimal(BigDecimal limit) {
        return UNumberUtil.toBigDecimal(getRandom().nextDouble(limit.doubleValue()));
    }

    /**
     * 获得指定范围内的随机数
     *
     * @param min 最小数（包含）
     * @param max 最大数（不包含）
     * @return 随机数
     */
    public static BigDecimal randomBigDecimal(BigDecimal min, BigDecimal max) {
        return UNumberUtil.toBigDecimal(getRandom().nextDouble(min.doubleValue(), max.doubleValue()));
    }

    /**
     * 随机bytes
     *
     * @param length 长度
     * @return bytes
     */
    public static byte[] randomBytes(int length) {
        byte[] bytes = new byte[length];
        getRandom().nextBytes(bytes);
        return bytes;
    }

    /**
     * 随机获得列表中的元素
     *
     * @param <T>  元素类型
     * @param list 列表
     * @return 随机元素
     */
    public static <T> T randomEle(List<T> list) {
        return randomEle(list, list.size());
    }

    /**
     * 随机获得列表中的元素
     *
     * @param <T>   元素类型
     * @param list  列表
     * @param limit 限制列表的前N项
     * @return 随机元素
     */
    public static <T> T randomEle(List<T> list, int limit) {
        return list.get(randomInt(limit));
    }

    /**
     * 随机获得数组中的元素
     *
     * @param <T>   元素类型
     * @param array 列表
     * @return 随机元素
     * @since 3.3.0
     */
    public static <T> T randomEle(T[] array) {
        return randomEle(array, array.length);
    }

    /**
     * 随机获得数组中的元素
     *
     * @param <T>   元素类型
     * @param array 列表
     * @param limit 限制列表的前N项
     * @return 随机元素
     * @since 3.3.0
     */
    public static <T> T randomEle(T[] array, int limit) {
        return array[randomInt(limit)];
    }

    /**
     * 随机获得列表中的一定量元素
     *
     * @param <T>   元素类型
     * @param list  列表
     * @param count 随机取出的个数
     * @return 随机元素
     */
    public static <T> List<T> randomEles(List<T> list, int count) {
        final List<T> result = new ArrayList<T>(count);
        int limit = list.size();
        while (result.size() < count) {
            result.add(randomEle(list, limit));
        }

        return result;
    }

    /**
     * 随机获得列表中的一定量的不重复元素，返回Set
     *
     * @param <T>        元素类型
     * @param collection 列表
     * @param count      随机取出的个数
     * @return 随机元素
     * @throws IllegalArgumentException 需要的长度大于给定集合非重复总数
     */
    public static <T> Set<T> randomEleSet(Collection<T> collection, int count) {
        ArrayList<T> source = new ArrayList<>(new HashSet<>(collection));
        if (count > source.size()) {
            throw new IllegalArgumentException("Count is larger than collection distinct size !");
        }

        final HashSet<T> result = new HashSet<T>(count);
        int limit = collection.size();
        while (result.size() < count) {
            result.add(randomEle(source, limit));
        }

        return result;
    }

    /**
     * 获得一个随机的字符串（只包含数字和字符）
     *
     * @param length 字符串的长度
     * @return 随机字符串
     */
    public static String randomString(int length) {
        return randomString(BASE_CHAR_NUMBER, length);
    }

    /**
     * 获得一个随机的字符串（只包含数字和大写字符）
     *
     * @param length 字符串的长度
     * @return 随机字符串
     */
    public static String randomStringUpper(int length) {
        return randomString(BASE_CHAR_NUMBER, length).toUpperCase();
    }

    /**
     * 获得一个只包含数字的字符串
     *
     * @param length 字符串的长度
     * @return 随机字符串
     */
    public static String randomNumbers(int length) {
        return randomString(BASE_NUMBER, length);
    }

    /**
     * 获得一个随机的字符串
     *
     * @param baseString 随机字符选取的样本
     * @param length     字符串的长度
     * @return 随机字符串
     */
    public static String randomString(String baseString, int length) {
        final StringBuilder sb = new StringBuilder();

        if (length < 1) {
            length = 1;
        }
        int baseLength = baseString.length();
        for (int i = 0; i < length; i++) {
            int number = getRandom().nextInt(baseLength);
            sb.append(baseString.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 随机数字，数字为0~9单个数字
     *
     * @return 随机数字字符
     */
    public static int randomNumber() {
        return randomChar(BASE_NUMBER);
    }

    /**
     * 随机字母或数字，小写
     *
     * @return 随机字符
     */
    public static char randomChar() {
        return randomChar(BASE_CHAR_NUMBER);
    }

    /**
     * 随机字符
     *
     * @param baseString 随机字符选取的样本
     * @return 随机字符
     */
    public static char randomChar(String baseString) {
        return baseString.charAt(getRandom().nextInt(baseString.length()));
    }

    /**
     * 生成随机颜色
     *
     * @return 随机颜色
     */
    public static Color randomColor() {
        final Random random = getRandom();
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

    /**
     * 带有权重的随机生成器
     * {@link WeightRandom}
     *
     * @param weightObjs 带有权重的对象列表
     * @return 权重的随机生成器
     */
    public static <T> WeightRandom<T> weightRandom(WeightObj<T>[] weightObjs) {
        return new WeightRandom<>(weightObjs);
    }

    /**
     * 带有权重的随机生成器
     * {@link WeightRandom}
     *
     * @param weightObjs 带有权重的对象列表
     * @return 权重的随机生成器
     */
    public static <T> WeightRandom<T> weightRandom(Iterable<WeightObj<T>> weightObjs) {
        return new WeightRandom<>(weightObjs);
    }

    // ------------------------------------------------------------------- UUID

    /**
     * randomUUI 废弃
     * 请使用{@link UIdUtil#randomUUID()}
     *
     * @return 随机UUID
     * @deprecated 请使用{@link UIdUtil#randomUUID()}
     */
    @Deprecated
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * simpleUUID 废弃
     * 请使用{@link UIdUtil#simpleUUID()}
     * <p>
     * 简化的UUID，去掉了横线
     *
     * @return 简化的UUID，去掉了横线
     * @deprecated 请使用{@link UIdUtil#simpleUUID()}
     */
    @Deprecated
    public static String simpleUUID() {
        return UUID.randomUUID().toString(true);
    }

    /**
     * 以当天为基准，随机产生一个日期
     *
     * @param min 偏移最小天，可以为负数表示过去的时间
     * @param max 偏移最大天，可以为负数表示过去的时间
     * @return 随机日期（随机天，其它时间不变）
     */
    public static DateTime randomDay(int min, int max) {
        return UDateUtil.offsetDay(UDateUtil.date(), randomInt(min, max));
    }
}
