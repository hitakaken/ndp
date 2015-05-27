package com.novbank.ndp.kernel.core.metadata.schema;

import org.apache.avro.Schema;
import org.apache.parquet.schema.MessageType;
import org.codehaus.groovy.tools.GroovyClass;

import java.util.Collection;
import java.util.Map;

/**
 * Created by ken on 15-5-26.
 */
public interface RecordClass extends Comparable<RecordClass>{
    <T> T newInstance() throws InstantiationException, IllegalAccessException;

    <T> T newInstance(Object... args) throws InstantiationException, IllegalAccessException;

    boolean isAbstract();

    RecordClass setAbstract(boolean isAbstract);

    boolean isStrictMode();

    RecordClass setStrictMode(boolean isStrictMode);

    RecordClass getSuperClass();

    RecordClass setSuperClass(RecordClass superClass);

    boolean isSubClassOf(String className);

    boolean isSubClassOf(RecordClass clazz);

    boolean isSuperClassOf(String className);

    boolean isSuperClassOf(RecordClass clazz);

    String getName();

    RecordClass setName(String name);

    String getNamespace();

    RecordClass setNamespace(String namespace);

    String getFullName();

    boolean isRdfsClass();

    Collection<RecordProperty> declaredProperties();

    Collection<RecordProperty> properties();

    Map<String, RecordProperty> propertiesMap();

    RecordProperty getProperty(String propertyName);

    RecordProperty getProperty(String prefix, String propertyName);

    RecordProperty createProperty(String propertyName, RecordType type);

    RecordProperty createProperty(String prefix, String propertyName, RecordType type);

    RecordProperty createProperty(String propertyName, RecordType type, RecordClass... linkedClasses);

    RecordProperty createProperty(String prefix, String propertyName, RecordType type, RecordClass... linkedClasses);

    RecordProperty createProperty(String propertyName, RecordType type, RecordType... linkedTypes);

    RecordProperty createProperty(String prefix, String propertyName, RecordType type, RecordType... linkedTypes);

    void dropProperty(String propertyName);

    void dropProperty(String prefix, String propertyName);

    boolean existsProperty(String propertyName);

    boolean existsProperty(String prefix, String propertyName);

    Class<?> getJavaClass();

    GroovyClass getGroovyClass();

    Schema getAvroSchema();

    MessageType getParquetSchema();

    Collection<RecordProperty> getIndexedProperties();
}
