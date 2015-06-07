package com.novbank.ndp.core.schema.internal.define;

import com.google.common.collect.Lists;
import com.novbank.ndp.core.schema.SchemaManager;
import com.novbank.ndp.core.schema.define.Property;
import com.novbank.ndp.core.schema.define.PropertyDefinition;
import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by hp on 2015/6/7.
 */
public class GenericPropertyDefinition implements PropertyDefinition {
    protected SchemaManager manager;
    protected Schema schema;
    protected GenericRecord record;

    public GenericPropertyDefinition(SchemaManager manager, String namespace, String name) {
        this(manager,namespace,name,null);
    }

    public GenericPropertyDefinition(SchemaManager manager, String namespace, String name, Iterable<String> range) {
        this(manager,namespace,name,null,range);
    }

    public GenericPropertyDefinition(SchemaManager manager, String namespace, String name, Iterable<String> domain, Iterable<String> range) {
        this(manager,namespace,name, true,true,true,true, domain, range);
    }

    public GenericPropertyDefinition(SchemaManager manager, String namespace, String name, boolean multiple, boolean sortable, boolean duplicatable, boolean labeling) {
        this(manager,namespace,name, multiple,sortable,duplicatable,labeling, null, null);
    }

    public GenericPropertyDefinition(SchemaManager manager, String namespace, String name, boolean multiple, boolean sortable, boolean duplicatable, boolean labeling, Iterable<String> domain, Iterable<String> range) {
        checkNotNull(name);
        this.manager = manager;
        record = new GenericRecordBuilder(SCHEMA_PROPERTY_DEFINITION)
                    .set(FIELD_NAMESPACE, namespace)
                    .set(FIELD_NAME, name)
                    .set(FIELD_MULTIPLE, multiple)
                    .set(FIELD_SORTABLE, sortable)
                    .set(FIELD_DUPLICATABLE, duplicatable)
                    .set(FIELD_LABELING, labeling)
                    .set(FIELD_DOMAIN, (domain != null ? Lists.newArrayList(domain) : null))
                    .set(FIELD_RANGE, (range != null ? Lists.newArrayList(range) : null))
                .build();
    }

    public GenericPropertyDefinition(SchemaManager manager, GenericRecord record) {
        this.manager = manager;
        this.record = record;
    }

    @Override
    public String namespace() {
        return (String) record.get(FIELD_NAMESPACE);
    }

    @Override
    public PropertyDefinition namespace(String namespace) {
        if(!StringUtils.equals(namespace(),namespace)){
            record.put(FIELD_NAMESPACE,namespace);
            schema = null;
        }
        return this;
    }

    @Override
    public String name() {
        return (String) record.get(FIELD_NAME);
    }

    @Override
    public PropertyDefinition name(String name) {
        if(!StringUtils.equals(name(),name)){
            record.put(FIELD_NAME,name);
            schema = null;
        }
        return this;
    }

    @Override
    public boolean multiple() {
        return (boolean) record.get(FIELD_MULTIPLE);
    }

    @Override
    public PropertyDefinition multiple(boolean multiple) {
        if(multiple() != multiple){
            record.put(FIELD_MULTIPLE,multiple);
            schema = null;
        }
        return this;
    }

    @Override
    public boolean sortable() {
        return (boolean) record.get(FIELD_SORTABLE);
    }

    @Override
    public PropertyDefinition sortable(boolean sortable) {
        if(sortable() != sortable){
            record.put(FIELD_SORTABLE,sortable);
            schema = null;
        }
        return this;
    }

    @Override
    public boolean duplicatable() {
        return (boolean) record.get(FIELD_DUPLICATABLE);
    }

    @Override
    public PropertyDefinition duplicatable(boolean duplicatable) {
        if(duplicatable() != duplicatable){
            record.put(FIELD_DUPLICATABLE,duplicatable);
            schema = null;
        }
        return this;
    }

    @Override
    public boolean labeling() {
        return (boolean) record.get(FIELD_LABELING);
    }

