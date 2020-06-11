package com.lc.tests;

import com.lc.utils.UDateUtils;

public class UDateUtilsTest {
    public static void main(String[] args) {
        System.out.println(UDateUtils.getTimeStamp()); // 1591760743346
        System.out.println(UDateUtils.getTimeStamp(false)); // 1591760743346
        System.out.println(UDateUtils.getTimeStamp(true)); // 1591760743
    }
}