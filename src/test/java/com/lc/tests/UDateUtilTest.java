package com.lc.tests;

import com.lc.lang.UConsole;
import com.lc.utils.UDateUtil;

import java.time.format.DateTimeFormatter;
import java.util.Date;

public class UDateUtilTest {
    public static void main(String[] args) {
        System.out.println(UDateUtil.getNowDate_EN());

        System.out.println(UDateUtil.currentStr());
        System.out.println(UDateUtil.getNowTime_EN());

        System.out.println(UDateUtil.getNowTime_EN_yMdH());
        System.out.println(UDateUtil.getNowTime_CN_yMdH());
        System.out.println(UDateUtil.getNowTime_EN_yMdHm());
        System.out.println(UDateUtil.getNowTime_EN_yyyyMMddHHmm());
        System.out.println(UDateUtil.getNowTime_CN_yMdHm());
        System.out.println(UDateUtil.getNowTime_CN_HHmmss());

        System.out.println(UDateUtil.format(60));
        System.out.println(UDateUtil.format(60 * 60));
        System.out.println(UDateUtil.format(60 * 60 * 24));
        System.out.println(UDateUtil.format(60 * 60 * 24 * 30));

        // 2020-06-12 14:46:07 加1h
        System.out.println(UDateUtil.getAfterOrPreNowTime("hour", +1l)); // 2020-06-12 15:46:07
        System.out.println(UDateUtil.addDate("HH", +1, new Date())); // Fri Jun 12 15:46:07 CST 2020
        System.out.println(UDateUtil.convert(UDateUtil.addDate("HH", +1, new Date()), UDateUtil.DATETIME_FORMAT)); // 2020-06-12 15:46:07

        System.out.println(UDateUtil.compareNow(UDateUtil.getAfterOrPreNowTime("hour", +1l)));

        System.out.println(UDateUtil.convert(UDateUtil.dayStartTimeAt(new Date()), UDateUtil.DATETIME_FORMAT)); // 2020-06-12 00:00:00
        System.out.println(UDateUtil.convert(UDateUtil.dayStartTimeAt(UDateUtil.getTimeStamp()), UDateUtil.DATETIME_FORMAT)); // 2020-06-12 00:00:00
        System.out.println(UDateUtil.convert(UDateUtil.dayEndTimeAt(new Date()), UDateUtil.DATETIME_FORMAT)); // 2020-06-12 23:59:59
        System.out.println(UDateUtil.convert(UDateUtil.dayEndTimeAt(UDateUtil.getTimeStamp()), UDateUtil.DATETIME_FORMAT)); // 2020-06-12 23:59:59

        System.out.println(UDateUtil.getTimeStamp()); // 1591760743346
        System.out.println(UDateUtil.getTimeStamp(false)); // 1591760743346
        System.out.println(UDateUtil.getTimeStamp(true)); // 1591760743

        // 输出纳秒时间戳
        UConsole.log(UDateUtil.getMicTime()); // 1592270862967321

        // getPieDateRange()
        // * @examples:
        // * <li>startDate="2019-02-28",endDate="2019-03-05",period="day"</li>
        // * <li>结果为：[2019-02-28, 2019-03-01, 2019-03-02, 2019-03-03, 2019-03-04, 2019-03-05]</li><br>
        // * <li>startDate="2019-02-28",endDate="2019-03-25",period="week"</li>
        // * <li>结果为：[2019-02-28,2019-03-06, 2019-03-07,2019-03-13, 2019-03-14,2019-03-20,
        // * 2019-03-21,2019-03-25]</li><br>
        // * <li>startDate="2019-02-28",endDate="2019-05-25",period="month"</li>
        // * <li>结果为：[2019-02-28,2019-02-28, 2019-03-01,2019-03-31, 2019-04-01,2019-04-30,
        // * 2019-05-01,2019-05-25]</li><br>
        // * <li>startDate="2019-02-28",endDate="2020-05-25",period="year"</li>
        // * <li>结果为：[2019-02-28,2019-12-31, 2020-01-01,2020-05-25]</li><br>
        UConsole.log(UDateUtil.getPieDateRange("2019-02-28", "2019-03-05", "day")); // [2019-02-28, 2019-03-01, 2019-03-02, 2019-03-03, 2019-03-04, 2019-03-05]

        // getAfterOrPreNowTimePlus()
        // 现在时间: 2020-06-16 10:22:16
        // * <ul>
        // * 比如当前时间为：2019-03-30 10:20:30的格式日期
        // * <li>node="hour",num=5L:2019-03-30 15:20:30</li>
        // * <li>node="day",num=1L:2019-03-31 10:20:30</li>
        // * <li>node="year",num=1L:2020-03-30 10:20:30</li>
        // * </ul>
        DateTimeFormatter yyyyMMddHHmmss_EN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        UConsole.log(UDateUtil.getAfterOrPreNowTimePlus(yyyyMMddHHmmss_EN, "hour", +5L)); // 5小时后 2020-06-16 15:22:16
        UConsole.log(UDateUtil.getAfterOrPreNowTimePlus(yyyyMMddHHmmss_EN, "hour", -5L)); // 5小时前 2020-06-16 05:22:16

        // getTime()
        // *日期格式<br>
        // *<li>yyyy</li>
        // *<li>yyyy-MM-dd</li>
        // *<li>yyyy-MM-dd HH:mm:ss</li>
        // *<li>HH:mm:ss</li>
        UConsole.log(UDateUtil.getTime(UDateUtil.DATETIME_FORMAT)); // 2020-06-16 10:33:04
    }
}