package com.novbank.ndp.core.index;

import com.novbank.ndp.core.record.other.PropertyContainer;

/**
 * Created by hp on 2015/6/1.
 */
public interface Index<T extends PropertyContainer> extends ReadableIndex<T>{
    /**
     * @param record 对象
     * @param key 索引名
     * @param value 索引值
     */
    void add( T record, String key, Object value );

    /**
     * @param record 对象
     * @param key 索引名
     * @param value 索引值
     */
    void remove( T record, String key, Object value );

    /**
     * @param record 对象
     * @param key 索引名
     */
    void remove( T record, String key );

    /**
     * @param record 对象
     */
    void remove( T record);

    /**
     * 删除索引
     */
    void delete();

    /**
     * @param record 对象
     * @param key 索引名
     * @param value 索引值
     * @return
     */
    T putIfAbsent( T record, String key, Object value );
}
