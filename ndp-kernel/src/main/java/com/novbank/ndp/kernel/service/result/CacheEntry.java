package com.novbank.ndp.kernel.service.result;

import javax.jcr.RepositoryException;
import java.io.InputStream;
import java.util.Collection;

/**
 * A CacheEntry abstraction for the various possible types of entries
 *
 * Created by CaoKe on 2015/5/16.
 */
public interface CacheEntry {
    /**
     * Check the fixity of a {@link CacheEntry}
     * @param algorithm the given algorithm
     * @return a {@link FixityResult} containing the relevant data
     * @throws RepositoryException if repository exception occurred
     */
    Collection<FixityResult> checkFixity(final String algorithm)
            throws RepositoryException;

    /**
     * Get a raw input stream from the underlying store
     * @return the content for this entry
     * @throws RepositoryException if repository exception occurred
     */
    InputStream getInputStream() throws RepositoryException;

    /**
     * Generate a human-readable identifier for the location of this entry
     *
     * @return human-readable identifier for the location of this entry
     * @throws RepositoryException if repository exception occurred
     */
    String getExternalIdentifier() throws RepositoryException;
}
