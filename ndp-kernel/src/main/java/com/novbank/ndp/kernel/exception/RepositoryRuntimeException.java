package com.novbank.ndp.kernel.exception;

/**
 * Runtime exception
 *
 * Created by CaoKe on 2015/5/12.
 */
public class RepositoryRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Ordinary constructor.
     *
     * @param msg the message
     */
    public RepositoryRuntimeException(final String msg) {
        super(msg);
    }

    /**
     * Ordinary constructor.
     *
     * @param rootCause the root cause
     */
    public RepositoryRuntimeException(final Throwable rootCause) {
        super(rootCause);
    }


    /**
     * Ordinary constructor.
     *
     * @param msg the message
     * @param rootCause the root cause
     */
    public RepositoryRuntimeException(final String msg, final Throwable rootCause) {
        super(msg, rootCause);
    }
}