    @Override
    public PropertyDefinition labeling(boolean labeling) {
        if(labeling() != labeling){
            record.put(FIELD_LABELING, labeling);
            schema = null;
        }
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<String> domain() {
        return (List<String>) record.get(FIELD_DOMAIN);
    }

    @Override
    public PropertyDefinition domain(Iterable<String> domain) {
        record.put(FIELD_DOMAIN,Lists.newArrayList(domain));
        schema = null;
        return this;
    }

    @Override
    public PropertyDefinition addDomain(String domainToAdd) {
        if(StringUtils.isNotBlank(domainToAdd)){
            if(domain() == null){
                record.put(FIELD_DOMAIN,Lists.newArrayList(domainToAdd));
                schema = null;
            }else if(!domain().contains(domainToAdd)){
                domain().add(domainToAdd);
                schema = null;
            }
        }
        return this;
    }

    @Override
    public PropertyDefinition removeDomain(String domainToRemove) {
        if(domain() != null && domain().contains(domainToRemove)){
            domain().remove(domainToRemove);
            schema = null;
        }
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<String> range() {
        return (List<String>) record.get(FIELD_RANGE);
    }

    @Override
    public PropertyDefinition range(Iterable<String> range) {
        record.put(FIELD_RANGE,Lists.newArrayList(range));
        schema = null;
        return this;
    }

    @Override
    public PropertyDefinition addRange(String rangeToAdd) {
        if(StringUtils.isNotBlank(rangeToAdd)){
            if(range() == null){
                record.put(FIELD_RANGE,Lists.newArrayList(rangeToAdd));
                schema = null;
            }else if(!range().contains(rangeToAdd)){
                range().add(rangeToAdd);
                schema = null;
            }
        }
        return this;
    }

    @Override
    public PropertyDefinition removeRange(String rangeToRemove) {
        if(range() != null && range().contains(rangeToRemove)){
            range().remove(rangeToRemove);
            schema = null;
        }
        return this;
    }

    @Override
    public Schema asAvroSchema() {
        if(schema == null){
            SchemaBuilder.RecordBuilder<Schema> builder = SchemaBuilder.record(name());
            if(StringUtils.isNotBlank(namespace())) builder.namespace(namespace());
            List<Schema> types = Lists.newArrayList();
            if(range() == null)
                types.addAll(manager.genericRecognizedTypes());
            else
                range().forEach(t -> types.add(manager.getSchema(t)));
            Schema s = types.size() == 1?types.get(1) : Schema.createUnion(types);
            builder.fields();
            if(labeling())
                builder.fields().name(FIELD_LABEL).type().nullable().array().items().unionOf().stringType().and().map().values().stringType().endUnion().noDefault();
            schema = !labeling() ?builder.fields().name(name()).type(s).noDefault().endRecord():
                    builder.fields()
                            .name(name()).type(s).noDefault()
                            .name(FIELD_LABEL).type().nullable().array().items().unionOf().stringType().and().map().values().stringType().endUnion().noDefault()
                            .endRecord();
        }
        return schema;
    }

    @Override
    public GenericRecord asAvroRecord() {
        return record;
    }

    public static final String FIELD_NAME = "name";
    public static final String FIELD_NAMESPACE = "namespace";
    public static final String FIELD_MULTIPLE = "multiple";
    public static final String FIELD_SORTABLE = "sortable";
    public static final String FIELD_DUPLICATABLE = "duplicatable";
    public static final String FIELD_LABELING = "labeling";
    public static final String FIELD_DOMAIN = "domain";
    public static final String FIELD_RANGE = "range";
    public static final String FIELD_LABEL = "label";

    public static final Schema SCHEMA_PROPERTY_DEFINITION;
    static {
        SCHEMA_PROPERTY_DEFINITION = SchemaBuilder.builder()
                .record(Property.class.getSimpleName())
                .namespace(Property.class.getPackage().getName())
                .fields()
                    .name(FIELD_NAME).type().stringType().noDefault()
                    .name(FIELD_NAMESPACE).type().nullable().stringType().noDefault()
                    .name(FIELD_MULTIPLE).type().booleanType().booleanDefault(true)
                    .name(FIELD_SORTABLE).type().booleanType().booleanDefault(true)
                    .name(FIELD_DUPLICATABLE).type().booleanType().booleanDefault(true)
                    .name(FIELD_LABELING).type().booleanType().booleanDefault(true)
                    .name(FIELD_DOMAIN).type().nullable().array().items().stringType().noDefault()
                    .name(FIELD_RANGE).type().nullable().array().items().stringType().noDefault()
                .endRecord();
    }
}
