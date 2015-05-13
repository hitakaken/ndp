package com.novbank.ndp.kernel.exception;

/**
 * Created by CaoKe on 2015/5/13.
 */
public class ServerManagedPropertyException extends RepositoryRuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     *
     * @param msg the message
     */
    public ServerManagedPropertyException(final String msg) {
        super(msg);
    }
}
