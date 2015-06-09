package com.novbank.ndp.core.schema.internal;

import com.google.common.collect.Maps;
import org.apache.avro.Schema;

import java.util.Map;

/**
 * Created by CaoKe on 2015/6/7.
 */
public class DefaultSchemaManager /*implements SchemaManager */{
    protected Map<String,Schema> avroSchemas;

    public DefaultSchemaManager() {
        avroSchemas = Maps.newHashMap();

    }

    /*@Override
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
     public <P> SchemaManager register(Class<P> pojoClass) throws IOException {
        checkNotNull(pojoClass);
        Schema schema = ReflectData.get().getSchema(pojoClass);
        ReflectDatumWriter<P> writer = new ReflectDatumWriter<>(schema);
        GenericDatumReader<Object> reader=new GenericDatumReader<>(schema);
        DatasetDescriptor descriptor = new DatasetDescriptor.Builder()
                .schemaUri("resource:user.avsc")
                .build();
        Dataset<GenericData.Record> users = Datasets.create(
                "dataset:hdfs:/tmp/data/users", descriptor, GenericData.Record.class);
        Datasets.update()
        return null;
    }*/
}
