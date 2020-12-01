package com.sxquan.core.exception;

/**
 * 限流异常
 * @author sxquan
 * @since 2020/7/15 23:19
 */
public class LimitAccessException extends RuntimeException {

    public LimitAccessException(String message) {
        super(message);
    }
}
