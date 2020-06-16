package com.lc.exception;

import com.lc.utils.UExceptionUtil;
import com.lc.utils.UStringUtil;

/**
 * 工具类异常
 *
 * @author LC
 */
public class UtilException extends RuntimeException {
    private static final long serialVersionUID = 8247610319171014183L;

    public UtilException(Throwable e) {
        super(UExceptionUtil.getMessage(e), e);
    }

    public UtilException(String message) {
        super(message);
    }

    public UtilException(String messageTemplate, Object... params) {
        super(UStringUtil.format(messageTemplate, params));
    }

    public UtilException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public UtilException(Throwable throwable, String messageTemplate, Object... params) {
        super(UStringUtil.format(messageTemplate, params), throwable);
    }
}
