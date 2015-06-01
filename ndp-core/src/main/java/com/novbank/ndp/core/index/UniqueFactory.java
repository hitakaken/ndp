package com.novbank.ndp.core.index;

import com.novbank.ndp.core.record.PropertyContainer;

import java.util.Collections;
import java.util.Map;

/**
 * Created by hp on 2015/6/1.
 */
public abstract class UniqueFactory<T extends PropertyContainer> {
    public static class UniqueRecord<T extends PropertyContainer>{
        private final T record;
        private final boolean created;

        UniqueRecord( T record, boolean created ){
            this.record = record;
            this.created = created;
        }

        public T record()
        {
            return this.record;
        }

        public boolean wasCreated()
        {
            return this.created;
        }
    }

    /**
     * Implement this method to create the {@link Node} or {@link Relationship} to index.
     *
     * This method will be invoked exactly once per transaction that attempts to create an entry in the index.
     * The created entity might be discarded if another thread creates an entity with the same mapping concurrently.
     *
     * @param properties the properties that this entity will is to be indexed uniquely with.
     * @return the entity to add to the index.
     */
    protected abstract T create( Map<String, Object> properties );

    /**
     * Implement this method to initialize the {@link Node} or {@link Relationship} created for being stored in the index.
     *
     * This method will be invoked exactly once per created unique entity.
     *
     * The created entity might be discarded if another thread creates an entity concurrently.
     * This method will however only be invoked in the transaction that succeeds in creating the node.
     *
     * @param created the created entity to initialize.
     * @param properties the properties that this entity was indexed uniquely with.
     */
    protected abstract void initialize( T created, Map<String, Object> properties );

    /**
     * Invoked after a new entity has been {@link #create(Map) created}, but adding it to the index failed (due to being
     * added by another transaction concurrently). The purpose of this method is to undo the {@link #create(Map)
     * creation of the entity}, the default implementations of this method remove the entity. Override this method to
     * define a different behavior.
     *
     * @param created the entity that was created but was not added to the index.
     */
    protected abstract void delete( T created );

    /**
     * Get the indexed entity, creating it (exactly once) if no indexed entity exists.
     * @param key the key to find the entity under in the index.
     * @param value the value the key is mapped to for the entity in the index.
     * @return the unique entity in the index.
     */
    public final T getOrCreate( String key, Object value )
    {
        return getOrCreateWithOutcome( key, value ).record();
    }

    /**
     * Get the indexed entity, creating it (exactly once) if no indexed entity exists.
     * Includes the outcome, i.e. whether the entity was created or not.
     * @param key the key to find the entity under in the index.
     * @param value the value the key is mapped to for the entity in the index.
     * @return the unique entity in the index as well as whether or not it was created,
     * wrapped in a {@link UniqueRecord}.
     */
    public final UniqueRecord<T> getOrCreateWithOutcome( String key, Object value )
    {
        // Index reads implies asserting we're in a transaction.
        T result = index.get( key, value ).getSingle();
        boolean wasCreated = false;
        if ( result == null )
        {
            /*try ( Transaction tx = graphDatabase().beginTx() )
            {
                Map<String, Object> properties = Collections.singletonMap(key, value);
                T created = create( properties );
                result = index.putIfAbsent( created, key, value );
                if ( result == null )
                {
                    initialize( created, properties );
                    result = created;
                    wasCreated = true;
                }
                else    {
                    delete( created );
                }
                tx.success();
            }*/
        }
        return new UniqueRecord<>( result, wasCreated );
    }

    /**
     * Get the referenced index.
     * @return the referenced index.
     */
    protected final Index<T> index()
    {
        return index;
    }

    private final Index<T> index;

    private UniqueFactory( Index<T> index ){
        this.index = index;
    }
}
