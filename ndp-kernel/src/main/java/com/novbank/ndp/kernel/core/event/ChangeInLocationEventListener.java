package com.novbank.ndp.kernel.core.event;

/**
 * 变更位置监听器
 *
 * Created by hp on 2015/5/28.
 */
public interface ChangeInLocationEventListener extends EventListener{
    /**
     * 处理变更
     *
     * @param source 事件源
     * @param location 变更位置
     */
    default void handleChangeEvent(Object source, String location){ }
}
