package com.novbank.ndp.kernel.core.metadata.schema;

import org.apache.avro.Schema;

import java.util.Collection;
import java.util.Map;

/**
 * Created by ken on 15-5-26.
 */
public interface RecordClass extends Comparable<RecordClass>{
    <T> T newInstance() throws InstantiationException, IllegalAccessException;
    <T> T newInstance(Object... args) throws InstantiationException, IllegalAccessException;
    Collection<RecordProperty> declaredProperties();
    Collection<RecordProperty> properties();
    Map<String, RecordProperty> propertiesMap();
    Collection<RecordProperty> getIndexedProperties();
    RecordProperty getProperty(String propertyName);
    Schema getAvroSchema();
}
