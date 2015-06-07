package com.novbank.ndp.core.schema.internal.define;

import com.novbank.ndp.core.schema.SchemaManager;
import com.novbank.ndp.core.schema.define.PropertyDefinition;
import com.novbank.ndp.core.schema.define.Record;
import com.novbank.ndp.core.schema.define.RecordDefinition;
import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.apache.avro.generic.*;
import org.apache.avro.generic.GenericRecord;

import java.util.List;
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
    public List<PropertyDefinition> properties() {
        return null;
    }

    @Override
    public Schema asAvroSchema() {
        return null;
    }

    @Override
    public GenericRecord asAvroRecord() {
        return null;
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
                .name(FIELD_PROPERTIES).type().array().items(GenericPropertyDefinition.SCHEMA_PROPERTY_DEFINITION).noDefault()
                .endRecord();
    }
}

