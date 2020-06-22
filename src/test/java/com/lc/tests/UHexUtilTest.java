package com.lc.tests;

import com.lc.lang.UConsole;
import com.lc.utils.UCharsetUtil;
import com.lc.utils.UHexUtil;
import org.junit.Assert;
import org.junit.Test;

public class UHexUtilTest {
    public static void main(String[] args) {
        UConsole.log(UHexUtil.encodeHexStr("123")); // 313233
        UConsole.log(UHexUtil.encodeHexStr("abc")); // 616263

        UConsole.log(UHexUtil.decodeHexStr("313233")); // 123
        UConsole.log(UHexUtil.decodeHexStr("616263")); // abc

        byte[] aabbccdds_byte = UHexUtil.decodeHex("aabbccdd");
        UConsole.log(aabbccdds_byte[0]); // -86
        UConsole.log(aabbccdds_byte[1]); // -69
        UConsole.log(aabbccdds_byte[2]); // -52
        UConsole.log(aabbccdds_byte[3]); // -35
    }

    @Test
    public void hexStrTest() {
        String str = "我是一个字符串";

        String hex = UHexUtil.encodeHexStr(str, UCharsetUtil.CHARSET_UTF_8);
        UConsole.log(hex);
        String decodedStr = UHexUtil.decodeHexStr(hex);
        UConsole.log(decodedStr);
        Assert.assertEquals(str, decodedStr);
    }

    @Test
    public void toUnicodeHexTest() {
        String unicodeHex = UHexUtil.toUnicodeHex('\u2001');
        UConsole.log(unicodeHex);
        Assert.assertEquals("\\u2001", unicodeHex);

        unicodeHex = UHexUtil.toUnicodeHex('你');
        UConsole.log(unicodeHex);
        Assert.assertEquals("\\u4f60", unicodeHex);
    }

    @Test
    public void isHexNumberTest() {
        String a = "0x3544534F444";
        boolean isHex = UHexUtil.isHexNumber(a);
        UConsole.log(isHex);
        Assert.assertTrue(isHex);
    }

    @Test
    public void decodeTest() {
        String str = "e8c670380cb220095268f40221fc748fa6ac39d6e930e63c30da68bad97f885d";
        UConsole.log(UHexUtil.decodeHex(str));
        UConsole.log(UHexUtil.decodeHex(str.toUpperCase()));
        Assert.assertArrayEquals(UHexUtil.decodeHex(str),
                UHexUtil.decodeHex(str.toUpperCase()));
    }
}