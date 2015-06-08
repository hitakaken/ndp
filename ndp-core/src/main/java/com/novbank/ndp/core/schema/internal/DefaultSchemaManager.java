package com.novbank.ndp.core.schema.internal;

import com.google.common.collect.Maps;
import com.novbank.ndp.core.record.internal.DefaultPropertyDefinition;
import com.novbank.ndp.core.record.internal.DefaultRecordDefinition;
import com.novbank.ndp.core.schema.SchemaManager;
import com.novbank.ndp.core.schema.PropertyDefinition;
import com.novbank.ndp.core.schema.RecordDefinition;
import org.apache.avro.Schema;
import org.apache.commons.lang3.StringUtils;
import org.jooq.lambda.Seq;

import java.util.List;
import java.util.Map;

import static com.novbank.ndp.core.util.NamespaceUtils.*;

/**
 * Created by CaoKe on 2015/6/7.
 */
public class DefaultSchemaManager implements SchemaManager {
    protected Map<String,Schema> avroSchemas;
    protected Map<String,RecordDefinition> records;
    protected Map<String,PropertyDefinition> globeProperties;

    public DefaultSchemaManager() {
        avroSchemas = Maps.newHashMap();
        records = Maps.newHashMap();
        globeProperties = Maps.newHashMap();
    }

    @Override
    public List<Schema> genericRecognizedTypes() {
        return Seq.seq(avroSchemas.values()).toList();
    }

    @Override
    public Schema getAvroSchema(String fullName) {
        return avroSchemas.get(fullName);
    }

    @Override
    public Schema getAvroSchema(String namespace, String name) {
        return avroSchemas.get(getFullName(namespace,name));
    }

    @Override
    public SchemaManager addAvroSchema(String namespace, String name, Schema schema) {
        return addAvroSchema(getFullName(namespace,name),schema);
    }

    @Override
    public SchemaManager addAvroSchema(String fullName, Schema schema) {
        avroSchemas.put(fullName,schema);
        return this;
    }

    @Override
    public boolean hasGlobePropertyDefinition(String namespace, String name) {
        return globeProperties.containsKey(getFullName(namespace,name));
    }

    @Override
    public boolean hasPrivatePropertyDefinition(String namespace, String name) {
        return (records.containsKey(namespace) && (
                    records.get(namespace).hasProperty(name)||
                            records.get(namespace).hasProperty(getFullName(namespace,name))));
    }

    protected boolean isPrivatePropertyDefinition(PropertyDefinition propertyDefinition){
        return StringUtils.isBlank(propertyDefinition.namespace()) || records.containsKey(propertyDefinition.namespace());
    }

    @Override
    public PropertyDefinition getOrCreatePropertyDefinition(String namespace, String name) {
        if(hasGlobePropertyDefinition(namespace,name))
            return globeProperties.get(getFullName(namespace,name));
        if(records.containsKey(namespace)){
            if(records.get(namespace).hasProperty(name))
                return records.get(namespace).property(name);
            else if (records.get(namespace).hasProperty(getFullName(namespace,name)))
                return records.get(namespace).property(getFullName(namespace,name));
        }
        return new DefaultPropertyDefinition(this,namespace,name);
    }

    @Override
    public SchemaManager addPropertyDefinition(PropertyDefinition propertyDefinition) {
        if(isPrivatePropertyDefinition(propertyDefinition)){
            //ignore private property
        }else
            globeProperties.put(propertyDefinition.fullName(),propertyDefinition);
        return this;
    }

    @Override
    public RecordDefinition getOrCreateRecordDefinition(String fullName) {
        if(records.containsKey(fullName))
            return records.get(fullName);
        return new DefaultRecordDefinition(this,getNamespaceByFullName(fullName),getNameByFullName(fullName));
    }

    @Override
    public RecordDefinition getOrCreateRecordDefinition(String namespace, String name) {
        if(records.containsKey(getFullName(namespace,name)))
            return records.get(getFullName(namespace,name));
        return new DefaultRecordDefinition(this,namespace,name);
    }

    @Override
    public SchemaManager addRecordDefinition(RecordDefinition recordDefinition) {
        records.put(recordDefinition.fullName(),recordDefinition);
        return this;
    }
}
