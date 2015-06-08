package com.novbank.ndp.core.schema;

import org.apache.avro.Schema;

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

    /**
     * @param fullName
     * @param schema
     * @return 链式管理器
     */
    SchemaManager addAvroSchema(String fullName, Schema schema);

    /**
     * @param namespace 命名空间
     * @param name 属性名
     * @return 是否包含符合条件的属性（全局/私有）
     */
    default boolean hasPropertyDefinition(String namespace, String name){
        return hasGlobePropertyDefinition(namespace,name) || hasPrivatePropertyDefinition(namespace,name);
    }

    /**
     * @param namespace 命名空间
     * @param name 属性名
     * @return 是否包含符合条件的属性（全局）
     */
    boolean hasGlobePropertyDefinition(String namespace, String name);

    /**
     *
     * @param fullName 记录类型名称
     * @param name  属性名
     * @return 是否包含符合条件的属性（全局）
     */
    boolean hasPrivatePropertyDefinition(String fullName, String name);

    /**
     * @param namespace 命名空间
     * @param name 属性名
     * @return 获取符合条件的全局属性（RDF）/ 或者新建属性
     */
    PropertyDefinition getOrCreatePropertyDefinition(String namespace, String name);

    /**
     * @param propertyDefinition
     * @return 链式管理器
     */
    SchemaManager addPropertyDefinition(PropertyDefinition propertyDefinition);

    /**
     * @param fullName 记录类型全名
     * @return 记录定义
     */
    RecordDefinition getOrCreateRecordDefinition(String fullName);

    /**
     * @param namespace 命名空间
     * @param name 记录名称
     * @return 记录定义
     */
    RecordDefinition getOrCreateRecordDefinition(String namespace, String name);

    /**
     * @param recordDefinition
     * @return 链式管理器
     */
    SchemaManager addRecordDefinition(RecordDefinition recordDefinition);


}
