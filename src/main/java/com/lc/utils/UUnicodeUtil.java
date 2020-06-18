package com.lc.utils;

import com.lc.core.StrBuilder;

/**
 * 提供Unicode字符串和普通字符串之间的转换<br>
 * Full Of ❤Love❤
 *
 * @author LC
 * @version 0.2
 * @since 0.1
 */
public class UUnicodeUtil {

    /**
     * Unicode字符串转为普通字符串<br>
     * Unicode字符串的表现方式为：\\uXXXX
     *
     * @param unicode Unicode字符串
     * @return 普通字符串
     */
    public static String toString(String unicode) {
        if (UStringUtil.isBlank(unicode)) {
            return unicode;
        }

        final int len = unicode.length();
        StrBuilder sb = StrBuilder.create(len);
        int i = -1;
        int pos = 0;
        while ((i = UStringUtil.indexOfIgnoreCase(unicode, "\\u", pos)) != -1) {
            sb.append(unicode, pos, i);//写入Unicode符之前的部分
            pos = i;
            if (i + 5 < len) {
                char c = 0;
                try {
                    c = (char) Integer.parseInt(unicode.substring(i + 2, i + 6), 16);
                    sb.append(c);
                    pos = i + 6;//跳过整个Unicode符
                } catch (NumberFormatException e) {
                    //非法Unicode符，跳过
                    sb.append(unicode, pos, i + 2);//写入"\\u"
                    pos = i + 2;
                }
            } else {
                pos = i;//非Unicode符，结束
                break;
            }
        }

        if (pos < len) {
            sb.append(unicode, pos, len);
        }
        return sb.toString();
    }

    /**
     * 字符串编码为Unicode形式
     *
     * @param str 被编码的字符串
     * @return Unicode字符串
     */
    public static String toUnicode(String str) {
        return toUnicode(str, true);
    }

    /**
     * 字符串编码为Unicode形式
     *
     * @param str         被编码的字符串
     * @param isSkipAscii 是否跳过ASCII字符（只跳过可见字符）
     * @return Unicode字符串
     */
    public static String toUnicode(String str, boolean isSkipAscii) {
        if (UStringUtil.isEmpty(str)) {
            return str;
        }

        final int len = str.length();
        final StrBuilder unicode = StrBuilder.create(str.length() * 6);
        char c;
        for (int i = 0; i < len; i++) {
            c = str.charAt(i);
            if (isSkipAscii && UCharUtil.isAsciiPrintable(c)) {
                unicode.append(c);
            } else {
                unicode.append(UHexUtil.toUnicodeHex(c));
            }
        }
        return unicode.toString();
    }
}

