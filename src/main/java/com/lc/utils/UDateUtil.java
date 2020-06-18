package com.lc.utils;

import com.lc.core.date.BetweenFormater;
import com.lc.core.date.DateField;
import com.lc.core.date.DatePattern;
import com.lc.core.date.DateTime;
import com.lc.core.date.format.DateParser;
import com.lc.core.date.format.FastDateFormat;
import com.lc.exception.DateException;
import com.lc.lang.Validator;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

/**
 * 日期时间工具类
 *
 * @author LC
 * @since 0.1
 */
public class UDateUtil {
    // 时间元素
    private static final String YEAR = "year";
    private static final String MONTH = "month";
    private static final String WEEK = "week";
    private static final String DAY = "day";
    private static final String HOUR = "hour";
    private static final String MINUTE = "minute";
    private static final String SECOND = "second";

    // 星期元素
    private static final String MONDAY = "MONDAY";// 星期一
    private static final String TUESDAY = "TUESDAY";// 星期二
    private static final String WEDNESDAY = "WEDNESDAY";// 星期三
    private static final String THURSDAY = "THURSDAY";// 星期四
    private static final String FRIDAY = "FRIDAY";// 星期五
    private static final String SATURDAY = "SATURDAY";// 星期六
    private static final String SUNDAY = "SUNDAY";// 星期日

    // 根据指定格式显示日期和时间
    /**
     * yyyy-MM-dd
     */
    private static final DateTimeFormatter yyyyMMdd_EN = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    /**
     * yyyy-MM-dd HH
     */
    private static final DateTimeFormatter yyyyMMddHH_EN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
    /**
     * yyyy-MM-dd HH:mm
     */
    private static final DateTimeFormatter yyyyMMddHHmm_EN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    /**
     * yyyyMMddHHmm
     */
    private static final DateTimeFormatter yyyyMMddHHmm_DEFAULT_TIMESTAMP_EN = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    private static final DateTimeFormatter yyyyMMddHHmmss_EN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    /**
     * HH:mm:ss
     */
    private static final DateTimeFormatter HHmmss_EN = DateTimeFormatter.ofPattern("HH:mm:ss");
    /**
     * yyyy年MM月dd日
     */
    private static final DateTimeFormatter yyyyMMdd_CN = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
    /**
     * yyyy年MM月dd日HH时
     */
    private static final DateTimeFormatter yyyyMMddHH_CN = DateTimeFormatter.ofPattern("yyyy年MM月dd日HH时");
    /**
     * yyyy年MM月dd日HH时mm分
     */
    private static final DateTimeFormatter yyyyMMddHHmm_CN = DateTimeFormatter.ofPattern("yyyy年MM月dd日HH时mm分");
    /**
     * yyyy年MM月dd日HH时mm分ss秒
     */
    private static final DateTimeFormatter yyyyMMddHHmmss_CN = DateTimeFormatter.ofPattern("yyyy年MM月dd日HH时mm分ss秒");
    /**
     * HH时mm分ss秒
     */
    private static final DateTimeFormatter HHmmss_CN = DateTimeFormatter.ofPattern("HH时mm分ss秒");
    /**
     * 缺省短日期格式(yyyy-MM-dd)
     */
    public final static String DEFAULT_SHORT_DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 时间格式(HH:mm:ss:SS)
     */
    public final static String TIME_FORMAT = "HH:mm:ss:SS";
    /**
     * 缺省长日期格式
     */
    public final static String DEFAULT_LONG_DATE_FORMAT = DEFAULT_SHORT_DATE_FORMAT + UCharUtil.SPACE + TIME_FORMAT;
    /**
     * 日期时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期时间格式 年月(yyyy-MM)
     */
    public final static String DATETIME_FORMAT_NIANYUE = "yyyy-MM";

    /**
     * 日期时间格式 年月日(yyyy-MM-dd)
     */
    public final static String DATETIME_FORMAT_NIANYUERI = "yyyy-MM-dd";
    /**
     * Java能支持的最小日期字符串（yyyy-MM-dd）
     */
    public final static String JAVA_MIN_SHORT_DATE_STR = "1970-01-01";

    /**
     * Java能支持的最小日期字符串（yyyy-MM-dd HH:mm:ss:SS）
     */
    public final static String JAVA_MIN_LONG_DATE_STR = "1970-01-01 00:00:00:00";
    /**
     * Java能支持的最大日期字符串（yyyy-MM-dd）
     */
    public final static String JAVA_MAX_SHORT_DATE_STR = "9999-12-31";
    /**
     * Java能支持的最大日期字符串（yyyy-MM-dd HH:mm:ss:SS）
     */
    public final static String JAVA_MAX_LONG_DATE_STR = "9999-12-31 23:59:59:99";
    /**
     * 默认的时间段显示格式
     */
    public final static String DEFAULT_PERIOD_FORMAT = "{0}天{1}小时{2}分钟";

