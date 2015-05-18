package com.novbank.ndp.kernel.impl.service.result;

import com.novbank.ndp.kernel.service.result.CacheEntry;
import org.apache.jackrabbit.oak.spi.blob.BlobStore;

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
        final BlobStore store = binaryStore(repository);

        if (binary instanceof ExternalBinaryValue) {
            return new ProjectedCacheEntry(property);
        } else if (binary instanceof InMemoryBinaryValue) {
            return new BinaryCacheEntry(property);
        } else {
            return forProperty(store, property);
        }
    }

    /**
     * Get a store-specific Cache Entry
     * @param store the store
     * @param property the property
     * @return store specific cache entry
     * @throws RepositoryException if repository exception occurred
     */
    public static CacheEntry forProperty(final BlobStore store, final Property property)
            throws RepositoryException {
        final Binary binary = property.getBinary();
        if (store instanceof InfinispanBinaryStore) {
            return new InfinispanCacheStoreEntry((InfinispanBinaryStore)store, property);
        } else if (store instanceof FileSystemBinaryStore) {
            return new FileSystemBinaryStoreEntry((FileSystemBinaryStore)store, property);
        } else if (store instanceof CompositeBinaryStore) {
            final CompositeBinaryStore compositeBinaryStore = (CompositeBinaryStore) store;
            final BlobStore binaryStoreContainingKey
                    = compositeBinaryStore.findBinaryStoreContainingKey(binary.getKey());
            return forProperty(binaryStoreContainingKey, property);
        } else {
            return new LocalBinaryStoreEntry(store, property);
        }
    }

    private static Function<Repository,BlobStore> getBinaryStore ;

    private static BlobStore binaryStore(final Repository repo) {
        final BlobStore store = getBinaryStore.apply(repo);
        assert store != null;
        return store;
    }
}
