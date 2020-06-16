package com.lc.tests;

import com.lc.utils.UStringUtil;
import org.junit.Test;

public class UStringUtilTest {
    public static void main(String[] args) {
        System.out.println(UStringUtil.toUpper("lc")); // LC
        System.out.println(UStringUtil.toLower("LC")); // lc
    }

    @Test
    public void UStringUtilTest1() {
        System.out.println(UStringUtil.toUpper("lc")); // LC
    }
}