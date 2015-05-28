package com.novbank.ndp.kernel.core.event;

/**
 * 变更监听器
 *
 * Created by hp on 2015/5/28.
 */
public interface ChangeEventListener extends EventListener {
    /**
     * 处理变更事件
     *
     * @param source
     */
    default void handleChangeEvent(Object source){}
}
