package com.novbank.ndp.kernel.core.record;

import java.beans.*;
import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * Created by ken on 15-5-28.
 */
public abstract class AbstractPropertyContainer {
    //属性变更支持
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    //属性约束支持
    private VetoableChangeSupport vcs = new VetoableChangeSupport(this);

    protected void doSetProperty(String propertyName, Object oldValue, Object newValue, BiConsumer consumer) throws PropertyVetoException{
        if(Objects.equals(oldValue,newValue)) return;
        vcs.fireVetoableChange(propertyName,oldValue,newValue);
        consumer.accept(this, newValue);
        pcs.firePropertyChange(propertyName,oldValue,newValue);
    }

    /**
     * 添加属性变更监听器
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    /**
     * 移除属性变更监听器
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    /**
     * 添加属性约束监听器
     *
     * @param listener
     */
    public void addVetoableChangeListener(VetoableChangeListener listener) {
        vcs.addVetoableChangeListener(listener);
    }


    /**
     * 移除属性约束监听器
     *
     * @param listener
     */
    public void removeVetoableChangeListener(VetoableChangeListener listener) {
        vcs.removeVetoableChangeListener(listener);
    }
}
