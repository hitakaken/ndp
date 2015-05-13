package com.novbank.ndp.kernel.exception;

/**
 * A transaction was not found in the transaction registry
 *
 * Created by CaoKe on 2015/5/13.
 */
public class TransactionMissingException extends RepositoryRuntimeException {

    private static final long serialVersionUID = 2139084821001303830L;

    /**
     *
     * @param s the exception message
     */
    public TransactionMissingException(final String s) {
        super(s);
    }
}
