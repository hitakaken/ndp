package com.novbank.ndp.kernel.exception;

/**
 * Exception thrown when the calculated digest does not match the stored digest
 *
 * Created by CaoKe on 2015/5/13.
 */
public class InvalidChecksumException extends Exception{
    private static final long serialVersionUID = 1L;

    /**
     * Exception with message
     * @param message the message
     */
    public InvalidChecksumException(final String message) {
        super(message);
    }
}
