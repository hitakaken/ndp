package com.novbank.ndp.kernel.core.event;

/**
 * Created by hp on 2015/5/28.
 */
public class ListenerWrapper {
    private ListenerWrapper() {
    }

    static class ChangeInLocationToChangeEventListener implements DelegateEventListener,ChangeEventListener{
        private ChangeInLocationEventListener delegate;
        private String location;
        public ChangeInLocationToChangeEventListener(ChangeInLocationEventListener delegate, String location) {
            this.delegate = delegate;
            this.location = location;
        }
        @Override
        public EventListener delegate() {
            return delegate;
        }
        @Override
        public void handleChangeEvent(Object source) {
            delegate.handleChangeEvent(source,location);
        }
    }

    public static ChangeEventListener wrap(ChangeInLocationEventListener delegate, String location){
        return new ChangeInLocationToChangeEventListener(delegate,location);
    }
}