    // 本地时间显示格式：区分中文和外文显示
    private static final DateTimeFormatter shotDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
    private static final DateTimeFormatter fullDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
    private static final DateTimeFormatter longDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
    private static final DateTimeFormatter mediumDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);

    /**
     * 防止非法实例化
     */
    private UDateUtil() {
    }

    /**
     * 获取当前日期
     *
     * @return 返回字符串时间 日期格式:yyyy-MM-dd
     */
    public static String getNowDate_EN() {
        return String.valueOf(LocalDate.now());
    }

    /**
     * 获取当前日期(yyyy-MM-dd HH:mm:ss)
     *
     * @return 返回字符串时间 日期时间格式:yyyy-MM-dd HH:mm:ss
     */
    public static String getNowTime_EN() {
        return LocalDateTime.now().format(yyyyMMddHHmmss_EN);
    }

    /**
     * 获取当前时间（yyyy-MM-dd HH）
     *
     * @return 返回字符串时间 日期时间格式:yyyy-MM-dd HH
     */
    public static String getNowTime_EN_yMdH() {
        return LocalDateTime.now().format(yyyyMMddHH_EN);
    }

    /**
     * 获取当前时间（yyyy年MM月dd日HH时）
     *
     * @return 返回字符串时间 日期时间格式:yyyy年MM月dd日HH时
     */
    public static String getNowTime_CN_yMdH() {
        return LocalDateTime.now().format(yyyyMMddHH_CN);
    }

    /**
     * 获取当前时间（yyyy-MM-dd HH:mm）
     *
     * @return 返回字符串时间 日期时间格式:yyyy-MM-dd HH:mm
     */
    public static String getNowTime_EN_yMdHm() {
        return LocalDateTime.now().format(yyyyMMddHHmm_EN);
    }

    /**
     * 获取当前时间（yyyyMMddHHmm）
     *
     * @return 返回字符串时间 日期时间格式:yyyyMMddHHmm
     */
    public static String getNowTime_EN_yyyyMMddHHmm() {
        return LocalDateTime.now().format(yyyyMMddHHmm_DEFAULT_TIMESTAMP_EN);
    }

    /**
     * 获取当前时间（yyyy年MM月dd日HH时mm分）
     *
     * @return 返回字符串时间 日期时间格式:yyyy年MM月dd日HH时mm分
     */
    public static String getNowTime_CN_yMdHm() {
        return LocalDateTime.now().format(yyyyMMddHHmm_CN);
    }

    /**
     * 获取当前时间（HH时mm分ss秒）
     *
     * @return 返回字符串时间 日期时间格式:HH时mm分ss秒
     */
    public static String getNowTime_CN_HHmmss() {
        return LocalDateTime.now().format(HHmmss_CN);
    }

    /**
     * 根据日期时间格式，获取当前时间
     *
     * @param formatStr 传入的日期时间格式
     * @return 返回字符串时间 日期时间格式:按照传入的日期时间格式
     */
    public static String getTime(String formatStr) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(formatStr));
    }

    /**
     * 获取中文的当前日期
     *
     * @return 返回字符串时间 日期时间格式:yyyy年mm月dd日
     */
    public static String getNowDate_CN() {
        return LocalDate.now().format(yyyyMMdd_CN);
    }

    /**
     * 获取中文当前时间
     *
     * @return 返回字符串时间 日期时间格式:yyyy年MM月dd日HH时mm分ss秒
     */
    public static String getNowTime_CN() {
        return LocalDateTime.now().format(yyyyMMddHHmmss_CN);
    }

    /**
     * 简写本地当前日期：yy-M-dd<br>
     * 例如：19-3-30为2019年3月30日
     *
     * @return 返回字符串时间 日期格式:字符串yy-M-dd
     */
    public static String getNowLocalTime_shot() {
        return LocalDateTime.now().format(shotDate);
    }

    /**
     * 根据当地日期显示格式：yyyy年M月dd日 星期？（中国）
     *
     * @return 返回字符串时间 日期星期格式: 形如:2019年3月30日 星期六
     */
    public static String getNowLocalTime_full() {
        return LocalDateTime.now().format(fullDate);
    }

    /**
     * 根据当地显示日期格式：yyyy年M月dd日（中国）
     *
     * @return 返回字符串时间 日期格式: 形如:2019年3月30日
     */
    public static String getNowLocalTime_long() {
        return LocalDateTime.now().format(longDate);
    }

    /**
     * 根据当地显示日期格式：yyyy-M-dd（中国）
     *
     * @return 返回字符串时间 日期格式: 形如:2019-3-30
     */
    public static String getNowLocalTime_medium() {
        return LocalDateTime.now().format(mediumDate);
    }

    /**
     * 获取当前日期的节点时间（年，月，周，日，时，分，秒）
     *
     * @param node 日期中的节点元素（年，月，周，日，时，分，秒）
     * @return 节点数字，如创建此方法的时间：年 2019，月 3，日 30，周 6
     */
    public static Integer getNodeTime(String node) {
        LocalDateTime today = LocalDateTime.now();
        Integer resultNode = null;
        switch (node) {
            case YEAR:
                resultNode = today.getYear();
                break;
            case MONTH:
                resultNode = today.getMonthValue();
                break;
            case WEEK:
                resultNode = transformWeekEN2Num(String.valueOf(today.getDayOfWeek()));
                break;
            case DAY:
                resultNode = today.getDayOfMonth();
                break;
            case HOUR:
                resultNode = today.getHour();
                break;
            case MINUTE:
                resultNode = today.getMinute();
                break;
            case SECOND:
                resultNode = today.getSecond();
                break;
            default:
                // 当前日期是当前年的第几天。例如：2019/1/3是2019年的第三天
                resultNode = today.getDayOfYear();
                break;
        }
        return resultNode;
    }

    /**
     * 将英文星期转换成数字
     *
     * @param enWeek 英文星期
     * @return int，如果数字小于0，则检查，看是否输入错误 or 入参为null
     */
    public static int transformWeekEN2Num(String enWeek) {
        if (MONDAY.equals(enWeek)) {
            return 1;
        } else if (TUESDAY.equals(enWeek)) {
            return 2;
        } else if (WEDNESDAY.equals(enWeek)) {
            return 3;
        } else if (THURSDAY.equals(enWeek)) {
            return 4;
        } else if (FRIDAY.equals(enWeek)) {
            return 5;
        } else if (SATURDAY.equals(enWeek)) {
            return 6;
        } else if (SUNDAY.equals(enWeek)) {
            return 7;
        } else {
            return -1;
        }
    }

    /**
     * 获取当前日期之后（之后）的节点事件<br>
     * <p>
     * 比如当前时间为：2019-03-30 10:20:30
     * <p>
     * node="hour",num=5L:2019-03-30 15:20:30
     * node="day",num=1L:2019-03-31 10:20:30
     * node="year",num=1L:2020-03-30 10:20:30
     *
     * @param node 节点元素（“year”,"month","week","day","huor","minute","second"）
     * @param num  第几天（+：之后，-：之前）
     * @return 之后或之后的日期
     */
    public static String getAfterOrPreNowTime(String node, Long num) {
        LocalDateTime now = LocalDateTime.now();
        if (HOUR.equals(node)) {
            return now.plusHours(num).format(yyyyMMddHHmmss_EN);
        } else if (DAY.equals(node)) {
            return now.plusDays(num).format(yyyyMMddHHmmss_EN);
        } else if (WEEK.equals(node)) {
            return now.plusWeeks(num).format(yyyyMMddHHmmss_EN);
        } else if (MONTH.equals(node)) {
            return now.plusMonths(num).format(yyyyMMddHHmmss_EN);
        } else if (YEAR.equals(node)) {
            return now.plusYears(num).format(yyyyMMddHHmmss_EN);
        } else if (MINUTE.equals(node)) {
            return now.plusMinutes(num).format(yyyyMMddHHmmss_EN);
        } else if (SECOND.equals(node)) {
            return now.plusSeconds(num).format(yyyyMMddHHmmss_EN);
        } else {
            return "Node is Error!";
        }
    }

    /**
     * 把日期对象加减年、月、日后得到新的日期对象
     *
     * @param datepart yy-年、MM-月、dd-日、HH-时、mm-分、ss-秒
     * @param number   加减因子
     * @param date     需要加减年、月、日的日期对象
     * @return Date 新的日期对象
     */
    public static Date addDate(String datepart, int number, Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (datepart.equals("yy")) {
            cal.add(Calendar.YEAR, number);
        } else if (datepart.equals("MM")) {
            cal.add(Calendar.MONTH, number);
        } else if (datepart.equals("dd")) {
            cal.add(Calendar.DATE, number);
        } else if (datepart.equals("HH")) {
            cal.add(Calendar.HOUR, number);
        } else if (datepart.equals("mm")) {
            cal.add(Calendar.MINUTE, number);
        } else if (datepart.equals("ss")) {
            cal.add(Calendar.SECOND, number);
        } else {
            throw new IllegalArgumentException("UDateUtil.addDate()方法非法参数值："
                    + datepart);
        }
        return cal.getTime();
    }

    /**
     * 比较时间大小
     * 默认和当前时间比较
     * 按照时间格式字符串:yyyy-MM-dd HH:mm:ss
     *
     * @param time1 时间1
     * @return 如果time1小于当前时间返回true, 否则返回false
     */
    public static boolean compareNow(String time1) {
        return compareTime(time1, currentStr(), DATETIME_FORMAT);
    }

    /**
     * 比较时间大小
     * 按照时间格式字符串:yyyy-MM-dd HH:mm:ss
     *
     * @param time1 时间1
     * @param time2 时间2
     * @return 如果time1小于time2返回true, 否则返回false
     */
    public static boolean compareTime(String time1, String time2) {
        return compareTime(time1, time2, DATETIME_FORMAT);
    }

    /**
     * 比较时间大小
     *
     * @param time1      时间1
     * @param time2      时间2
     * @param dateFormat 时间格式字符串
     * @return 如果time1小于time2返回true, 否则返回false
     */
    public static boolean compareTime(String time1, String time2, String dateFormat) {
        SimpleDateFormat t1 = new SimpleDateFormat(dateFormat);
        SimpleDateFormat t2 = new SimpleDateFormat(dateFormat);
        try {
            Date d1 = t1.parse(time1);
            Date d2 = t2.parse(time2);
            return d1.before(d2);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 将指定格式的字符串转换成日期类型
     *
     * @param date   待转换的日期字符串
     * @param format 日期格式字符串
     * @return 返回Date日期对象
     */
    public static Date convert(String date, String format) {

        if (date == null || date.equals("")) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (Exception e) {
            throw new RuntimeException("UDateUtil.convert():"
                    + e.getMessage());
        }
    }

    /**
     * 将日期类型转换成指定格式的日期字符串
     *
     * @param date   Date日期对象
     * @param format 日期格式字符串
     * @return 返回按照指定日期格式转换成的日期字符串
     */
    public static String convert(Date date, String format) {

        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 将时间戳转换成指定格式的日期字符串
     *
     * @param date   时间戳
     * @param format 日期格式字符串
     * @return 按照指定日期格式转换成的日期字符串
     */
    public static String convert(Long date, String format) {
        if (date == null) {
            return "";
        }
        return convert(convert(date), format);
    }

    /**
     * 将日期时间字符串转成日期对象
     * 备注:时间格式(yyyy-MM-dd HH:mm:ss)
     *
     * @param date 字符串日期对象
     * @return 返回Date
     */
    public static Date convert(String date) {
        return convert(date, DATETIME_FORMAT);
    }

    /**
     * 将字符串日期对象转成日期对象-年月
     * 备注:日期格式(yyyy-MM)
     *
     * @param date 字符串日期对象
     * @return 返回Date
     */
    public static Date convertNianYue(String date) {
        return convert(date, DATETIME_FORMAT_NIANYUE);
    }

    /**
     * 将字符串日期对象转成日期对象-年月日
     * 备注:日期格式(yyyy-MM-dd)
     *
     * @param date 字符串日期对象
     * @return 返回Date
     */
    public static Date convertNianYueRi(String date) {
        return convert(date, DATETIME_FORMAT_NIANYUERI);
    }

    /**
     * 时间戳转日期
     *
     * @param time 传入的时间戳
     * @return 返回Date
     */
    public static Date convert(long time) {
        return new Date(time);
    }

    /**
     * 时间戳转日期
     *
     * @param time 传入的时间戳
     * @return 返回Date
     */
    public static Date convert(Long time) {
        return new Date(time.longValue());
    }

    /**
     * 将对象转成日期对象
     *
     * @param o 传入的对象Object
     * @return 返回Date
     */
    public static Date toDate(Object o) {
        if (null == o) {
            return null;
        }
        if (o instanceof Date) {
            return (Date) o;
        }
        if (o instanceof String) {
            return convert((String) o);
        }
        if (o instanceof Long) {
            Long t = (Long) o;
            return new Date(t.longValue());
        }
        if (o instanceof Integer) {
            Long t = ((Integer) o).longValue();
            return new Date(t.longValue());
        }
        throw new RuntimeException("invalid time object:" + o);
    }

    /**
     * 把输入的时间转换成时间段显示
     *
     * @param period 单位为秒
     * @return 返回时间段 默认格式为"d天h小时m分钟"
     */
    public static String format(long period) {

        long dayUnit = 24 * 60 * 60L;
        long hourUnit = 60 * 60L;
        long minUnit = 60L;
        String result = MessageFormat.format(DEFAULT_PERIOD_FORMAT,
                (period / dayUnit), (period % dayUnit / hourUnit), (period
                        % hourUnit / minUnit));
        return result;
    }

    /**
     * 计算两个日期之间的相隔的年、月、日。注意：只有计算相隔天数是准确的，相隔年和月都是 近似值，按一年365天，一月30天计算，忽略闰年和闰月的差别。
     *
     * @param datepart  两位的格式字符串，yy表示年，MM表示月，dd表示日
     * @param startdate 开始日期
     * @param enddate   结束日期
     * @return double 返回double格式的相隔数
     * 备注:
     * 如果 enddate 大于 startdate，返回一个大于0的实数，否则返回一个小于等于0的实数
     */
    public static double dateDiff(String datepart, Date startdate, Date enddate) {

        if (datepart == null || datepart.equals("")) {
            throw new IllegalArgumentException("UDateUtil.dateDiff()方法非法参数值："
                    + datepart);
        }

        double days = (double) (enddate.getTime() - startdate.getTime())
                / (60 * 60 * 24 * 1000);

        if (datepart.equals("yy")) {
            days = days / 365;
        } else if (datepart.equals("MM")) {
            days = days / 30;
        } else if (datepart.equals("dd")) {
            return days;
        } else {
            throw new IllegalArgumentException("UDateUtil.dateDiff()方法非法参数值："
                    + datepart);
        }
        return days;
    }

    /**
     * 返回当前时间，默认格式为 yyyy-MM-dd HH:mm:ss
     *
     * @return 当前时间 日期时间字符串格式:yyyy-MM-dd HH:mm:ss
     */
    public static String currentStr() {
        return currentStr(DATETIME_FORMAT);
    }

    /**
     * <p>
     * 取得当前日期，并将其转换成格式为"dateFormat"的字符串 例子：假如当前日期是 2003-09-24 9:19:10，则：
     * <p>
     * 获取示例:
     * getCurrDateStr("yyyyMMdd")="20030924"
     * getCurrDateStr("yyyy-MM-dd")="2003-09-24"
     * getCurrDateStr("yyyy-MM-dd HH:mm:ss")="2003-09-24 09:19:10"
     *
     * @param dateFormat - String 日期格式字符串
     * @return String
     */
    public static String currentStr(String dateFormat) {
        return convert(new Date(), dateFormat);
    }

    /**
     * 得到系统当前时间的Timestamp对象
     *
     * @return 系统当前时间的Timestamp对象
     */
    public static Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 获取时间戳字符串的毫秒表示，字符串日期时间格式 “yyyy-MM-dd HH:mm:ss”
     *
     * @param dateStr 时间戳字符串的毫秒表示，1363140153000 1354723201000
     * @return 返回时间戳
     */
    public static long getTimeMillis(String dateStr) {
        return convert(dateStr, DATETIME_FORMAT).getTime();
    }

    /**
     * 获取指定日期所在月份的第一天
     *
     * @param date 指定日期
     * @return 第一天
     */
    public static Date getFirstMonthDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
        return calendar.getTime();
    }

    /**
     * 获取指定日期下个月第一天的开始时间
     *
     * @param date 指定日期
     * @return 下个月第一天的开始时间
     */
    public static Date getFirstDayNextMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取当前月第一天的开始时间
     *
     * @param date 指定日期
     * @return 下个月第一天的开始时间
     */
    public static Date getFirstDayCurMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取指定日期所在月份的最后一天
     *
     * @param date 指定日期
     * @return 最后一天
     */
    public static Date getLastMonthDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        return calendar.getTime();
    }

    /**
     * 获取当前时间的最小时间
     *
     * @param time 时间戳
     * @return Date
     */
    public static Date dayStartTimeAt(long time) {
        return dayStartTimeAt(convert(time));
    }

    /**
     * 获取当前时间的最小时间
     *
     * @param date 传入的date
     * @return 返回Date
     */
    public static Date dayStartTimeAt(Date date) {
        if (date == null) {
            return null;
        }
        // 首先Date转成LocalDateTime
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime minTime = localDateTime.with(LocalTime.MIN);
        // 再将LocalDateTime转回Date
        Date minDate = localDateTimeToDate(minTime);
        return minDate;
    }

    /**
     * 获取当前时间的最大时间
     *
     * @param time 时间戳
     * @return 返回Date
     */
    public static Date dayEndTimeAt(long time) {
        return dayEndTimeAt(convert(time));
    }

    /**
     * 获取当前时间的最大时间
     *
     * @param date 传入的date
     * @return 返回Date
     */
    public static Date dayEndTimeAt(Date date) {
        // 首先Date转成LocalDateTime
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime maxTime = localDateTime.with(LocalTime.MAX);
        // 再将LocalDateTime转回Date
        Date minDate = localDateTimeToDate(maxTime);
        return minDate;
    }

    /**
     * 10位int型的时间戳转换为字符串时间(yyyy-MM-dd HH:mm:ss)
     *
     * @param time 传入的时间戳
     * @return 返回转换好的字符串时间
     */
    public static String timestampToString(Integer time) {
        //int转long时，先进行转型再进行计算，否则会是计算结束后在转型
        long temp = (long) time * 1000;
        Timestamp ts = new Timestamp(temp);
        String tsStr = "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            //方法一
            tsStr = dateFormat.format(ts);
            /*System.out.println(tsStr);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tsStr;
    }

    /**
     * Date转换成LocalDate
     *
     * @param date 传入的date
     * @return LocalDate
     */
    public static LocalDate dateToLocalDate(Date date) {
        if (null == date) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * LocalDate转换成Date
     *
     * @param localDate 传入的localDate
     * @return Date
     */
    public static Date localDateToDate(LocalDate localDate) {
        if (null == localDate) {
            return null;
        }
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * Date转换成LocalDateTime
     * <p>
     * System.out.println(localDateTime.toString());
     * System.out.println(localDateTime.toLocalDate() + " " +localDateTime.toLocalTime());
     * DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");//This class is immutable and thread-safe.@since 1.8
     * System.out.println(dateTimeFormatter.format(localDateTime));
     *
     * @param date 传入的date
     * @return LocalDateTime
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant(); // An instantaneous point on the time-line.(时间线上的一个瞬时点。)
        ZoneId zoneId = ZoneId.systemDefault(); // A time-zone ID, such as {@code Europe/Paris}.(时区)
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        return localDateTime;
    }

    /**
     * LocalDateTime转换成Date
     *
     * @param localDateTime 传入的localDateTime
     * @return 转换成的Date
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);//Combines this date-time with a time-zone to create a  ZonedDateTime.
        Date date = Date.from(zdt.toInstant());
        return date;
    }

    /**
     * 获取与当前日期相距num个之后（之前）的日期<br>
     *
     * @param dtf  格式化当前时间格式（dtf = yyyyMMddHHmmss_EN）
     * @param node 节点元素（“year”,"month","week","day","huor","minute","second"）
     * @param num  （+：之后，-：之前）
     * @return 之后之前的日期
     */
    public static String getAfterOrPreNowTimePlus(DateTimeFormatter dtf, String node, Long num) {
        LocalDateTime now = LocalDateTime.now();
        if (HOUR.equals(node)) {
            return now.plusHours(num).format(dtf);
        } else if (DAY.equals(node)) {
            return now.plusDays(num).format(dtf);
        } else if (WEEK.equals(node)) {
            return now.plusWeeks(num).format(dtf);
        } else if (MONTH.equals(node)) {
            return now.plusMonths(num).format(dtf);
        } else if (YEAR.equals(node)) {
            return now.plusYears(num).format(dtf);
        } else if (MINUTE.equals(node)) {
            return now.plusMinutes(num).format(dtf);
        } else if (SECOND.equals(node)) {
            return now.plusSeconds(num).format(dtf);
        } else {
            return "Node is Error!";
        }
    }

    /**
     * 当前时间的hour，minute，second之后（之前）的时刻
     *
     * @param node 时间节点元素（hour，minute，second）
     * @param num  之后（之后）多久时，分，秒（+：之后，-：之前）
     * @return HH:mm:ss 字符串
     */
    public static String getAfterOrPreNowTimeSimp(String node, Long num) {
        LocalTime now = LocalTime.now();
        if (HOUR.equals(node)) {
            return now.plusHours(num).format(HHmmss_EN);
        } else if (MINUTE.equals(node)) {
            return now.plusMinutes(num).format(HHmmss_EN);
        } else if (SECOND.equals(node)) {
            return now.plusSeconds(num).format(HHmmss_EN);
        } else {
            return "Node is Error!";
        }
    }

    /**
     * 检查重复事件，比如生日。
     *
     * @param month      月份
     * @param dayOfMonth 当前月的第几天
     * @return 当前日期是否是你的生日 是返回true 不是返回false
     */
    public static boolean isBirthday(int month, int dayOfMonth) {
        MonthDay birthDay = MonthDay.of(month, dayOfMonth);
        MonthDay curMonthDay = MonthDay.from(LocalDate.now());// MonthDay只存储了月、日。
        if (birthDay.equals(curMonthDay)) {
            return true;
        }
        return false;
    }

    /**
     * 获取当前日期第index日之后(之前)的日期（yyyy-MM-dd）
     *
     * @param index 第index天
     * @return 日期字符串：yyyy-MM-dd
     */
    public static String getAfterOrPreDayDate(int index) {
        return LocalDate.now().plus(index, ChronoUnit.DAYS).format(yyyyMMdd_EN);
    }

    /**
     * 获取当前日期第index周之前（之后）的日期（yyyy-MM-dd）
     *
     * @param index 第index周（+：之后，-：之前）
     * @return 日期字符串：yyyy-MM-dd
     */
    public static String getAfterOrPreWeekDate(int index) {
        return LocalDate.now().plus(index, ChronoUnit.WEEKS).format(yyyyMMdd_EN);
    }

    /**
     * 获取当前日期第index月之前（之后）的日期（yyyy-MM-dd）
     *
     * @param index 第index月（+：之后，-：之前）
     * @return 日期字符串：yyyy-MM-dd
     */
    public static String getAfterOrPreMonthDate(int index) {
        return LocalDate.now().plus(index, ChronoUnit.MONTHS).format(yyyyMMdd_EN);
    }

    /**
     * 获取当前日期第index年之前（之后）的日期（yyyy-MM-dd）
     *
     * @param index 第index年（+：之后，-：之前）
     * @return 日期字符串：yyyy-MM-dd
     */
    public static String getAfterOrPreYearDate(int index) {
        return LocalDate.now().plus(index, ChronoUnit.YEARS).format(yyyyMMdd_EN);
    }

    /**
     * 获取指定日期之前之后的第index的日，周，月，年的日期
     *
     * @param date  指定日期格式：yyyy-MM-dd
     * @param node  时间节点元素（日周月年）
     * @param index 之前之后第index个日期
     * @return yyyy-MM-dd 日期字符串
     */
    public static String getAfterOrPreDate(String date, String node, int index) {
        date = date.trim();
        if (DAY.equals(node)) {
            return LocalDate.parse(date).plus(index, ChronoUnit.DAYS).format(yyyyMMdd_EN);
        } else if (WEEK.equals(node)) {
            return LocalDate.parse(date).plus(index, ChronoUnit.WEEKS).format(yyyyMMdd_EN);
        } else if (MONTH.equals(node)) {
            return LocalDate.parse(date).plus(index, ChronoUnit.MONTHS).format(yyyyMMdd_EN);
        } else if (YEAR.equals(node)) {
            return LocalDate.parse(date).plus(index, ChronoUnit.YEARS).format(yyyyMMdd_EN);
        } else {
            return "Wrong date format!";
        }
    }

    /**
     * 检测：输入年份是否是闰年？
     *
     * @param date 日期格式：yyyy-MM-dd
     * @return true：闰年，false：平年
     */
    public static boolean isLeapYear(String date) {
        return LocalDate.parse(date.trim()).isLeapYear();
    }

    /**
     * 计算两个日期字符串之间相差多少个周期（天，月，年）
     *
     * @param date1 yyyy-MM-dd
     * @param date2 yyyy-MM-dd
     * @param node  三者之一:(day，month,year)
     * @return 相差多少周期
     */
    public static int peridCount(String date1, String date2, String node) {
        date1 = date1.trim();
        date2 = date2.trim();
        if (DAY.equals(node)) {
            return Period.between(LocalDate.parse(date1), LocalDate.parse(date2)).getDays();
        } else if (MONTH.equals(node)) {
            return Period.between(LocalDate.parse(date1), LocalDate.parse(date2)).getMonths();
        } else if (YEAR.equals(node)) {
            return Period.between(LocalDate.parse(date1), LocalDate.parse(date2)).getYears();
        } else {
            return 0;
        }
    }

    /**
     * 切割日期。按照周期切割成小段日期段。例如:
     *
     * @param startDate 开始日期（yyyy-MM-dd）
     * @param endDate   结束日期（yyyy-MM-dd）
     * @param period    周期（天，周，月，年）
     * @return 切割之后的日期集合
     */
    public static List<String> getPieDateRange(String startDate, String endDate, String period) {
        List<String> result = new ArrayList<>();
        LocalDate end = LocalDate.parse(endDate, yyyyMMdd_EN);
        LocalDate start = LocalDate.parse(startDate, yyyyMMdd_EN);
        LocalDate tmp = start;
        switch (period) {
            case DAY:
                while (start.isBefore(end) || start.isEqual(end)) {
                    result.add(start.toString());
                    start = start.plusDays(1);
                }
                break;
            case WEEK:
                while (tmp.isBefore(end) || tmp.isEqual(end)) {
                    if (tmp.plusDays(6).isAfter(end)) {
                        result.add(tmp.toString() + "," + end);
                    } else {
                        result.add(tmp.toString() + "," + tmp.plusDays(6));
                    }
                    tmp = tmp.plusDays(7);
                }
                break;
            case MONTH:
                while (tmp.isBefore(end) || tmp.isEqual(end)) {
                    LocalDate lastDayOfMonth = tmp.with(TemporalAdjusters.lastDayOfMonth());
                    if (lastDayOfMonth.isAfter(end)) {
                        result.add(tmp.toString() + "," + end);
                    } else {
                        result.add(tmp.toString() + "," + lastDayOfMonth);
                    }
                    tmp = lastDayOfMonth.plusDays(1);
                }
                break;
            case YEAR:
                while (tmp.isBefore(end) || tmp.isEqual(end)) {
                    LocalDate lastDayOfYear = tmp.with(TemporalAdjusters.lastDayOfYear());
                    if (lastDayOfYear.isAfter(end)) {
                        result.add(tmp.toString() + "," + end);
                    } else {
                        result.add(tmp.toString() + "," + lastDayOfYear);
                    }
                    tmp = lastDayOfYear.plusDays(1);
                }
                break;
            default:
                break;
        }
        return result;
    }

    /**
     * 获取指定日期月的第一天或最后一天（yyyy-MM-dd）
     *
     * @param curDate     日期格式（yyyy-MM-dd）
     * @param firstOrLast true：第一天，false：最后一天
     * @return 返回日期月的第一天或最后一天（yyyy-MM-dd）
     */
    public static String getLastDayOfMonth(String curDate, boolean firstOrLast) {
        if (firstOrLast) {
            return LocalDate.parse(curDate, yyyyMMdd_EN).with(TemporalAdjusters.firstDayOfMonth()).toString();
        } else {
            return LocalDate.parse(curDate, yyyyMMdd_EN).with(TemporalAdjusters.lastDayOfMonth()).toString();
        }
    }

    /**
     * 获取指定日期年的第一天或最后一天（yyyy-MM-dd）
     *
     * @param curDate     指定日期（格式：yyyy-MM-dd）
     * @param firstOrLast true:第一天，false:最后一天
     * @return 返回日期年的第一天或最后一天（yyyy-MM-dd）
     */
    public static String getLastDayOfYear(String curDate, boolean firstOrLast) {
        if (firstOrLast) {
            return LocalDate.parse(curDate, yyyyMMdd_EN).with(TemporalAdjusters.firstDayOfYear()).toString();
        } else {
            return LocalDate.parse(curDate, yyyyMMdd_EN).with(TemporalAdjusters.lastDayOfYear()).toString();
        }
    }

    /**
     * 获取下一个星期的日期
     *
     * @param curDay          yyyy-MM-dd
     * @param dayOfWeek       monday:1~sunday:7
     * @param isContainCurDay 是否包含当天，true：是，false：不包含
     * @return 日期（yyyy-MM-dd）
     */
    public static String getNextWeekDate(String curDay, int dayOfWeek, boolean isContainCurDay) {
        dayOfWeek = dayOfWeek < 1 || dayOfWeek > 7 ? 1 : dayOfWeek;
        if (isContainCurDay) {
            return LocalDate.parse(curDay).with(TemporalAdjusters.nextOrSame(DayOfWeek.of(dayOfWeek))).toString();
        } else {
            return LocalDate.parse(curDay).with(TemporalAdjusters.next(DayOfWeek.of(dayOfWeek))).toString();
        }
    }

    /**
     * 获取上一个星期的日期
     *
     * @param curDay    指定日期（yyyy-MM-dd）
     * @param dayOfWeek 数字范围（monday:1~sunday:7）
     * @param isCurDay  是否包含当天，true：是，false：不包含
     * @return 日期（yyyy-MM-dd）
     */
    public static String getPreWeekDate(String curDay, int dayOfWeek, boolean isCurDay) {
        dayOfWeek = dayOfWeek < 1 || dayOfWeek > 7 ? 1 : dayOfWeek;
        if (isCurDay) {
            return LocalDate.parse(curDay).with(TemporalAdjusters.previousOrSame(DayOfWeek.of(dayOfWeek))).toString();
        } else {
            return LocalDate.parse(curDay).with(TemporalAdjusters.previous(DayOfWeek.of(dayOfWeek))).toString();
        }
    }

    /**
     * 获取指定日期当月的最后或第一个星期日期
     *
     * @param curDay      指定日期（yyyy-MM-dd）
     * @param dayOfWeek   周几（1~7）
     * @param lastOrFirst true：最后一个，false本月第一个
     * @return 日期（yyyy-MM-dd）
     */
    public static String getFirstOrLastWeekDate(String curDay, int dayOfWeek, boolean lastOrFirst) {
        dayOfWeek = dayOfWeek < 1 || dayOfWeek > 7 ? 1 : dayOfWeek;
        if (lastOrFirst) {
            return LocalDate.parse(curDay).with(TemporalAdjusters.lastInMonth(DayOfWeek.of(dayOfWeek))).toString();
        } else {
            return LocalDate.parse(curDay).with(TemporalAdjusters.firstInMonth(DayOfWeek.of(dayOfWeek))).toString();
        }
    }

    /**
     * @return 默认返回时间戳 到毫秒ms
     */
    public static Long getTimeStamp() {
        return new Date().getTime();
    }

    /**
     * @param isSeconds 是否到秒s true到秒s false到毫秒ms
     * @return 返回时间戳 到秒s
     */
    public static Long getTimeStamp(Boolean isSeconds) {
        if (isSeconds) {
            return new Date().getTime() / 1000;
        }
        return new Date().getTime();
    }

    /**
     * @return Long 返回时间戳 到纳秒ns
     */
    public static Long getMicTime() {
        Long cutime = System.currentTimeMillis() * 1000; // 微秒
        Long nanoTime = System.nanoTime(); // 纳秒
        return cutime + (nanoTime - nanoTime / 1000000 * 1000000) / 1000;
    }

    //===============================================================================================================begin ☆

    /**
     * 当前时间秒数
     *
     * @return 当前时间秒数
     */
    public static long currentSeconds() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 转换为Calendar对象
     *
     * @param date 日期对象
     * @return Calendar对象
     */
    public static Calendar calendar(Date date) {
        return calendar(date.getTime());
    }

    /**
     * 转换为Calendar对象
     *
     * @param millis 时间戳
     * @return Calendar对象
     */
    public static Calendar calendar(long millis) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(millis);
        return cal;
    }

    /**
     * 将日期字符串转换为{@link DateTime}对象，格式：<br>
     * <ol>
     * <li>yyyy-MM-dd HH:mm:ss</li>
     * <li>yyyy/MM/dd HH:mm:ss</li>
     * <li>yyyy.MM.dd HH:mm:ss</li>
     * <li>yyyy年MM月dd日 HH时mm分ss秒</li>
     * <li>yyyy-MM-dd</li>
     * <li>yyyy/MM/dd</li>
     * <li>yyyy.MM.dd</li>
     * <li>HH:mm:ss</li>
     * <li>HH时mm分ss秒</li>
     * <li>yyyy-MM-dd HH:mm</li>
     * <li>yyyy-MM-dd HH:mm:ss.SSS</li>
     * <li>yyyyMMddHHmmss</li>
     * <li>yyyyMMddHHmmssSSS</li>
     * <li>yyyyMMdd</li>
     * <li>EEE, dd MMM yyyy HH:mm:ss z</li>
     * <li>EEE MMM dd HH:mm:ss zzz yyyy</li>
     * </ol>
     *
     * @param dateStr 日期字符串
     * @return 日期
     */
    public static DateTime parse(String dateStr) {
        if (null == dateStr) {
            return null;
        }
        // 去掉两边空格并去掉中文日期中的“日”，以规范长度
        dateStr = dateStr.trim().replace("日", "");
        int length = dateStr.length();

        if (Validator.isNumber(dateStr)) {
            // 纯数字形式
            if (length == DatePattern.PURE_DATETIME_PATTERN.length()) {
                return parse(dateStr, DatePattern.PURE_DATETIME_FORMAT);
            } else if (length == DatePattern.PURE_DATETIME_MS_PATTERN.length()) {
                return parse(dateStr, DatePattern.PURE_DATETIME_MS_FORMAT);
            } else if (length == DatePattern.PURE_DATE_PATTERN.length()) {
                return parse(dateStr, DatePattern.PURE_DATE_FORMAT);
            } else if (length == DatePattern.PURE_TIME_PATTERN.length()) {
                return parse(dateStr, DatePattern.PURE_TIME_FORMAT);
            }
        }

        if (length == DatePattern.NORM_DATETIME_PATTERN.length() || length == DatePattern.NORM_DATETIME_PATTERN.length() + 1) {
            return parseDateTime(dateStr);
        } else if (length == DatePattern.NORM_DATE_PATTERN.length()) {
            return parseDate(dateStr);
        } else if (length == DatePattern.NORM_TIME_PATTERN.length() || length == DatePattern.NORM_TIME_PATTERN.length() + 1) {
            return parseTime(dateStr);
        } else if (length == DatePattern.NORM_DATETIME_MINUTE_PATTERN.length() || length == DatePattern.NORM_DATETIME_MINUTE_PATTERN.length() + 1) {
            return parse(normalize(dateStr), DatePattern.NORM_DATETIME_MINUTE_FORMAT);
        } else if (length >= DatePattern.NORM_DATETIME_MS_PATTERN.length() - 2) {
            return parse(normalize(dateStr), DatePattern.NORM_DATETIME_MS_FORMAT);
        }

        // 没有更多匹配的时间格式
        throw new DateException("No format fit for date String [{}] !", dateStr);
    }

    /**
     * @return 今年
     */
    public static int thisYear() {
        return year(date());
    }

    /**
     * 获得年的部分
     *
     * @param date 日期
     * @return 年的部分
     */
    public static int year(Date date) {
        return DateTime.of(date).year();
    }

    /**
     * 转换为{@link DateTime}对象
     *
     * @return 当前时间
     */
    public static DateTime date() {
        return new DateTime();
    }

    /**
     * 格式化日期间隔输出
     *
     * @param betweenMs 日期间隔
     * @param level     级别，按照天、小时、分、秒、毫秒分为5个等级
     * @return XX天XX小时XX分XX秒XX毫秒
     */
    public static String formatBetween(long betweenMs, BetweenFormater.Level level) {
        return new BetweenFormater(betweenMs, level).format();
    }

    /**
     * 将特定格式的日期转换为Date对象
     *
     * @param dateStr 特定格式的日期
     * @param format  格式，例如yyyy-MM-dd
     * @return 日期对象
     */
    public static DateTime parse(String dateStr, String format) {
        return new DateTime(dateStr, format);
    }

    /**
     * 构建DateTime对象
     *
     * @param dateStr Date字符串
     * @param parser  格式化器,{@link FastDateFormat}
     * @return DateTime对象
     */
    public static DateTime parse(String dateStr, DateParser parser) {
        return new DateTime(dateStr, parser);
    }

    /**
     * 格式yyyy-MM-dd HH:mm:ss
     *
     * @param dateString 标准形式的时间字符串
     * @return 日期对象
     */
    public static DateTime parseDateTime(String dateString) {
        dateString = normalize(dateString);
        return parse(dateString, DatePattern.NORM_DATETIME_FORMAT);
    }

    /**
     * 格式yyyy-MM-dd
     *
     * @param dateString 标准形式的日期字符串
     * @return 日期对象
     */
    public static DateTime parseDate(String dateString) {
        dateString = normalize(dateString);
        return parse(dateString, DatePattern.NORM_DATE_FORMAT);
    }

    /**
     * 解析时间，格式HH:mm:ss，默认为1970-01-01
     *
     * @param timeString 标准形式的日期字符串
     * @return 日期对象
     */
    public static DateTime parseTime(String timeString) {
        timeString = normalize(timeString);
        return parse(timeString, DatePattern.NORM_TIME_FORMAT);
    }

    /**
     * 偏移天
     *
     * @param date   日期
     * @param offset 偏移天数，正数向未来偏移，负数向历史偏移
     * @return 偏移后的日期
     */
    public static DateTime offsetDay(Date date, int offset) {
        return offset(date, DateField.DAY_OF_YEAR, offset);
    }

    /**
     * 获取指定日期偏移指定时间后的时间，生成的偏移日期不影响原日期
     *
     * @param date      基准日期
     * @param dateField 偏移的粒度大小（小时、天、月等）{@link DateField}
     * @param offset    偏移量，正数为向后偏移，负数为向前偏移
     * @return 偏移后的日期
     */
    public static DateTime offset(Date date, DateField dateField, int offset) {
        return dateNew(date).offset(dateField, offset);
    }

    /**
     * 根据已有{@link Date} 产生新的{@link DateTime}对象
     *
     * @param date Date对象
     * @return {@link DateTime}对象
     */
    public static DateTime dateNew(Date date) {
        return new DateTime(date);
    }

    // ------------------------------------------------------------------------ Private method start

    /**
     * 获得指定日期年份和季节<br>
     * 格式：[20131]表示2013年第一季度
     *
     * @param cal 日期
     * @deprecated 请使用{@link yearAndQuarter}
     */
    @Deprecated
    private static String yearAndSeason(Calendar cal) {
        return new StringBuilder().append(cal.get(Calendar.YEAR)).append(cal.get(Calendar.MONTH) / 3 + 1).toString();
    }

    /**
     * 获得指定日期年份和季节<br>
     * 格式：[20131]表示2013年第一季度
     *
     * @param cal 日期
     */
    private static String yearAndQuarter(Calendar cal) {
        return new StringBuilder().append(cal.get(Calendar.YEAR)).append(cal.get(Calendar.MONTH) / 3 + 1).toString();
    }

    /**
     * 标准化日期，默认处理以空格区分的日期时间格式，空格前为日期，空格后为时间：<br>
     * 将以下字符替换为"-"
     *
     * <pre>
     * "."
     * "/"
     * "年"
     * "月"
     * </pre>
     * <p>
     * 将以下字符去除
     *
     * <pre>
     * "日"
     * </pre>
     * <p>
     * 将以下字符替换为":"
     *
     * <pre>
     * "时"
     * "分"
     * "秒"
     * </pre>
     * <p>
     * 当末位是":"时去除之（不存在毫秒时）
     *
     * @param dateStr 日期时间字符串
     * @return 格式化后的日期字符串
     */
    private static String normalize(String dateStr) {
        if (UStringUtil.isBlank(dateStr)) {
            return dateStr;
        }

        // 日期时间分开处理
        final List<String> dateAndTime = UStringUtil.splitTrim(dateStr, ' ');
        final int size = dateAndTime.size();
        if (size < 1 || size > 2) {
            // 非可被标准处理的格式
            return dateStr;
        }

        final StringBuilder builder = UStringUtil.builder();

        // 日期部分（"\"、"/"、"."、"年"、"月"都替换为"-"）
        String datePart = dateAndTime.get(0).replaceAll("[\\/.年月]", "-");
        datePart = UStringUtil.removeSuffix(datePart, "日");
        builder.append(datePart);

        // 时间部分
        if (size == 2) {
            builder.append(' ');
            String timePart = dateAndTime.get(1).replaceAll("[时分秒]", ":");
            timePart = UStringUtil.removeSuffix(timePart, ":");
            builder.append(timePart);
        }

        return builder.toString();
    }

    /**
     * 是否闰年
     *
     * @param year 年
     * @return 是否闰年
     */
    public static boolean isLeapYear(int year) {
        return new GregorianCalendar().isLeapYear(year);
    }
    // ------------------------------------------------------------------------ Private method end
    //===============================================================================================================end ☆
}