package com.novbank.ndp.core.schema.internal.define;

import com.novbank.ndp.core.schema.SchemaManager;
import com.novbank.ndp.core.schema.define.PropertyDefinition;
import com.novbank.ndp.core.schema.define.Record;
import com.novbank.ndp.core.schema.define.RecordDefinition;
import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.apache.avro.generic.GenericRecord;

import java.util.Collection;
import java.util.Map;

/**
 * Created by hp on 2015/6/7.
 */
public class GenericRecordDefinition implements RecordDefinition{
    protected SchemaManager manager;
    protected Schema schema;
    protected GenericRecord record;
    protected Map<String,PropertyDefinition> properties;

    public GenericRecordDefinition(SchemaManager manager, String namespace, String name) {
        this(manager,namespace,name,null);
    }

    public GenericRecordDefinition(SchemaManager manager, String namespace, String name, Iterable<PropertyDefinition> properties) {

    }

    public GenericRecordDefinition(SchemaManager manager, GenericRecord record) {
        this.manager = manager;
        this.record = record;
        record.get(FIELD_PROPERTIES);
    }

    @Override
    public String namespace() {
        return null;
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public Collection<PropertyDefinition> properties() {
        return properties.values();
    }

    @Override
    public boolean hasProperty(String key) {
        return properties.containsKey(key);
    }

    @Override
    public PropertyDefinition property(String key) {
        return hasProperty(key)?properties.get(key):null;
    }

    @Override
    public RecordDefinition addProperty(PropertyDefinition property) {
        return null;
    }

    @Override
    public RecordDefinition addProperty(String namespace, String name) {
        return null;
    }

    @Override
    public RecordDefinition addProperty(String namespace, String name, Iterable<String> range) {
        return null;
    }

    @Override
    public RecordDefinition addProperty(String namespace, String name, Iterable<String> domain, Iterable<String> range) {
        return null;
    }

    @Override
    public RecordDefinition addProperty(String namespace, String name, boolean multiple) {
        return null;
    }

    @Override
    public RecordDefinition addProperty(String namespace, String name, boolean multiple, boolean sortable) {
        return null;
    }

    @Override
    public RecordDefinition addProperty(String namespace, String name, boolean multiple, boolean sortable, boolean duplicatable) {
        return null;
    }

    @Override
    public RecordDefinition addProperty(String namespace, String name, boolean multiple, boolean sortable, boolean duplicatable, boolean labeling) {
        return null;
    }

    @Override
    public RecordDefinition addProperty(String namespace, String name, boolean multiple, boolean sortable, boolean duplicatable, boolean labeling, Iterable<String> domain, Iterable<String> range) {
        return null;
    }

    @Override
    public RecordDefinition removeProperty(String key) {
        return null;
    }

    @Override
    public Schema asAvroSchema() {
        return null;
    }

    @Override
    public GenericRecord asAvroRecord() {
        return record;
    }

    public static final String FIELD_NAME = "name";
    public static final String FIELD_NAMESPACE = "namespace";
    public static final String FIELD_PROPERTIES = "properties";

    public static final Schema SCHEMA_RECORD_DEFINITION;
    static {
        SCHEMA_RECORD_DEFINITION = SchemaBuilder.builder()
                .record(Record.class.getSimpleName())
                .namespace(Record.class.getPackage().getName())
                .fields()
                .name(FIELD_NAME).type().stringType().noDefault()
                .name(FIELD_NAMESPACE).type().stringType().noDefault()
                .name(FIELD_PROPERTIES).type().map().values(GenericPropertyDefinition.SCHEMA_PROPERTY_DEFINITION).noDefault()
                .endRecord();
    }
}

