package com.novbank.ndp.core.schema;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;

import java.io.IOException;
import java.util.List;

/**
 * Schema管理器
 * 基于Avro实现Schema的管理
 *
 * Created by hp on 2015/6/5.
 */
public interface SchemaManager {
    /**
     * @return 通用类型对应的Avro Schemas
     */
    List<Schema> genericRecognizedTypes();

    /**
     * @param fullName  Schema 全名
     * @return 对应的Avro Schema
     */
    Schema getAvroSchema(String fullName);

    /**
     * @param namespace 命名空间
     * @param name 名称
     * @return 对应的Avro Schema
     */
    Schema getAvroSchema(String namespace, String name);

    /**
     * @param namespace
     * @param name
     * @param schema
     * @return 链式管理器
     */
    SchemaManager addAvroSchema(String namespace, String name, Schema schema);

    SchemaManager addAvroSchema(String fullName, Schema schema);

    /**
     * @param schema
     * @return 链式管理器
     */
    SchemaManager register(Schema schema);

    <P> SchemaManager register(Class<P> pojoClass) throws IOException;

    SchemaManager register(Package pojoPackage);

    GenericRecord toAvroRecord(Object pojo);
}
