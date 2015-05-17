package com.novbank.ndp.kernel.service.result;

import javax.jcr.RepositoryException;
import java.net.URI;
import java.util.Set;

/**
 * Created by CaoKe on 2015/5/16.
 */
public interface FixityResult {
    /**
     * The possible fixity states (which may be ORed together later)
     */
    enum FixityState {
        SUCCESS, BAD_CHECKSUM, BAD_SIZE
    }

    /**
     * Get the identifier for the entry's store
     * @return String
     * @throws RepositoryException if repository exception occurred
     */
    String getStoreIdentifier() throws RepositoryException;

    /**
     * Check if the fixity result matches the given checksum URI
     *
     * @param checksum the given checksum uri
     * @return fixity result matches the given checksum URI
     */
    boolean matches(URI checksum);

    /**
     * Check if the fixity result matches the given size
     *
     * @param size the given size
     * @return fixity result matches the given size
     */
    boolean matches(long size);

    /**
     * Does the fixity entry match the given size and checksum?
     *
     * @param size bitstream size in bytes
     * @param checksum checksum URI
     * @return true if both conditions matched
     */
    boolean matches(long size, URI checksum);

    /**
     * @param size the size
     * @param checksum the checksum uri
     * @return the status
     */
    Set<FixityState> getStatus(long size, URI checksum);

    /**
     * @return the computed size
     */
    long getComputedSize();

    /**
     * @return the computed checksum
     */
    URI getComputedChecksum();
}
