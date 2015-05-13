package com.novbank.ndp.kernel.exception;

/**
 * Created by CaoKe on 2015/5/13.
 */
public class RepositoryVersionRuntimeException extends RepositoryRuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Wrap a RepositoryVersionException in a runtime exception
     * @param rootCause the root cause
     */
    public RepositoryVersionRuntimeException(final Throwable rootCause) {
        super(rootCause);
    }

    /**
     * Wrap a RepositoryVersionException in a runtime exception
     * @param msg the message
     */
    public RepositoryVersionRuntimeException(final String msg) {
        super(msg);
    }
}
