package com.qmtec.common.exception;

/**
 * @version 1.0
 * @Author: liaochou
 * @Date: 18-10-10
 * @Time: 上午10:42
 */
public class HdfsApiException extends Exception {

    public HdfsApiException(String message) {
        super(message);
    }

    public HdfsApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
