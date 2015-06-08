package com.novbank.ndp.core.index;

import com.novbank.ndp.core.record.other.PropertyContainer;

import java.util.Set;

/**
 * 自动索引器
 *
 * Created by hp on 2015/6/1.
 */
public interface AutoIndexer<T extends PropertyContainer> {
    /**
     * @param enabled 是否自动索引
     */
    void setEnabled( boolean enabled );

    /**
     * @return 是否自动索引
     */
    boolean isEnabled();

    /**
     * @return 自动索引
     */
    ReadableIndex<T> getAutoIndex();

    /**
     * @param propName 属性名
     */
    void startAutoIndexingProperty( String propName );

    /**
     * @param propName 停止自动索引
     */
    void stopAutoIndexingProperty( String propName );

    /**
     * @return 自动索引的属性列表
     */
    Set<String> getAutoIndexedProperties();
}
