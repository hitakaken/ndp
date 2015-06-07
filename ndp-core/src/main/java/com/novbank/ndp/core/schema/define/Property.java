package com.novbank.ndp.core.schema.define;

import org.apache.avro.generic.GenericRecord;
import org.javatuples.Pair;

import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2015/6/7.
 */
public interface Property {
    /**
     * @return 属性定义
     */
    PropertyDefinition definition();

    /**
     * @return 命名空间
     */
    default String namespace(){ return definition().namespace();};

    /**
     * @return 属性名
     */
    default String name(){ return definition().name();};

    /**
     * @return 所属记录
     */
    Record record();

    /**
     * @return 属性值
     */
    Object value();

    /**
     * @param value 属性值
     * @return 链式属性
     */
    Property value(Object value);

    /**
     * @return 标签
     */
    Map<String, Object> labels();

    /**
     * @param key 标签 Key
     * @param value 标签 Value
     * @return 链式属性
     */
    Property addLabel(String key, String value);

    /**
     * @return 映射到 Avro Record
     */
    GenericRecord asAvroRecord();
}
