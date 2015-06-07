package com.novbank.ndp.core.schema.define;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 对象属性定义
 * i.e.类比 RDF:Property
 * i.e.面向存储
 *
 * Created by CaoKe on 2015/6/6.
 */
public interface PropertyDefinition {
    /**
     * @return 完整属性名
     */
    default String fullName() {
        return StringUtils.isBlank(namespace()) ? name() : namespace()+"."+name();
    }

    /**
     * @return 命名空间
     */
    String namespace();

    /**
     * @param namespace 命名空间
     * @return 链式定义
     */
    PropertyDefinition namespace(String namespace);

    /**
     * @return 属性名
     */
    String name();

    /**
     * @param name 属性名
     * @return 链式定义
     */
    PropertyDefinition  name(String name);

    /**
     * @return 是否多值
     */
    boolean multiple();

    /**
     * @param multiple 是否多值
     * @return 链式定义
     */
    PropertyDefinition multiple(boolean multiple);

    /**
     * @return 是否有序
     */
    boolean sortable();

    /**
     * @param sortable 是否有序
     * @return 链式定义
     */
    PropertyDefinition sortable(boolean sortable);

    /**
     * @return 元素是否可重复
     */
    boolean duplicatable();

    /**
     * @param duplicatable 元素是否可重复
     * @return 链式定义
     */
    PropertyDefinition duplicatable(boolean duplicatable);

    /**
     * @return 是否带标签
     */
    boolean labeling ();

    /**
     * @param labeling 是否带标签
     * @return 链式定义
     */
    PropertyDefinition labeling(boolean labeling);

    /**
     * @return 是否全局属性
     */
    default boolean globally(){
        return domain()!=null && (domain().size() > 1 || !domain().contains(namespace()));
    };

    /**
     * @return (全局)具有该属性的对象类型
     */
    List<String> domain();

    /**
     * @param domain 拥有该属性的对象类型
     * @return 链式定义
     */
    PropertyDefinition domain(Iterable<String> domain);

    /**
     * @param domainToAdd 待添加的域
     * @return 链式定义
     */
    PropertyDefinition addDomain(String domainToAdd);

    /**
     * @param domainToRemove 待删除的域
     * @return 链式定义
     */
    PropertyDefinition removeDomain(String domainToRemove);

    /**
     * @return 属性值的对象类型
     */
    List<String> range();

    /**
     * @param range 属性值的对象类型
     * @return 链式定义
     */
    PropertyDefinition range(Iterable<String> range);

    /**
     * @param rangeToAdd 待添加的属性值的对象类型
     * @return 链式定义
     */
    PropertyDefinition addRange(String rangeToAdd);

    /**
     * @param rangeToRemove 待删除的属性值的对象类型
     * @return 链式定义
     */
    PropertyDefinition removeRange(String rangeToRemove);

    /**
     * @return 映射到Avro Schema对象
     */
    Schema asAvroSchema();

    /**
     * @return 映射到Avro Record
     */
    GenericRecord asAvroRecord();
}
