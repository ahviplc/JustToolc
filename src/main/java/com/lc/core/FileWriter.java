package com.lc.core;

import com.lc.exception.IORuntimeException;
import com.lc.utils.UCharsetUtil;
import com.lc.utils.UFileUtil;
import com.lc.utils.UIoUtil;


import java.io.*;
import java.nio.charset.Charset;

/**
 * 文件写入器
 *
 * @author LC
 */
public class FileWriter extends FileWrapper {

    private static final long serialVersionUID = 1L;

    /**
     * 创建 FileWriter
     *
     * @param file    文件
     * @param charset 编码，使用 {@link UCharsetUtil}
     * @return {@link FileWriter}
     */
    public static FileWriter create(File file, Charset charset) {
        return new FileWriter(file, charset);
    }

    /**
     * 创建 FileWriter, 编码：{@link FileWrapper#DEFAULT_CHARSET}
     *
     * @param file 文件
     * @return {@link FileWriter}
     */
    public static FileWriter create(File file) {
        return new FileWriter(file);
    }

// ------------------------------------------------------- Constructor start

    /**
     * 构造
     *
     * @param file    文件
     * @param charset 编码，使用 {@link UCharsetUtil}
     */
    public FileWriter(File file, Charset charset) {
        super(file, charset);
        checkFile();
    }

    /**
     * 构造
     *
     * @param file    文件
     * @param charset 编码，使用 {@link UCharsetUtil#charset(String)}
     */
    public FileWriter(File file, String charset) {
        this(file, UCharsetUtil.charset(charset));
    }

    /**
     * 构造
     *
     * @param filePath 文件路径，相对路径会被转换为相对于ClassPath的路径
     * @param charset  编码，使用 {@link UCharsetUtil}
     */
    public FileWriter(String filePath, Charset charset) {
        this(UFileUtil.file(filePath), charset);
    }

    /**
     * 构造
     *
     * @param filePath 文件路径，相对路径会被转换为相对于ClassPath的路径
     * @param charset  编码，使用 {@link UCharsetUtil#charset(String)}
     */
    public FileWriter(String filePath, String charset) {
        this(UFileUtil.file(filePath), UCharsetUtil.charset(charset));
    }

    /**
     * 构造<br>
     * 编码使用 {@link FileWrapper#DEFAULT_CHARSET}
     *
     * @param file 文件
     */
    public FileWriter(File file) {
        this(file, DEFAULT_CHARSET);
    }

    /**
     * 构造<br>
     * 编码使用 {@link FileWrapper#DEFAULT_CHARSET}
     *
     * @param filePath 文件路径，相对路径会被转换为相对于ClassPath的路径
     */
    public FileWriter(String filePath) {
        this(filePath, DEFAULT_CHARSET);
    }
    // ------------------------------------------------------- Constructor end

    /**
     * 将String写入文件，覆盖模式
     *
     * @param content 写入的内容
     * @return 目标文件
     * @throws IORuntimeException IO异常
     */
    public File write(String content) throws IORuntimeException {
        return write(content, false);
    }

    /**
     * 将String写入文件
     *
     * @param content  写入的内容
     * @param isAppend 是否追加
     * @return 目标文件
     * @throws IORuntimeException IO异常
     */
    public File write(String content, boolean isAppend) throws IORuntimeException {
        BufferedWriter writer = null;
        try {
            writer = getWriter(isAppend);
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            throw new IORuntimeException(e);
        } finally {
            UIoUtil.close(writer);
        }
        return file;
    }

    /**
     * 获得一个带缓存的写入对象
     *
     * @param isAppend 是否追加
     * @return BufferedReader对象
     * @throws IORuntimeException IO异常
     */
    public BufferedWriter getWriter(boolean isAppend) throws IORuntimeException {
        try {
            return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(UFileUtil.touch(file), isAppend), charset));
        } catch (Exception e) {
            throw new IORuntimeException(e);
        }
    }

    /**
     * 检查文件
     *
     * @throws IORuntimeException IO异常
     */
    private void checkFile() throws IORuntimeException {
        Assert.notNull(file, "File to write content is null !");
        if (this.file.exists() && false == file.isFile()) {
            throw new IORuntimeException("File [{}] is not a file !", this.file.getAbsoluteFile());
        }
    }

    /**
     * 写入数据到文件
     *
     * @param data     数据
     * @param off      数据开始位置
     * @param len      数据长度
     * @param isAppend 是否追加模式
     * @return 目标文件
     * @throws IORuntimeException IO异常
     */
    public File write(byte[] data, int off, int len, boolean isAppend) throws IORuntimeException {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(UFileUtil.touch(file), isAppend);
            out.write(data, off, len);
            out.flush();
        } catch (IOException e) {
            throw new IORuntimeException(e);
        } finally {
            UIoUtil.close(out);
        }
        return file;
    }
}
