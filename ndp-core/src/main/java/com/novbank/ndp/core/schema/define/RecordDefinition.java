package com.novbank.ndp.core.schema.define;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;

import java.util.List;

/**
 * 记录定义
 * i.e.类比 RDF:Property Avro:Schema
 * i.e 面向存储
 *
 * Created by hp on 2015/6/7.
 */
public interface RecordDefinition {
    /**
     * @return 命名空间
     */
    String namespace();

    /**
     * @return 记录类型
     */
    String name();

    /**
     * @return 属性定义列表
     */
    List<PropertyDefinition> properties();

    /**
     * @param key 属性名
     * @return true 存在，false 不存在
     */
    boolean hasProperty( String key );

    /**
     * @param key 属性名
     * @return 属性定义
     */
    PropertyDefinition property(String key);

    /**
     * @return 映射到Avro Schema对象
     */
    Schema asAvroSchema();

    /**
     * @return 映射到Avro Record
     */
    GenericRecord asAvroRecord();
}
