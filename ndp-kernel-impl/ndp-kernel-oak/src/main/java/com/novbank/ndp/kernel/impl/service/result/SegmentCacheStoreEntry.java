package com.novbank.ndp.kernel.impl.service.result;

import com.novbank.ndp.kernel.service.result.CacheEntry;
import org.apache.jackrabbit.oak.plugins.segment.SegmentNodeStore;

import javax.jcr.Property;
import javax.jcr.RepositoryException;
import java.io.InputStream;

/**
 * Created by ken on 15-5-18.
 */
public class SegmentCacheStoreEntry extends AbstractCacheEntry{
    public SegmentCacheStoreEntry(SegmentNodeStore store, Property property) {
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
