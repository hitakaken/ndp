package com.novbank.ndp.kernel.core.event;

/**
 * 属性监听器
 *
 * Created by ken on 15-5-27.
 */
public interface PropertyListener extends EventListener{
    /**
     *  属性改变前触发
     *
     * @param source
     * @param key
     * @param newValue
     * @param oldValue
     * @return
     */
    default boolean beforePropertyChange(PropertyContainer source, String key, Object newValue, Object oldValue){
        return true;
    }

    /**
     * 属性改变后触发
     *
     * @param source
     * @param key
     * @param newValue
     * @param oldValue
     * @return
     */
    default boolean afterPropertyChange(PropertyContainer source, String key, Object newValue, Object oldValue){
        return true;
    }
}
