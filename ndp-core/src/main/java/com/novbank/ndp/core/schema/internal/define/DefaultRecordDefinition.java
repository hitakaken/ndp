package com.novbank.ndp.core.schema.internal.define;

import com.google.common.collect.Maps;
import com.novbank.ndp.core.schema.SchemaManager;
import com.novbank.ndp.core.schema.define.PropertyDefinition;
import com.novbank.ndp.core.schema.define.Record;
import com.novbank.ndp.core.schema.define.RecordDefinition;
import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;
import org.apache.commons.lang3.StringUtils;
import org.jooq.lambda.Seq;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.novbank.ndp.core.Constants.*;

/**
 * Created by hp on 2015/6/7.
 */
public class DefaultRecordDefinition implements RecordDefinition{
    protected SchemaManager manager;
    protected Schema schema;
    protected GenericRecord record;
    protected Map<String,PropertyDefinition> properties;

    public DefaultRecordDefinition(SchemaManager manager, String namespace, String name) {
        this(manager,namespace,name,null);
    }

    public DefaultRecordDefinition(SchemaManager manager, String namespace, String name, Iterable<PropertyDefinition> properties) {
        this.manager = manager;
        this.properties = Maps.newHashMap();
        record = new GenericRecordBuilder(SCHEMA_RECORD_DEFINITION)
                    .set(FIELD_NAME, name)
                    .set(FIELD_NAMESPACE, namespace)
                    .set(FIELD_PROPERTIES, Collections.EMPTY_LIST)
                .build();
        addProperties(properties);
    }

    public DefaultRecordDefinition(SchemaManager manager, GenericRecord record) {
        this.manager = manager;
        this.record = record;
        this.properties = Seq.seq((List<GenericRecord>)record.get(FIELD_NAME))
                .map(r -> (PropertyDefinition) new DefaultPropertyDefinition(manager, r))
                .toMap(PropertyDefinition::fullName, p -> p);
    }

    @Override
    public String namespace() {
        return (String) record.get(FIELD_NAMESPACE);
    }

    @Override
    public RecordDefinition namespace(String namespace) {
        if(!StringUtils.equals(namespace(),namespace)){
            record.put(FIELD_NAMESPACE,namespace);
            schema = null;
        }
        return this;
    }

    @Override
    public String name() {
        return (String)record.get(FIELD_NAMESPACE);
    }

    @Override
    public RecordDefinition name(String name) {
        if(!StringUtils.equals(name(), name)){
            record.put(FIELD_NAME,name);
            schema = null;
        }
        return this;
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

    protected void refreshRecord(){
        record.put(FIELD_PROPERTIES,
                Seq.seq(this.properties.values()).distinct().map(PropertyDefinition::asAvroRecord).toList());
    }

    @Override
    public RecordDefinition addProperties(Iterable<PropertyDefinition> properties){
        if (properties !=null){
            for(PropertyDefinition property : properties)
                this.properties.put(property.fullName(),property);
            refreshRecord();
            schema = null;
        }
        return this;
    }

    @Override
    public RecordDefinition addProperty(PropertyDefinition property) {
        return addProperties(Collections.singleton(property));
    }

    @Override
    public RecordDefinition addProperty(String namespace, String name) {
        return addProperty(manager.getOrCreatePropertyDefinition(namespace, name));
    }

    @Override
    public RecordDefinition addProperty(String namespace, String name, Iterable<String> range) {
        return addProperty(manager.getOrCreatePropertyDefinition(namespace,name).range(range));
    }

    @Override
    public RecordDefinition addProperty(String namespace, String name, Iterable<String> domain, Iterable<String> range) {
        return addProperty(manager.getOrCreatePropertyDefinition(namespace, name)
                .domain(domain).range(range));
    }

    @Override
    public RecordDefinition addProperty(String namespace, String name, boolean multiple) {
        return addProperty(manager.getOrCreatePropertyDefinition(namespace, name)
                .multiple(multiple));
    }

    @Override
    public RecordDefinition addProperty(String namespace, String name, boolean multiple, boolean sortable) {
        return addProperty(manager.getOrCreatePropertyDefinition(namespace, name)
                .multiple(multiple).sortable(sortable));
    }

    @Override
    public RecordDefinition addProperty(String namespace, String name, boolean multiple, boolean sortable, boolean duplicatable) {
        return addProperty(manager.getOrCreatePropertyDefinition(namespace,name)
                .multiple(multiple).sortable(sortable).duplicatable(duplicatable));
    }

    @Override
    public RecordDefinition addProperty(String namespace, String name, boolean multiple, boolean sortable, boolean duplicatable, boolean labeling) {
        return addProperty(manager.getOrCreatePropertyDefinition(namespace,name)
                .multiple(multiple).sortable(sortable).duplicatable(duplicatable).labeling(labeling));
    }

    @Override
    public RecordDefinition addProperty(String namespace, String name, boolean multiple, boolean sortable, boolean duplicatable, boolean labeling, Iterable<String> domain, Iterable<String> range) {
        return addProperty(manager.getOrCreatePropertyDefinition(namespace,name)
                .multiple(multiple).sortable(sortable).duplicatable(duplicatable).labeling(labeling)
                .domain(domain).range(range));
    }

    @Override
    public RecordDefinition removeProperty(String key) {
        if(hasProperty(key)){
            properties.remove(key);
            refreshRecord();
            schema = null;
        }
        return this;
    }

    @Override
    public Schema asAvroSchema() {
        if(schema == null){
            schema = SchemaBuilder.builder(namespace()).record(name()).fields()
            .name(FIELD_PROPERTIES)
                    .type().array().items(Schema.createUnion(Seq.seq(properties.values()).map(PropertyDefinition::asAvroSchema).toList())).noDefault()
            .name(FIELD_EXTENSION)
                    .type().nullable().map().values().type(Schema.createUnion(manager.genericRecognizedTypes())).noDefault()
            .endRecord();

        }
        return schema;
    }

    @Override
    public GenericRecord asAvroRecord() {
        return record;
    }

    public static final Schema SCHEMA_RECORD_DEFINITION;
    static {
        SCHEMA_RECORD_DEFINITION = SchemaBuilder.builder()
                .record(Record.class.getSimpleName())
                .namespace(Record.class.getPackage().getName())
                .fields()
                    .name(FIELD_NAME).type().stringType().noDefault()
                    .name(FIELD_NAMESPACE).type().stringType().noDefault()
                    .name(FIELD_PROPERTIES).type()
                        .map().values(DefaultPropertyDefinition.SCHEMA_PROPERTY_DEFINITION).noDefault()
                .endRecord();
    }
}

