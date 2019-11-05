package com.qmtec.common.exception;

/**
 * 索引异常类
 * @date 2017/10/31
 */
public class IndexException extends RuntimeException {

    public IndexException() {
        super();
    }

    public IndexException(String message) {
        super(message);
    }

    public IndexException(String message, Throwable cause) {
        super(message, cause);
    }

    public IndexException(Throwable cause) {
        super(cause);
    }

    protected IndexException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
