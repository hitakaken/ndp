package com.novbank.ndp.core.record;

import com.google.common.collect.Maps;
import com.novbank.ndp.core.helper.reflect.PropertyContainerHelper;
import com.novbank.ndp.core.util.IteratorUtils;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by CaoKe on 2015/6/1.
 */
public abstract class AbstractRecordClass extends AbstractPropertyContainer implements RecordClass {
    protected RecordSchema schema;
    protected Map<String,RecordProperty> properties;

    public AbstractRecordClass(RecordSchema schema) {
        this.schema = schema;
        properties = Maps.newHashMap();
    }

    public AbstractRecordClass(RecordSchema schema,Map<String,RecordProperty> properties) {
        this.schema = schema;
        this.properties = properties;
    }

    @Override
    public boolean isHandled(Method m) {
        return PropertyContainerHelper.isPropertyContainerMethod(m);
    }

    @Override
    public boolean hasProperty(String key) {
        return properties.containsKey(key);
    }

    public abstract <V> RecordProperty<V> of(V value);

    @Override
    public <V> void property(String key, V value) {
        properties.put(key, of(value));
    }

    @Override
    public Property remove(String key) {
        return properties.remove(key);
    }

    @Override
    public Iterable<String> keys() {
        return properties.keySet();
    }

    @Override
    public <V> Iterator<? extends Property<V>> properties(String... propertyKeys) {
        if (null == properties)
            return Collections.emptyIterator();
        return properties.entrySet().stream().filter(entry ->
                PropertyContainerHelper.keyExists(entry.getKey(), propertyKeys))
                .map(Map.Entry::getValue)
                .iterator();
    }
}
