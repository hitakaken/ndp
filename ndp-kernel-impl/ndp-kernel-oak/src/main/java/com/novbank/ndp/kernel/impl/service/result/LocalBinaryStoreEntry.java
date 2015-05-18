package com.novbank.ndp.kernel.impl.service.result;

import com.novbank.ndp.kernel.service.result.CacheEntry;
import org.apache.jackrabbit.oak.spi.state.NodeStore;

import javax.jcr.Property;
import javax.jcr.RepositoryException;
import java.io.InputStream;

/**
 * Created by ken on 15-5-18.
 */
public class LocalBinaryStoreEntry extends AbstractCacheEntry {
    public LocalBinaryStoreEntry(NodeStore store, Property property) {
    }

    @Override
    public InputStream getInputStream() throws RepositoryException {
        return null;
    }

    @Override
    public String getExternalIdentifier() throws RepositoryException {
        return null;
    }
}
