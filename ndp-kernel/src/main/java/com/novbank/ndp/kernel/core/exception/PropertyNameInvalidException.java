package com.novbank.ndp.kernel.core.exception;

/**
 * Created by ken on 15-5-27.
 */
public class PropertyNameInvalidException extends Exception{
    private static final long serialVersionUID = 4913425088311559049L;

    public PropertyNameInvalidException() {
    }

    public PropertyNameInvalidException(String message) {
        super(message);
    }

    public PropertyNameInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public PropertyNameInvalidException(Throwable cause) {
        super(cause);
    }

    public PropertyNameInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
