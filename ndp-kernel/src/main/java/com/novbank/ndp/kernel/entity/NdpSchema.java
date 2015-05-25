package com.novbank.ndp.kernel.entity;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.avro.AvroFactory;
import com.fasterxml.jackson.dataformat.avro.AvroSchema;
import com.fasterxml.jackson.dataformat.avro.schema.AvroSchemaGenerator;
import org.apache.avro.Schema;

/**
 * Created by hp on 2015/5/25.
 */
public class NdpSchema {
    ObjectMapper mapper = new ObjectMapper(new AvroFactory());
    AvroSchemaGenerator gen = new AvroSchemaGenerator();
    AvroSchema jacksonAvroSchema;

}
