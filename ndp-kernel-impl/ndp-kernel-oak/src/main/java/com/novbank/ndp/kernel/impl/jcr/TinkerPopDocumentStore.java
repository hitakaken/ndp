package com.novbank.ndp.kernel.impl.jcr;

import com.google.common.collect.ImmutableMap;
import org.apache.jackrabbit.oak.cache.CacheStats;
import org.apache.jackrabbit.oak.plugins.document.*;
import org.apache.jackrabbit.oak.plugins.document.cache.CacheInvalidationStats;
import org.apache.jackrabbit.oak.plugins.document.rdb.RDBOptions;
import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.jooq.lambda.Seq;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Created by ken on 15-5-18.
 */
public class TinkerPopDocumentStore implements DocumentStore{


    public TinkerPopDocumentStore(Graph graph, DocumentMK.Builder builder) {
        try {
            initialize(graph, builder);
        } catch (Exception e) {
            throw new DocumentStoreException("initializing Graph document store", e);
        }
    }

    private Map<String, String> metadata;

    private void initialize(Graph graph, DocumentMK.Builder builder) throws Exception {
        metadata = ImmutableMap.<String,String>builder()
                .put("graph_api","tinkerpop3").build();
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
        return metadata;
    }

    protected <T extends Document> T convertFromVertex(Collection<T> collection, Vertex v) {
        T copy = null;
        if (v != null) {
            copy = collection.newDocument(this);
            //property
            for (String key : v.keys())
                copy.put(key,v.property(key).value());
            final T finalCopy = copy;
            Seq.seq(v.edges(Direction.IN)).forEach(edge -> {
                //finalCopy.put();
            });

        }
        return copy;
    }
}
