package com.novbank.ndp.kernel.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.avro.AvroFactory;
import com.fasterxml.jackson.dataformat.avro.AvroSchema;
import com.fasterxml.jackson.dataformat.avro.schema.AvroSchemaGenerator;
import org.apache.avro.Schema;

/**
 * Created by hp on 2015/5/25.
 */
public class SchemaBuilder {
    ObjectMapper mapper = new ObjectMapper(new AvroFactory());
    AvroSchemaGenerator gen = new AvroSchemaGenerator();

    public SchemaBuilder(){

    }

    public void build(){
        Schema schema;
        String SCHEMA_JSON = "{\n"
                +"\"type\": \"record\",\n"
                +"\"name\": \"Employee\",\n"
                +"\"fields\": [\n"
                +" {\"name\": \"name\", \"type\": \"string\"},\n"
                +" {\"name\": \"age\", \"type\": \"int\"},\n"
                +" {\"name\": \"emails\", \"type\": {\"type\": \"array\", \"items\": \"string\"}},\n"
                +" {\"name\": \"boss\", \"type\": [\"Employee\",\"null\"]}\n"
                +"]}";
        Schema raw = new Schema.Parser().setValidate(true).parse(SCHEMA_JSON);
        AvroSchema avro = new AvroSchema(raw);
    }
}
