package com.novbank.ndp.core.index;

import com.novbank.ndp.core.record.other.PropertyContainer;

/**
 * Created by hp on 2015/6/1.
 */
public interface ReadableIndex<T extends PropertyContainer> {
    /**
     * @return 索引名称
     */
    String getName();

    /**
     * @return 被索引对象类型
     */
    Class<T> getType();

    /**
     * @param key 索引名
     * @param value 索引值
     * @return 命中的索引
     */
    IndexHits<T> get( String key, Object value );

    /**
     * @param key 索引名
     * @param queryOrQueryObject 查询或查询对象
     * @return 命中的索引
     */
    IndexHits<T> query( String key, Object queryOrQueryObject );

    /**
     * @param queryOrQueryObject 查询或查询对象
     * @return 命中的索引
     */
    IndexHits<T> query( Object queryOrQueryObject );

    /**
     * @return 索引是否可写
     */
    boolean isWriteable();
}
