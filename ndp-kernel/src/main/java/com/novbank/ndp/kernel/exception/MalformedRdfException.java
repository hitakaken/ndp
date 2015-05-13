package com.novbank.ndp.kernel.exception;

import javax.jcr.RepositoryException;

/**
 * Indicates that RDF was presented for persistence to the repository,
 * but could not be persisted for some reportable reason.
 *
 * Created by CaoKe on 2015/5/13.
 */
public class MalformedRdfException extends RepositoryException {
    private static final long serialVersionUID = 1L;

    /**
     * Ordinary constructor.
     *
     * @param msg the message
     */
    public MalformedRdfException(final String msg) {
        super(msg);
    }


    /**
     * Ordinary constructor.
     *
     * @param msg the message
     * @param rootCause the root cause
     */
    public MalformedRdfException(final String msg, final Throwable rootCause) {
        super(msg, rootCause);
    }
}

