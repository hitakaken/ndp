package com.novbank.ndp.kernel.impl.service.result;

import org.apache.jackrabbit.oak.plugins.memory.MemoryNodeStore;

import javax.jcr.Property;
import javax.jcr.RepositoryException;
import java.io.InputStream;

/**
 * Created by ken on 15-5-18.
 */
public class MemoryCacheStoreEntry extends AbstractCacheEntry {

    public MemoryCacheStoreEntry(MemoryNodeStore store, Property property) {

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
