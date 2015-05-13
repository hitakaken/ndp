package com.novbank.ndp.kernel.exception;

/**
 * Created by CaoKe on 2015/5/13.
 */
public class PathNotFoundRuntimeException extends RepositoryRuntimeException{
    private static final long serialVersionUID = 1L;

    /**
     * Wrap a PathNotFoundException in a runtime exception
     * @param rootCause the root cause
     */
    public PathNotFoundRuntimeException(final Throwable rootCause) {
        super(rootCause);
    }
}
