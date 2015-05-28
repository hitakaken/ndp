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
    default boolean beforeMonitor(Object source){
        return true;
    }

    /**
     * 监听后触发
     *
     * @param source
     * @return
     */
    default boolean afterMonitor(Object source){
        return true;
    }

    /**
     * 取消监听后触发
     *
     * @param source
     * @return
     */
    default boolean beforeLeave(Object source){
        return true;
    }

    /**
     * 取消监听前触发
     *
     * @param source
     * @return
     */
    default boolean afterLeave(Object source){
        return true;
    }
}
