package com.novbank.ndp.kernel.exception;

/**
 * An extension of {@link RepositoryRuntimeException} that may be thrown when attempting a
 * operation (or instantiation) of a {@link com.novbank.ndp.kernel.model.Resource}
 * on a different (and incompatible) type.
 *
 * Created by CaoKe on 2015/5/13.
 */
public class ResourceTypeException extends RepositoryRuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     * @param message the message
     */
    public ResourceTypeException(final String message) {
        super(message);
    }
}
