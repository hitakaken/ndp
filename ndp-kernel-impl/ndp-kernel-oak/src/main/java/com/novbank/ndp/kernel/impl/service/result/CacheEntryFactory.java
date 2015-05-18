package com.novbank.ndp.kernel.impl.service.result;

import com.novbank.ndp.kernel.service.result.CacheEntry;
import org.apache.jackrabbit.oak.plugins.document.DocumentNodeStore;
import org.apache.jackrabbit.oak.plugins.memory.MemoryNodeStore;
import org.apache.jackrabbit.oak.plugins.segment.SegmentNodeStore;
import org.apache.jackrabbit.oak.plugins.segment.SegmentNodeStoreService;
import org.apache.jackrabbit.oak.spi.blob.BlobStore;
import org.apache.jackrabbit.oak.spi.state.NodeStore;
import org.joor.Reflect;

import javax.jcr.Binary;
import javax.jcr.Property;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import java.util.function.Function;

/**
 * Created by hp on 2015/5/18.
 */
public class CacheEntryFactory {
    /**
     * No public constructor on utility class
     */
    private CacheEntryFactory() {
    }

    /**
     * Load a store-specific CacheEntry model
     * @param repository the repository
     * @param property the property
     * @return CacheEntry model for the property in the given repository
     * @throws RepositoryException if repository exception occurred
     */
    public static CacheEntry forProperty(final Repository repository, final Property property)
            throws RepositoryException {
        final Binary binary = property.getBinary();
        final NodeStore store = nodeStore(repository);
        return forProperty(store, property);
    }

    /**
     * Get a store-specific Cache Entry
     * @param store the store
     * @param property the property
     * @return store specific cache entry
     * @throws RepositoryException if repository exception occurred
     */
    public static CacheEntry forProperty(final NodeStore store, final Property property)
            throws RepositoryException {
        final Binary binary = property.getBinary();
        if (store instanceof MemoryNodeStore) {
            return new MemoryCacheStoreEntry((MemoryNodeStore)store, property);
        } else if (store instanceof DocumentNodeStore) {
            return new DocumentCacheStoreEntry((DocumentNodeStore)store, property);
        } else if (store instanceof SegmentNodeStore) {
            return new SegmentCacheStoreEntry((SegmentNodeStore)store, property);
        } else {
            return new LocalBinaryStoreEntry(store, property);
        }
    }

    private static Function<Repository,NodeStore> getNodeStore = r ->
            (NodeStore) Reflect.on(r).field("contentRepository").field("nodeStore");

    private static NodeStore nodeStore(final Repository repo) {
        final NodeStore store = getNodeStore.apply(repo);
        assert store != null;
        return store;
    }
}
