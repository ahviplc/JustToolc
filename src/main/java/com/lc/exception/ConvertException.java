package com.lc.exception;

import com.lc.utils.UExceptionUtil;
import com.lc.utils.UStringUtil;

/**
 * 转换异常
 *
 * @author LC
 */
public class ConvertException extends RuntimeException {
    private static final long serialVersionUID = 4730597402855274362L;

    public ConvertException(Throwable e) {
        super(UExceptionUtil.getMessage(e), e);
    }

    public ConvertException(String message) {
        super(message);
    }

    public ConvertException(String messageTemplate, Object... params) {
        super(UStringUtil.format(messageTemplate, params));
    }

    public ConvertException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ConvertException(Throwable throwable, String messageTemplate, Object... params) {
        super(UStringUtil.format(messageTemplate, params), throwable);
    }
}
