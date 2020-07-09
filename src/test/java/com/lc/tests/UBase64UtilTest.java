package com.lc.tests;

import com.lc.lang.UConsole;
import com.lc.utils.UBase64Util;
import org.junit.Test;

public class UBase64UtilTest {
    public static void main(String[] args) throws Exception {

        // 编码:
        String aabbccdd_data = UBase64Util.encode("aabbccdd".getBytes("UTF-8"));
        UConsole.log(aabbccdd_data); // YWFiYmNjZGQ=
        // 解码:
        byte[] aabbccdd_byte = UBase64Util.decode(aabbccdd_data);
        String str_bytes_aabbccdd_data = new String(aabbccdd_byte, "UTF-8");
        UConsole.log(str_bytes_aabbccdd_data); // aabbccdd

        // 以下encode源格式是16进制字符串(源格式HEX字符)，先将16进制字符串转成byte[] ，再将byte[]传入
        String toBase64Str = UBase64Util.encodeHexStrToByteArrayToBase64Str("aabbccdd");
        UConsole.log(toBase64Str); // qrvM3Q==
        // 以下decode输入base64源格式是字符串，转成byte[] ，再将byte[]转成16进制字符串输出 (解码显示方式为16进制HEX)
        String toString = UBase64Util.decodeBase64StrToByteArrayToHexStr(toBase64Str);
        UConsole.log(toString); // aabbccdd

        // 6816
        UConsole.log(UBase64Util.encodeHexStrToByteArrayToBase64Str("680033300101000003600000006800000000000000001900036000000068898604401118C21742080867726030867495167816")); // aAAzMAEBAAADYAAAAGgAAAAAAAAAABkAA2AAAABoiYYEQBEYwhdCCAhncmAwhnSVFngW
        UConsole.log(UBase64Util.decodeBase64StrToByteArrayToHexStr("aAAzMAEBAAADYAAAAGgAAAAAAAAAABkAA2AAAABoiYYEQBEYwhdCCAhncmAwhnSVFngW")); // 680033300101000003600000006800000000000000001900036000000068898604401118c21742080867726030867495167816
    }

    @Test
    public void UBase64UtilTest1() throws Exception {
        UConsole.log(UBase64Util.encode("aabbccdd".getBytes("UTF-8"))); // YWFiYmNjZGQ=
        UConsole.log(UBase64Util.decode("YWFiYmNjZGQ=")); // aabbccdd
    }
}
