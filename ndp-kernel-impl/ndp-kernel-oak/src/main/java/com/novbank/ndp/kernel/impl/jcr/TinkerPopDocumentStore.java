package com.novbank.ndp.kernel.impl.jcr;

import org.apache.jackrabbit.oak.cache.CacheStats;
import org.apache.jackrabbit.oak.plugins.document.*;
import org.apache.jackrabbit.oak.plugins.document.cache.CacheInvalidationStats;

import java.util.List;
import java.util.Map;

/**
 * Created by ken on 15-5-18.
 */
public class TinkerPopDocumentStore implements DocumentStore{

    public TinkerPopDocumentStore(DocumentMK.Builder builder) {

    }

    @Override
    public <T extends Document> T find(Collection<T> collection, String key) {
        return null;
    }

    @Override
    public <T extends Document> T find(Collection<T> collection, String key, int maxCacheAge) {
        return null;
    }

    @Override
    public <T extends Document> List<T> query(Collection<T> collection, String fromKey, String toKey, int limit) {
        return null;
    }

    @Override
    public <T extends Document> List<T> query(Collection<T> collection, String fromKey, String toKey, String indexedProperty, long startValue, int limit) {
        return null;
    }

    @Override
    public <T extends Document> void remove(Collection<T> collection, String key) {

    }

    @Override
    public <T extends Document> void remove(Collection<T> collection, List<String> keys) {

    }

    @Override
    public <T extends Document> boolean create(Collection<T> collection, List<UpdateOp> updateOps) {
        return false;
    }

    @Override
    public <T extends Document> void update(Collection<T> collection, List<String> keys, UpdateOp updateOp) {

    }

    @Override
    public <T extends Document> T createOrUpdate(Collection<T> collection, UpdateOp update) {
        return null;
    }

    @Override
    public <T extends Document> T findAndUpdate(Collection<T> collection, UpdateOp update) {
        return null;
    }

    @Override
    public CacheInvalidationStats invalidateCache() {
        return null;
    }

    @Override
    public <T extends Document> void invalidateCache(Collection<T> collection, String key) {

    }

    @Override
    public void dispose() {

    }

    @Override
    public <T extends Document> T getIfCached(Collection<T> collection, String key) {
        return null;
    }

    @Override
    public void setReadWriteMode(String readWriteMode) {

    }

    @Override
    public CacheStats getCacheStats() {
        return null;
    }

    @Override
    public Map<String, String> getMetadata() {
        return null;
    }
}
