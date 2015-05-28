package com.novbank.ndp.kernel.core.event;

/**
 * Created by hp on 2015/5/28.
 */
public interface DelegateEventListener extends EventListener{
    EventListener delegate();

    @Override
    default boolean beforeMonitor(Object source) {
        return delegate().beforeMonitor(source);
    }

    @Override
    default boolean afterMonitor(Object source) {
        return delegate().afterMonitor(source);
    }

    @Override
    default boolean beforeLeave(Object source) {
        return delegate().beforeLeave(source);
    }

    @Override
    default boolean afterLeave(Object source) {
        return delegate().afterLeave(source);
    }
}
