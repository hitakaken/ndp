package com.novbank.ndp.core.record;

import com.novbank.ndp.core.schema.*;
import org.apache.avro.generic.GenericRecord;

import java.util.List;
import java.util.Map;

/**
 * 记录
 *
 * Created by hp on 2015/6/7.
 */
public interface Record {
    /**
     * @return 记录定义
     */
    RecordDefinition definition();

    /**
     * @return 命名空间
     */
    default String namespace(){ return definition().namespace();};

    /**
     * @return 属性名
     */
    default String name(){ return definition().name();};

    /**
     * @return 属性
     */
    List<Property> properties();

    /**
     * @return 扩展（未定义）属性
     */
    Map<String,Object> extProperties();

    /**
     * @param key 属性名
     * @return true 存在，false 不存在
     */
    default boolean hasProperty( String key ){
        return definition().hasProperty(key) || hasExtProperty(key);
    }

    /**
     * @param key 属性名
     * @return true 存在，false 不存在
     */
    default boolean hasExtProperty(String key){
        return extProperties()!=null && extProperties().containsKey(key);
    }

    /**
     * @param key 属性名
     * @return 属性集合
     */
    PropertyGroup property(String key);

    /**
     * @param key 扩展属性名
     * @return 属性值
     */
    Object extProperty(String key);

    /**
     * @param key 待设置的属性名
     * @param value 新属性值
     * @param labels 标签
     * @return 链式记录
     */
    @SuppressWarnings("unchecked")
    Record addProperty(String key, Object value, String... labels) ;

    /**
     * @param key 待设置的属性名
     * @param value 新属性值
     * @param labels 标签
     * @return 链式记录(不触发更新)
     */
    Record doAddProperty(String key, Object value, String... labels) ;

    /**
     * @param key 待设置的属性名
     * @param value 新属性值
     * @param labels 标签
     * @return 链式记录
     */
    @SuppressWarnings("unchecked")
    Record setProperty(String key, Object value, String... labels) ;

    /**
     * @param key 待设置的属性名
     * @param value 新属性值
     * @param labels 标签
     * @return 链式记录(不触发更新)
     */
    Record doSetProperty(String key, Object value, String... labels) ;

    /**
     * @param key 待删除的属性名
     * @return 链式记录
     */
    Record removeAllProperty(String key);

    /**
     * @param key 待删除的属性名
     * @return 链式记录(不触发更新)
     */
    Record doRemoveAllProperty(String key);

    /**
     * @param propertiesToRemove 待删除的属性
     * @return 链式记录
     */
    Record removeProperty(Property... propertiesToRemove);

    /**
     * @param propertiesToRemove 待删除的属性
     * @return 链式记录(不触发更新)
     */
    Record doRemoveProperty(Property... propertiesToRemove);

    /**
     * @return 链式记录(更新)
     */
    Record doRefresh();

    /**
     * @return 映射到 Avro Record
     */
    GenericRecord asAvroRecord();
}
