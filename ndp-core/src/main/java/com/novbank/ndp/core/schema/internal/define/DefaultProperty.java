package com.novbank.ndp.core.schema.internal.define;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.novbank.ndp.core.schema.define.Property;
import com.novbank.ndp.core.schema.define.PropertyDefinition;
import com.novbank.ndp.core.schema.define.Record;
import org.apache.avro.generic.GenericRecord;

import java.util.Collection;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.novbank.ndp.core.Constants.*;

/**
 * Created by hp on 2015/6/7.
 */
public class DefaultProperty implements Property {
    protected Record record;
    protected PropertyDefinition definition;
    protected GenericRecord avroRecord;

    public DefaultProperty(Record record, PropertyDefinition definition, GenericRecord avroRecord) {
        this.record = record;
        this.definition = definition;
        this.avroRecord = avroRecord;
    }

    @Override
    public PropertyDefinition definition() {
        return definition;
    }

    @Override
    public Record record() {
        return record;
    }

    @Override
    public Object value() {
        return avroRecord.get(definition.name());
    }

    @Override
    public Property value(Object value) {
        avroRecord.put(definition.name(),value);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> labels() {
        return (Map<String, Object>) avroRecord.get(FIELD_LABELS);
    }

    @Override
    public Property addLabel(String key, String value) {
        checkNotNull(key);
        Map<String,Object> labels = labels()!=null? labels() : Maps.newHashMap();
        if(!labels.containsKey(key))
            labels.put(key,value);
        else{
            Object oldValue = labels.get(key);
            if(oldValue == null || oldValue instanceof String){
                labels.put(key, Lists.newArrayList(value, oldValue));
            }else if(oldValue instanceof Collection){
                if(!((Collection) oldValue).contains(value))
                    ((Collection) oldValue).add(value);
            }
        }
        avroRecord.put(FIELD_LABELS, labels);
        return this;
    }

    @Override
    public GenericRecord asAvroRecord() {
        return avroRecord;
    }
}
