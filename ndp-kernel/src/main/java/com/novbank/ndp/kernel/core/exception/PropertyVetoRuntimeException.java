package com.novbank.ndp.kernel.core.exception;

/**
 * Created by ken on 15-5-28.
 */
public class PropertyVetoRuntimeException extends RuntimeException{
    public PropertyVetoRuntimeException() {
    }

    public PropertyVetoRuntimeException(String message) {
        super(message);
    }

    public PropertyVetoRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public PropertyVetoRuntimeException(Throwable cause) {
        super(cause);
    }

    public PropertyVetoRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
