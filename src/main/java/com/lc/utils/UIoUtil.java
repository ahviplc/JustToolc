package com.lc.utils;

import com.lc.core.Assert;
import com.lc.core.io.FastByteArrayOutputStream;
import com.lc.core.io.StreamProgress;
import com.lc.exception.IORuntimeException;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * IO工具类<br>
 * IO工具类只是辅助流的读写，并不负责关闭流。原因是流可能被多次读写，读写关闭后容易造成问题。
 *
 * @author LC
 * @since 0.1
 */
public class UIoUtil {

    /**
     * 默认缓存大小 8192
     */
    public static final int DEFAULT_BUFFER_SIZE = 2 << 12;
    /**
     * 默认中等缓存大小 16384
     */
    public static final int DEFAULT_MIDDLE_BUFFER_SIZE = 2 << 13;
    /**
     * 默认大缓存大小 32768
     */
    public static final int DEFAULT_LARGE_BUFFER_SIZE = 2 << 14;

    /**
     * 数据流末尾
     */
    public static final int EOF = -1;

    /**
     * 关闭<br>
     * 关闭失败不会抛出异常
     *
     * @param closeable 被关闭的对象
     */
    public static void close(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (Exception e) {
                // 静默关闭
            }
        }
    }

    /**
     * 从流中读取bytes，读取完毕后关闭流
     *
     * @param in {@link InputStream}
     * @return bytes
     * @throws IORuntimeException IO异常
     */
    public static byte[] readBytes(InputStream in) throws IORuntimeException {
        return readBytes(in, true);
    }

    /**
     * 从流中读取bytes
     *
     * @param in            {@link InputStream}
     * @param isCloseStream 是否关闭输入流
     * @return bytes
     * @throws IORuntimeException IO异常
     */
    public static byte[] readBytes(InputStream in, boolean isCloseStream) throws IORuntimeException {
        final FastByteArrayOutputStream out = new FastByteArrayOutputStream();
        copy(in, out);
        if (isCloseStream) {
            close(in);
        }
        return out.toByteArray();
    }

    /**
     * 拷贝流，使用默认Buffer大小，拷贝后不关闭流
     *
     * @param in  输入流
     * @param out 输出流
     * @return 传输的byte数
     * @throws IORuntimeException IO异常
     */
    public static long copy(InputStream in, OutputStream out) throws IORuntimeException {
        return copy(in, out, DEFAULT_BUFFER_SIZE);
    }

    /**
     * 拷贝流，拷贝后不关闭流
     *
     * @param in         输入流
     * @param out        输出流
     * @param bufferSize 缓存大小
     * @return 传输的byte数
     * @throws IORuntimeException IO异常
     */
    public static long copy(InputStream in, OutputStream out, int bufferSize) throws IORuntimeException {
        return copy(in, out, bufferSize, null);
    }

    /**
     * 拷贝流，拷贝后不关闭流
     *
     * @param in             输入流
     * @param out            输出流
     * @param bufferSize     缓存大小
     * @param streamProgress 进度条
     * @return 传输的byte数
     * @throws IORuntimeException IO异常
     */
    public static long copy(InputStream in, OutputStream out, int bufferSize, StreamProgress streamProgress) throws IORuntimeException {
        Assert.notNull(in, "InputStream is null !");
        Assert.notNull(out, "OutputStream is null !");
        if (bufferSize <= 0) {
            bufferSize = DEFAULT_BUFFER_SIZE;
        }

        byte[] buffer = new byte[bufferSize];
        if (null != streamProgress) {
            streamProgress.start();
        }
        long size = 0;
        try {
            for (int readSize; (readSize = in.read(buffer)) != EOF; ) {
                out.write(buffer, 0, readSize);
                size += readSize;
                out.flush();
                if (null != streamProgress) {
                    streamProgress.progress(size);
                }
            }
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
        if (null != streamProgress) {
            streamProgress.finish();
        }
        return size;
    }

    /**
     * 将byte[]写到流中
     *
     * @param out        输出流
     * @param isCloseOut 写入完毕是否关闭输出流
     * @param content    写入的内容
     * @throws IORuntimeException IO异常
     */
    public static void write(OutputStream out, boolean isCloseOut, byte[] content) throws IORuntimeException {
        try {
            out.write(content);
        } catch (IOException e) {
            throw new IORuntimeException(e);
        } finally {
            if (isCloseOut) {
                close(out);
            }
        }
    }
}
