package com.lc.tests;

import com.lc.lang.UConsole;
import com.lc.utils.UNumberUtil;

public class UNumberUtilTest {
    public static void main(String[] args) {
        UConsole.log(UNumberUtil.add(1, 1)); // 2.0
        UConsole.log(UNumberUtil.add(2.22f, 1.11f)); // 3.33
        UConsole.log(UNumberUtil.sub(522.3344, 1)); // 521.3344
        UConsole.log(UNumberUtil.mul(100, 5.21)); // 521.0
        UConsole.log(UNumberUtil.div(100, 3)); // 33.3333333333

        UConsole.log(UNumberUtil.compare(1, 1)); // 0
        UConsole.log(UNumberUtil.compare(1, 2)); // -1
        UConsole.log(UNumberUtil.compare(2, 1)); // 1
        UConsole.log(UNumberUtil.compare(5.21, 6.21)); // -1
        UConsole.log(UNumberUtil.compare(1, 2)); // -1

        UConsole.log(UNumberUtil.decimalFormat("0", 1.23567)); // 1
        UConsole.log(UNumberUtil.decimalFormat("0.00", 1.233)); // 1.23
        UConsole.log(UNumberUtil.decimalFormat("00.000", 1.23567)); // 01.236
        UConsole.log(UNumberUtil.decimalFormat("#", 1.23567)); // 1
        UConsole.log(UNumberUtil.decimalFormat("#.##%", 1.23567)); // 123.57%
        UConsole.log(UNumberUtil.decimalFormat("0.00%", 1.23567)); // 123.57%
        UConsole.log(UNumberUtil.decimalFormat("0.000", 1.23567)); // 1.236
        UConsole.log(UNumberUtil.decimalFormat("0.00", 1.239)); // 1.24
        UConsole.log(UNumberUtil.decimalFormat("0.00%", 0.99)); // 99.00%
        UConsole.log(UNumberUtil.decimalFormat(",###", 1234567890)); // 1,234,567,890
        UConsole.log(UNumberUtil.decimalFormat("#.#####E0", 1234567890)); // 1.23457E9

        UConsole.log(UNumberUtil.count(100, 10)); // 总分成10份

        UConsole.log(UNumberUtil.isInteger("1")); // true
        UConsole.log(UNumberUtil.isInteger("1.11")); // true
        UConsole.log(UNumberUtil.isInteger("LC")); // false
        UConsole.log(UNumberUtil.isLong("2")); // true
        UConsole.log(UNumberUtil.isDouble("2")); // false
        UConsole.log(UNumberUtil.isDouble("2.1")); // true
    }
}
