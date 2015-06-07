package com.novbank.ndp.core.schema.internal.define;

import com.novbank.ndp.core.schema.SchemaManager;
import com.novbank.ndp.core.schema.define.Property;
import com.novbank.ndp.core.schema.define.PropertyGroup;
import com.novbank.ndp.core.schema.define.Record;
import com.novbank.ndp.core.schema.define.RecordDefinition;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;
import org.javatuples.Pair;

import java.util.List;
import java.util.Map;

import static com.novbank.ndp.core.Constants.*;

/**
 * Created by hp on 2015/6/7.
 */
public class DefaultRecord implements Record{
    protected SchemaManager manager;
    protected RecordDefinition definition;
    protected GenericRecord record;

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
    }


    @Override
    public RecordDefinition definition() {
        return definition;
    }

    @Override
    public List<Property> properties() {
        return null;
    }

    @Override
    public Map<String, Object> extProperties() {
        return null;
    }

    @Override
    public PropertyGroup property(String key) {
        return null;
    }

    @Override
    public Object extProperty(String key) {
        return null;
    }

    @Override
    public void property(String key, Object value, Pair<String, String>... labels) throws IllegalArgumentException {

    }

    @Override
    public GenericRecord asAvroRecord() {
        return null;
    }
}
