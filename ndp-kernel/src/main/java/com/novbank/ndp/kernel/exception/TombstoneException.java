package com.novbank.ndp.kernel.exception;

import com.novbank.ndp.kernel.model.Resource;

/**
 * Created by CaoKe on 2015/5/17.
 */
public class TombstoneException extends RepositoryRuntimeException {
    private static final long serialVersionUID = 1L;

    private final Resource resource;
    private final String uri;

    /**
     * Construct a new tombstone exception for a resource
     * @param resource the ndp resource
     */
    public TombstoneException(final Resource resource) {
        this(resource, null);
    }

    /**
     * Create a new tombstone exception with a URI to the tombstone resource
     * @param resource the ndp resource
     * @param uri the uri to the tombstone resource
     */
    public TombstoneException(final Resource resource, final String uri) {
        super("Discovered tombstone resource at " + resource);
        this.resource = resource;
        this.uri = uri;
    }

    /**
     * Get the tombstone resource
     * @return the tombstone resource
     */
    public Resource getResource() {
        return resource;
    }

    /**
     * Get a URI to the tombstone resource
     * @return the URI to the tombstone resource
     */
    public String getURI() {
        return uri;
    }
}
