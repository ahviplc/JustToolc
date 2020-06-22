package com.lc.utils;

import com.lc.core.codec.Base64Decoder;
import com.lc.core.codec.Base64Encoder;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Base64工具类<br>
 * 提供Base64的编码和解码方案<br>
 * base64编码是用64（2的6次方）个ASCII字符来表示256（2的8次方）个ASCII字符，<br>
 * 也就是三位二进制数组经过编码后变为四位的ASCII字符显示，长度比原来增加1/3。
 *
 * <p>
 * Base64解码 Base64编码 UTF8 GB2312 UTF16 GBK 二进制 十六进制 解密 - The X 在线工具
 * https://the-x.cn/base64/
 *
 * @author LC
 * @since 0.2
 */
public class UBase64Util {

    /**
     * base64编码
     * <p>
     * 16进制字符串转换为Base64字符串
     * <p>
     * encode源格式是16进制字符串(源格式HEX字符)，先将16进制字符串转成byte[] ，再将byte[]传入
     * <p>
     * 具体转换流程:16进制字符串 到 byte数组 到 编码为Base64字符串
     *
     * <pre>
     *  例如：aabbccdd 对应 qrvM3Q==  AABBCCDD 对应 qrvM3Q==
     * </pre>
     *
     * @param hexStr 输入源格式: 16进制字符串
     * @return 输出：处理好的Base64字符串 (源格式HEX字符)
     */
    public static String encodeHexStrToByteArrayToBase64Str(String hexStr) {
        byte[] hexStrByte = UHexUtil.decodeHex(hexStr);
        return encode(hexStrByte);
    }

    /**
     * base64解码
     * <p>
     * decode输入base64源格式是字符串，转成byte[] ，再将byte[]转成16进制字符串输出 (解码显示方式为16进制HEX)
     * <p>
     * 具体转换流程: Base64字符串 到 byte数组 到 16进制字符串
     *
     * <pre>
     * 例如：qrvM3Q== 对应 AABBCCDD
     * </pre>
     *
     * @param base64 输入源格式:base64字符串(16进制)
     * @return 输出：处理好的16进制字符串 (解码显示方式为16进制HEX) 字母默认输出小写字母
     */
    public static String decodeBase64StrToByteArrayToHexStr(String base64) {
        byte[] temp_byte = decode(base64);
        return UHexUtil.encodeHexStr(temp_byte);
    }

    // ========================================================================================= begin

    // -------------------------------------------------------------------- encode

    /**
     * 编码为Base64，非URL安全的
     *
     * @param arr     被编码的数组
     * @param lineSep 在76个char之后是CRLF还是EOF
     * @return 编码后的bytes
     */
    public static byte[] encode(byte[] arr, boolean lineSep) {
        return Base64Encoder.encode(arr, lineSep);
    }

    /**
     * 编码为Base64，URL安全的
     *
     * @param arr     被编码的数组
     * @param lineSep 在76个char之后是CRLF还是EOF
     * @return 编码后的bytes
     */
    public static byte[] encodeUrlSafe(byte[] arr, boolean lineSep) {
        return Base64Encoder.encodeUrlSafe(arr, lineSep);
    }

    /**
     * base64编码
     *
     * @param source 被编码的base64字符串
     * @return 被加密后的字符串
     */
    public static String encode(CharSequence source) {
        return Base64Encoder.encode(source);
    }

    /**
     * base64编码，URL安全
     *
     * @param source 被编码的base64字符串
     * @return 被加密后的字符串
     */
    public static String encodeUrlSafe(CharSequence source) {
        return Base64Encoder.encodeUrlSafe(source);
    }

    /**
     * base64编码
     *
     * @param source  被编码的base64字符串
     * @param charset 字符集
     * @return 被加密后的字符串
     */
    public static String encode(CharSequence source, String charset) {
        return encode(source, UCharsetUtil.charset(charset));
    }

    /**
     * base64编码,URL安全
     *
     * @param source  被编码的base64字符串
     * @param charset 字符集
     * @return 被加密后的字符串
     */
    public static String encodeUrlSafe(CharSequence source, String charset) {
        return encodeUrlSafe(source, UCharsetUtil.charset(charset));
    }

    /**
     * base64编码
     *
     * @param source  被编码的base64字符串
     * @param charset 字符集
     * @return 被加密后的字符串
     */
    public static String encode(CharSequence source, Charset charset) {
        return Base64Encoder.encode(source, charset);
    }

    /**
     * base64编码，URL安全的
     *
     * @param source  被编码的base64字符串
     * @param charset 字符集
     * @return 被加密后的字符串
     */
    public static String encodeUrlSafe(CharSequence source, Charset charset) {
        return Base64Encoder.encodeUrlSafe(source, charset);
    }

    /**
     * base64编码
     *
     * @param source 被编码的base64字符串
     * @return 被加密后的字符串
     */
    public static String encode(byte[] source) {
        return Base64Encoder.encode(source);
    }

    /**
     * base64编码,URL安全的
     *
     * @param source 被编码的base64字符串
     * @return 被加密后的字符串
     */
    public static String encodeUrlSafe(byte[] source) {
        return Base64Encoder.encodeUrlSafe(source);
    }

    /**
     * base64编码
     *
     * @param in 被编码base64的流（一般为图片流或者文件流）
     * @return 被加密后的字符串
     */
    public static String encode(InputStream in) {
        return Base64Encoder.encode(UIoUtil.readBytes(in));
    }

