package com.novbank.ndp.kernel.core.event;

import com.novbank.ndp.kernel.core.exception.PropertyNameInvalidException;

import java.util.Collection;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 属性容器
 *
 * Created by ken on 15-5-27.
 */
public interface PropertyContainer extends EventSource{
    /**
     * @return 属性列表
     */
    Collection<String> propertyNames();

    /**
     * @param key 属性名
     * @return 属性值
     */
    Object getProperty(String key);

    /**
     * 属性名是否合法
     * @param key
     * @return
     */
    boolean isValidPropertyName(String key);

    /**
     * @param key 属性名
     * @param newValue 新属性值
     */
    default void setProperty(String key, Object newValue) throws PropertyNameInvalidException{
        checkNotNull(key);
        if(isValidPropertyName(key))
            throw new PropertyNameInvalidException();

        Object oldValue = getProperty(key);

        if(fireBeforePropertyChange(key, oldValue, newValue)) {
            doSetProperty(key, newValue);
            fireAfterPropertyChange(key,oldValue, getProperty(key));
            fireChangeEvent();
        }
    }

    /**
     * @param key 属性名
     * @param newValue 新属性值
     */
    void doSetProperty(String key, Object newValue);

    /**
     * 属性改变前触发
     *
     * @param key
     * @param oldValue
     * @param newValue
     * @return
     */
    default boolean fireBeforePropertyChange(String key, Object oldValue, Object newValue){
        final boolean[] result = {true};
        listeners().stream()
                .filter(listener -> listener instanceof PropertyListener)
                .forEach(listener ->
                        result[0] = result[0]
                                && ((PropertyListener) listener)
                                .beforePropertyChange(this, key, oldValue, newValue));
        return result[0];
    }

    /**
     * 属性改变后触发
     *
     * @param key
     * @param oldValue
     * @param newValue
     * @return
     */
    default boolean fireAfterPropertyChange(String key, Object oldValue,  Object newValue){
        final boolean[] result = {true};
        listeners().stream()
                .filter(listener -> listener instanceof PropertyListener)
                .forEach(listener ->
                        result[0] = result[0]
                                && ((PropertyListener) listener)
                                .afterPropertyChange(this, key, oldValue, newValue));
        return result[0];
    }
}
