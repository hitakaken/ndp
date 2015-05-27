package com.novbank.ndp.kernel.core.event;

/**
 * Created by ken on 15-5-27.
 */
public interface EventListener {
    /**
     * 监听前触发
     *
     * @param source
     * @return 监听器是否合适
     */
    default boolean beforeMonitor(EventSource source){
        return true;
    }

    /**
     * 监听后触发
     *
     * @param source
     * @return
     */
    default boolean afterMonitor(EventSource source){
        return true;
    }

    /**
     * 取消监听后触发
     *
     * @param source
     * @return
     */
    default boolean beforeLeave(EventSource source){
        return true;
    }

    /**
     * 取消监听前触发
     *
     * @param source
     * @return
     */
    default boolean afterLeave(EventSource source){
        return true;
    }

    /**
     * 处理变更事件
     *
     * @param eventSource
     */
    default void handleChangeEvent(EventSource eventSource){}
}
