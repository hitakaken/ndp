package com.novbank.ndp.core.schema.type;

import com.novbank.ndp.core.schema.namespace.Namespace;
import org.apache.avro.Schema;
import org.apache.parquet.schema.MessageType;

/**
 * Created by hp on 2015/6/2.
 */
public abstract class AbstractRecordType implements RecordType {
    protected Type type;
    protected Schema avro;

    public AbstractRecordType(Type type, Schema avro) {
        this.type = type;
        this.avro = avro;
    }

    @Override
    public Type type() {
        return type;
    }

    @Override
    public Schema asAvro() {
        return avro;
    }
}

