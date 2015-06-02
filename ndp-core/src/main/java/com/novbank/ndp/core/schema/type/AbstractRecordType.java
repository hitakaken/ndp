package com.novbank.ndp.core.schema.type;

import com.novbank.ndp.core.schema.namespace.Namespace;
import org.apache.avro.Schema;
import org.apache.parquet.schema.MessageType;

/**
 * Created by hp on 2015/6/2.
 */
public abstract class AbstractRecordType implements RecordType {
    private String name;
    private Namespace namespace;
    private Type type;
    private Class pojoClass;
    private Schema avro;
    private MessageType parquet;

    public AbstractRecordType(String name, Namespace namespace, Type type, Class pojoClass, Schema avro, MessageType parquet) {
        this.name = name;
        this.namespace = namespace;
        this.type = type;
        this.pojoClass = pojoClass;
        this.avro = avro;
        this.parquet = parquet;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Namespace namespace() {
        return namespace;
    }

    @Override
    public Type type() {
        return type;
    }

    @Override
    public Class asPojoClass() {
        return pojoClass;
    }

    @Override
    public Schema asAvro() {
        return avro;
    }

    @Override
    public MessageType asParquet() {
        return parquet;
    }

    @Override
    public boolean isInstance(Object object) {
        return pojoClass.isInstance(object);
    }

    @Override
    public boolean isAssignableFrom(Class clazz) {
        return pojoClass.isAssignableFrom(clazz);
    }

    @Override
    public boolean isAssignableFrom(RecordType otherType) {
        return pojoClass.isAssignableFrom(otherType.asPojoClass());
    }
}

