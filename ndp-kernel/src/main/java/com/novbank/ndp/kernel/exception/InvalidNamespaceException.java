package com.novbank.ndp.kernel.exception;

/**
 * Indicates a namespace used in a CRUD request has not been registered in the repository
 *
 * Created by CaoKe on 2015/5/12.
 */
public class InvalidNamespaceException extends RepositoryRuntimeException  {
    private static final long serialVersionUID = -8633142710318445565L;

    /**
     * Ordinary constructor
     *
     * @param msg the message
     */
    public InvalidNamespaceException(final String msg) {
        super(msg);
    }

    /**
     * Ordinary constructor
     *
     * @param msg the message
     * @param rootCause the root cause
     */
    public InvalidNamespaceException(final String msg, final Throwable rootCause) {
        super(msg, rootCause);
    }
}
