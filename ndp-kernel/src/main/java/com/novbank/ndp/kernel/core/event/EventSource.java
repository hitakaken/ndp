package com.novbank.ndp.kernel.core.event;

import java.util.Collection;

/**
 * Created by ken on 15-5-27.
 */
public interface EventSource {
    /**
     * @return 属性监听器列表
     */
    Collection<EventListener> listeners();

    /**
     * 添加监听器
     *
     * @param listener
     */
    default void addListener(EventListener listener){
        if(listener.beforeMonitor(this)){
            listeners().add(listener);
            listener.afterMonitor(this);
        }
    }

    /**
     * 添加监听器
     *
     * @param listeners
     */
    default void addListeners(Collection<EventListener> listeners){
        listeners.forEach(this::addListener);
    }

    /**
     * 删除监听器
     *
     * @param listener
     * @return
     */
    default void removeListener(EventListener listener){
        if (listeners().contains(listener) && listener.beforeLeave(this)){
            listeners().remove(listener);
            listener.afterLeave(this);
        }
    }

    /**
     * 删除监听器
     *
     * @param listeners
     * @return
     */
    default void removeListeners(Collection<EventListener> listeners){
        listeners.forEach(this::removeListener);
    }

    /**
     * 处理变更
     */
    default void fireChangeEvent(){
        listeners().forEach(listener -> listener.handleChangeEvent(this));
    }
}