    /**
     * base64编码,URL安全的
     *
     * @param in 被编码base64的流（一般为图片流或者文件流）
     * @return 被加密后的字符串
     */
    public static String encodeUrlSafe(InputStream in) {
        return Base64Encoder.encodeUrlSafe(UIoUtil.readBytes(in));
    }

    /**
     * base64编码
     *
     * @param file 被编码base64的文件
     * @return 被加密后的字符串
     */
    public static String encode(File file) {
        return Base64Encoder.encode(UFileUtil.readBytes(file));
    }

    /**
     * base64编码,URL安全的
     *
     * @param file 被编码base64的文件
     * @return 被加密后的字符串
     */
    public static String encodeUrlSafe(File file) {
        return Base64Encoder.encodeUrlSafe(UFileUtil.readBytes(file));
    }

    /**
     * base64编码
     *
     * @param source  被编码的base64字符串
     * @param charset 字符集
     * @return 被加密后的字符串
     * @deprecated 编码参数无意义，作废
     */
    @Deprecated
    public static String encode(byte[] source, String charset) {
        return Base64Encoder.encode(source);
    }

    /**
     * base64编码，URL安全的
     *
     * @param source  被编码的base64字符串
     * @param charset 字符集
     * @return 被加密后的字符串
     * @deprecated 编码参数无意义，作废
     */
    @Deprecated
    public static String encodeUrlSafe(byte[] source, String charset) {
        return Base64Encoder.encodeUrlSafe(source);
    }

    /**
     * base64编码
     *
     * @param source  被编码的base64字符串
     * @param charset 字符集
     * @return 被加密后的字符串
     * @deprecated 编码参数无意义，作废
     */
    @Deprecated
    public static String encode(byte[] source, Charset charset) {
        return Base64Encoder.encode(source);
    }

    /**
     * base64编码，URL安全的
     *
     * @param source  被编码的base64字符串
     * @param charset 字符集
     * @return 被加密后的字符串
     * @deprecated 编码参数无意义，作废
     */
    @Deprecated
    public static String encodeUrlSafe(byte[] source, Charset charset) {
        return Base64Encoder.encodeUrlSafe(source);
    }

    /**
     * 编码为Base64<br>
     * 如果isMultiLine为<code>true</code>，则每76个字符一个换行符，否则在一行显示
     *
     * @param arr         被编码的数组
     * @param isMultiLine 在76个char之后是CRLF还是EOF
     * @param isUrlSafe   是否使用URL安全字符，一般为<code>false</code>
     * @return 编码后的bytes
     */
    public static byte[] encode(byte[] arr, boolean isMultiLine, boolean isUrlSafe) {
        return Base64Encoder.encode(arr, isMultiLine, isUrlSafe);
    }

    // -------------------------------------------------------------------- decode

    /**
     * base64解码
     *
     * @param source 被解码的base64字符串
     * @return 被加密后的字符串
     */
    public static String decodeStrGbk(CharSequence source) {
        return Base64Decoder.decodeStr(source, UCharsetUtil.CHARSET_GBK);
    }

    /**
     * base64解码
     *
     * @param source 被解码的base64字符串
     * @return 被加密后的字符串
     */
    public static String decodeStr(CharSequence source) {
        return Base64Decoder.decodeStr(source);
    }

    /**
     * base64解码
     *
     * @param source  被解码的base64字符串
     * @param charset 字符集
     * @return 被加密后的字符串
     */
    public static String decodeStr(CharSequence source, String charset) {
        return decodeStr(source, UCharsetUtil.charset(charset));
    }

    /**
     * base64解码
     *
     * @param source  被解码的base64字符串
     * @param charset 字符集
     * @return 被加密后的字符串
     */
    public static String decodeStr(CharSequence source, Charset charset) {
        return Base64Decoder.decodeStr(source, charset);
    }

    /**
     * base64解码
     *
     * @param base64   被解码的base64字符串
     * @param destFile 目标文件
     * @return 目标文件
     */
    public static File decodeToFile(CharSequence base64, File destFile) {
        return UFileUtil.writeBytes(Base64Decoder.decode(base64), destFile);
    }

    /**
     * base64解码
     *
     * @param base64     被解码的base64字符串
     * @param out        写出到的流
     * @param isCloseOut 是否关闭输出流
     */
    public static void decodeToStream(CharSequence base64, OutputStream out, boolean isCloseOut) {
        UIoUtil.write(out, isCloseOut, Base64Decoder.decode(base64));
    }

    /**
     * base64解码
     *
     * @param base64 被解码的base64字符串
     * @return 被加密后的字符串
     */
    public static byte[] decode(CharSequence base64) {
        return Base64Decoder.decode(base64);
    }

    /**
     * base64解码
     *
     * @param source  被解码的base64字符串
     * @param charset 字符集
     * @return 被加密后的字符串
     * @deprecated 编码参数无意义，作废
     */
    @Deprecated
    public static byte[] decode(CharSequence source, String charset) {
        return Base64Decoder.decode(source);
    }

    /**
     * base64解码
     *
     * @param source  被解码的base64字符串
     * @param charset 字符集
     * @return 被加密后的字符串
     * @deprecated 编码参数无意义，作废
     */
    @Deprecated
    public static byte[] decode(CharSequence source, Charset charset) {
        return Base64Decoder.decode(source);
    }

    /**
     * 解码Base64
     *
     * @param in 输入
     * @return 解码后的bytes
     */
    public static byte[] decode(byte[] in) {
        return Base64Decoder.decode(in);
    }
    // ========================================================================================= end
}
