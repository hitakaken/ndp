package com.novbank.ndp.kernel.entity;


import com.fasterxml.jackson.dataformat.avro.AvroSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;

/**
 * Created by hp on 2015/5/25.
 */
public class Schema {
    AvroSchema avroSchema;

    public Schema(AvroSchema avroSchema) {
        this.avroSchema = avroSchema;
    }

    public AvroSchema getAvroSchema() {
        SchemaFactoryWrapper wrapper;
        avroSchema.getAvroSchema();
        return avroSchema;
    }
}
