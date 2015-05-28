package com.novbank.ndp.kernel.core.event;

/**
 * Created by hp on 2015/5/28.
 */
public interface ChangeEventSource extends EventSource{
    /**
     * 处理变更
     */
    default void fireChangeEvent(){
        listeners().forEach(listener -> {
            if(listener instanceof ChangeEventListener)
                ((ChangeEventListener)listener).handleChangeEvent(this);
        });
    }
}
