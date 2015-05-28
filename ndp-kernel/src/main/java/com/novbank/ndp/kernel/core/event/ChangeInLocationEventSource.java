package com.novbank.ndp.kernel.core.event;

/**
 * Created by hp on 2015/5/28.
 */
public interface ChangeInLocationEventSource extends EventSource{
    /**
     * 处理变更
     */
    default void fireChangeEvent(String location){
        listeners().forEach(listener -> {
            if(listener instanceof ChangeInLocationEventListener)
                ((ChangeInLocationEventListener)listener).handleChangeEvent(this,location);
        });
    }
}
