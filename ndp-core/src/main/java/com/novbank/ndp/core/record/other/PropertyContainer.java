package com.novbank.ndp.core.record.other;

import com.novbank.ndp.core.util.iterator.IteratorUtils;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 属性容器
 *
 * Created by hp on 2015/6/1.
 */
public interface PropertyContainer {
    /**
     * @param key 属性名
     * @return true 存在，false 不存在
     */
    boolean hasProperty( String key );

    /**
     * @param key 属性名
     * @return 属性
     */
    default <V> Property<V> property( String key ){
        final Iterator<? extends Property<V>> iterator = this.properties(key);
        return iterator.hasNext() ? iterator.next() : Property.<V>empty();
    }

    /**
     * @param key 属性名
     * @param <V>
     * @return 属性值
     * @throws NoSuchElementException
     */
    default <V> V value(final String key) throws NoSuchElementException{
        return this.<V>property(key).orElseThrow(() -> Property.Exceptions.propertyDoesNotExist(this,key));
    }

    /**
     * @param key 待设置的属性名
     * @param value 新属性值
     */
    <V> void property( String key, V value );

    /**
     * @param key 待删除的属性值
     * @return 被删除的属性
     */
    Property remove( String key );

    /**
     * @return 属性名列表
     */
    Iterable<String> keys();

    /**
     *
     * @param propertyKeys 属性名
     * @param <V> 属性值
     * @return 属性列表
     */
    default <V> Iterator<V> values(final String... propertyKeys){
        return IteratorUtils.map(this.<V>properties(propertyKeys), property -> property.value());
    }

    /**
     * @param propertyKeys 属性名
     * @param <V> 属性值
     * @return 属性值列表
     */
    <V> Iterator<? extends Property<V>> properties(final String... propertyKeys);

    /**
     * @return 属性变更支持
     */
    PropertyChangeSupport propertyChangeSupport();

    /**
     * @return 属性约束支持
     */
    VetoableChangeSupport vetoableChangeSupport();

    /**
     * @param listener 待添加属性变更监听器
     */
    default void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport().addPropertyChangeListener(listener);
    }

    /**
     * @param listener 待移除属性变更监听器
     */
    default void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport().removePropertyChangeListener(listener);
    }

    /**
     * @param listener 待添加属性约束监听器
     */
    default void addVetoableChangeListener(VetoableChangeListener listener) {
        vetoableChangeSupport().addVetoableChangeListener(listener);
    }


    /**
     * @param listener 待移除属性约束监听器
     */
    default void removeVetoableChangeListener(VetoableChangeListener listener) {
        vetoableChangeSupport().removeVetoableChangeListener(listener);
    }
}
