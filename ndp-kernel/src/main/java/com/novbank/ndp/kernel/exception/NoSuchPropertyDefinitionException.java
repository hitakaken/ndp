package com.novbank.ndp.kernel.exception;

import javax.jcr.RepositoryException;

/**
 * Represents the case where a property definition has been requested but does
 * not exist. Typically, this happens when a new property is added to a node
 * that does not restrict its property types.
 *
 * Created by CaoKe on 2015/5/13.
 */
public class NoSuchPropertyDefinitionException extends RepositoryException {
    private static final long serialVersionUID = 1L;
}
