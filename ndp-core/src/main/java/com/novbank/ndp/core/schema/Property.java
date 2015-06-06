package com.novbank.ndp.core.schema;

import org.apache.avro.Schema;

/**
 * 对象属性 （基于 RDF）
 *
 * Created by CaoKe on 2015/6/6.
 */
public interface Property {
    /**
     * @return 命名空间
     */
    String namespace();

    /**
     * @return 属性名称
     */
    String name();

    /**
     * @return 具有该属性的对象类型
     */
    Iterable<String> domain();

    /**
     * @return 属性值的对象类型
     */
    Iterable<String> range();

    /**
     * @return 是否多值
     */
    boolean multiple();

    /**
     * @return 是否有序
     */
    boolean sortable();

    /**
     * @return 带标签的
     */
    boolean labeling ();

    /**
     * @return 映射到Avro Schema对象
     */
    Schema asSchema();
}
