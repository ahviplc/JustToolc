package com.lc.tests;

import com.lc.utils.DateUtils;

public class DateUtilsTest {
    public static void main(String[] args) {
        System.out.println(DateUtils.getTimeStamp()); // 1591760743346
        System.out.println(DateUtils.getTimeStamp(false)); // 1591760743346
        System.out.println(DateUtils.getTimeStamp(true)); // 1591760743
    }
}
