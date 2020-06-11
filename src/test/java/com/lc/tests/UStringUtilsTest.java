package com.lc.tests;

import com.lc.utils.UStringUtils;
import org.junit.Test;

public class UStringUtilsTest {
    public static void main(String[] args) {
        System.out.println(UStringUtils.toUpper("lc")); // LC
        System.out.println(UStringUtils.toLower("LC")); // lc
    }

    @Test
    public void UStringUtilsTest1() {
        System.out.println(UStringUtils.toUpper("lc")); // LC
    }
}