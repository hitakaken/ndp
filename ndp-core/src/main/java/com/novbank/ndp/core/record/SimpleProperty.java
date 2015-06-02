package com.novbank.ndp.core.record;

import java.util.NoSuchElementException;

/**
 * Created by hp on 2015/6/2.
 */
public class SimpleProperty<V> implements Property<V>{
    private PropertyContainer container;
    private String key;
    private V value;

    public SimpleProperty(PropertyContainer container, String key, V value) {
        this.container = container;
        this.key = key;
        this.value = value;
    }

    @Override
    public String key() {
        return key;
    }

    @Override
    public V value() throws NoSuchElementException {
        return value;
    }

    @Override
    public boolean isPresent() {
        return value!=null;
    }

    @Override
    public PropertyContainer container() {
        return container;
    }

    @Override
    public void remove() {
        container.remove(key);
    }
}
