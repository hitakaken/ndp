package com.novbank.ndp.core.exception;

/**
 * Created by hp on 2015/6/2.
 */
public class NdpRuntimeException extends RuntimeException {
    private static final long serialVersionUID = -6740562079950336154L;

    public NdpRuntimeException() {
    }

    public NdpRuntimeException(String message) {
        super(message);
    }

    public NdpRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NdpRuntimeException(Throwable cause) {
        super(cause);
    }

    public NdpRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
