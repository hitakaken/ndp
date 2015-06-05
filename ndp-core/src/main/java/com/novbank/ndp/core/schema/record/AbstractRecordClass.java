package com.novbank.ndp.core.schema.record;

import com.google.common.collect.Maps;
import com.novbank.ndp.core.helper.reflect.PropertyContainerHelper;
import com.novbank.ndp.core.record.AbstractPropertyContainer;
import com.novbank.ndp.core.record.Property;
import com.novbank.ndp.core.schema.namespace.Namespace;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by CaoKe on 2015/6/1.
 */
public abstract class AbstractRecordClass extends AbstractPropertyContainer implements RecordClass {
    protected RecordSchema schema;
    protected Namespace namespace;
    protected String name;
    protected Map<String,Property<RecordProperty>> properties;

    public AbstractRecordClass(RecordSchema schema) {
        this.schema = schema;
        properties = Maps.newHashMap();
    }

    public AbstractRecordClass(RecordSchema schema,Namespace namespace,String name, Set<RecordProperty> properties) {
        this.schema = schema;
        this.namespace = namespace;
        this.name = name;
        this.properties = Maps.newHashMap();
        /*if(properties!=null && !properties.isEmpty())
            for(RecordProperty property:properties)
                this.properties.put(property.name(),new SimpleProperty<>(this,property.name(),property));*/
    }

    @Override
    public RecordSchema schema() {
        return schema;
    }

    public Namespace getNamespace() {
        return namespace;
    }

    public void setNamespace(Namespace namespace) {
        this.namespace = namespace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean isHandled(Method m) {
        return PropertyContainerHelper.isPropertyContainerMethod(m);
    }

    @Override
    public boolean hasProperty(String key) {
        return properties.containsKey(key);
    }

    @Override
    public <V> void property(String key, V value) {
        Property property = wrap(value);
        if(property!=null)
            properties.put(key,property);
    }

    protected <V> Property<RecordProperty> wrap(V value){
       /* if(value instanceof RecordProperty)
            return new SimpleProperty<>(this,((RecordProperty) value).name(),((RecordProperty) value));*/
        return null;
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
        return null;/*properties.entrySet().stream().filter(entry ->
                PropertyContainerHelper.keyExists(entry.getKey(), propertyKeys))
                .map(Map.Entry::getValue)
                .iterator();*/
    }
}
