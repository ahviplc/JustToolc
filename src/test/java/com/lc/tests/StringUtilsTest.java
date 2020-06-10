package com.lc.tests;

import com.lc.utils.StringUtils;
import org.junit.Test;

public class StringUtilsTest {
    public static void main(String[] args) {
        System.out.println(StringUtils.toUpper("lc")); // LC
        System.out.println(StringUtils.toLower("LC")); // lc
    }

    @Test
    public void StringUtilsTest1() {
        System.out.println(StringUtils.toUpper("lc")); // LC
    }
}
