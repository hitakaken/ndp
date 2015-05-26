package com.novbank.ndp.kernel.core.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.avro.AvroFactory;
import com.fasterxml.jackson.dataformat.avro.AvroSchema;
import com.fasterxml.jackson.dataformat.avro.schema.AvroSchemaGenerator;
import com.xmlns.foaf.*;
import org.apache.avro.Schema;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hp on 2015/5/26.
 */
public class JacksonTest {

    @Test
    public void testNewInstance() throws Exception {
        ObjectMapper mapper = new ObjectMapper(new AvroFactory());
                //Jackson.newInstance(new AvroFactory());
        AvroSchemaGenerator gen = new AvroSchemaGenerator();
        //mapper.acceptJsonFormatVisitor(Agent.class, gen);
        mapper.acceptJsonFormatVisitor(Document.class, gen);
        /*mapper.acceptJsonFormatVisitor(Group.class, gen);
        mapper.acceptJsonFormatVisitor(Organization.class, gen);
        mapper.acceptJsonFormatVisitor(Person.class, gen);
        mapper.acceptJsonFormatVisitor(Project.class, gen);*/
        AvroSchema schemaWrapper = gen.getGeneratedSchema();

        org.apache.avro.Schema avroSchema = schemaWrapper.getAvroSchema();
        String asJson = avroSchema.toString(true);
        System.out.println(asJson);
    }
}