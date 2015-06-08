package com.novbank.ndp.core.record.internal;

import com.google.common.collect.ForwardingList;
import com.google.common.collect.Lists;
import com.novbank.ndp.core.record.Property;
import com.novbank.ndp.core.record.PropertyGroup;
import com.novbank.ndp.core.record.Record;
import com.novbank.ndp.core.schema.*;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;
import org.jooq.lambda.Seq;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.novbank.ndp.core.Constants.*;

/**
 * Created by hp on 2015/6/7.
 */
public class DefaultRecord implements Record {
    protected SchemaManager manager;
    protected RecordDefinition definition;
    protected GenericRecord record;
    protected List<Property> properties;

    public DefaultRecord(SchemaManager manager, String fullName){
        this(manager,manager.getOrCreateRecordDefinition(fullName));
    }

    public DefaultRecord(SchemaManager manager, String namespace, String name){
        this(manager,manager.getOrCreateRecordDefinition(namespace,name));
    }

    public DefaultRecord(SchemaManager manager, RecordDefinition definition){
        this(manager, definition, new GenericRecordBuilder(definition.asAvroSchema()).build());
    }

    public DefaultRecord(SchemaManager manager, GenericRecord record){
        this(manager, manager.getOrCreateRecordDefinition(record.getSchema().getFullName()),record);
    }

    public DefaultRecord(SchemaManager manager,  RecordDefinition definition, GenericRecord record) {
        this.manager = manager;
        this.definition = definition;
        this.record = record;
        properties = Lists.newArrayList();
        List<GenericRecord> storedProperties = (List<GenericRecord>) record.get(FIELD_PROPERTIES);
         if(storedProperties!=null && storedProperties.size()>0)
             for(GenericRecord storedProperty : storedProperties)
                 properties.add(new DefaultProperty(this,this.definition.property(storedProperty.getSchema().getFullName()),storedProperty));
    }



    @Override
    public RecordDefinition definition() {
        return definition;
    }

    @Override
    public List<Property> properties() {
        return properties;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> extProperties() {
        return (Map<String, Object>) record.get(FIELD_EXTENSION);
    }

    @Override
    public PropertyGroup property(String key) {
        if(!definition.hasProperty(key))
            return null;
        return new DefaultPropertyGroup(definition.property(key), Seq.seq(properties).filter(p -> p.definition().fullName().equals(key)).toList());
    }

    public static class DefaultPropertyGroup extends ForwardingList<Property> implements PropertyGroup{
        protected PropertyDefinition definition;
        protected List<Property> delegate;
        public DefaultPropertyGroup(PropertyDefinition definition, List<Property> delegate) {
            this.definition = definition;
            this.delegate = delegate;
        }
        @Override
        protected List<Property> delegate() {
            return delegate;
        }
        @Override
        public PropertyDefinition definition() {
            return definition;
        }
    }

    @Override
    public Object extProperty(String key) {
        return extProperties().get(key);
    }

    @Override
    public Record addProperty(String key, Object value, String... labels){
        doAddProperty(key,value,labels);
        return doRefresh();
    }

    @Override
    public Record doAddProperty(String key, Object value, String... labels){
        if(definition.hasProperty(key)){
            Property property = new DefaultProperty(this, definition.property(key)).value(value);
            if(labels!=null && labels.length>0)
                for(int i=0;i<labels.length;i+=2)
                    property.addLabel(labels[i], i+1 < labels.length ? labels[i+1]:null);
            properties.add(property);
        }else{
            extProperties().put(key,value);
            //ignore labels
        }
        return this;
    }

    @Override
    public Record setProperty(String key, Object value, String... labels) {
        doSetProperty(key,value,labels);
        return doRefresh();
    }

    @Override
    public Record doSetProperty(String key, Object value, String... labels) {
        doRemoveAllProperty(key);
        doAddProperty(key,value,labels);
        return this;
    }

    @Override
    public Record removeAllProperty(String key) {
        doRemoveAllProperty(key);
        return doRefresh();
    }

    @Override
    public Record doRemoveAllProperty(String key) {
        if(definition.hasProperty(key))
            return doRemoveProperty(property(key));
        if(hasExtProperty(key))
            extProperties().remove(key);
        return this;
    }

    @Override
    public Record removeProperty(Property... propertiesToRemove) {
        doRemoveProperty(propertiesToRemove);
        return doRefresh();
    }

    @Override
    public Record doRemoveProperty(Property... propertiesToRemove) {
        return doRemoveProperty(Lists.newArrayList(propertiesToRemove));
    }

    private Record doRemoveProperty(List<Property> propertiesToRemove) {
        properties.removeAll(propertiesToRemove);
        return this;
    }

    public Record doRefresh(){
        List<GenericRecord> storedProperties = Lists.newArrayList();
        storedProperties.addAll(properties.stream().map(Property::asAvroRecord).collect(Collectors.toList()));
        record.put(FIELD_PROPERTIES,storedProperties);
        return this;
    }

    @Override
    public GenericRecord asAvroRecord() {
        return record;
    }
}
