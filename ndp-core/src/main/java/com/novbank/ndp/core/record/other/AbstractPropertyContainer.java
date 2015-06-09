package com.novbank.ndp.core.record.other;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeSupport;
import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * Created by CaoKe on 2015/6/1.
 */
public abstract class AbstractPropertyContainer implements PropertyContainer{
    //属性变更支持
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    //属性约束支持
    private VetoableChangeSupport vcs = new VetoableChangeSupport(this);

    protected void doSetProperty(String propertyName, Object oldValue, Object newValue, BiConsumer consumer) throws PropertyVetoException {
        if(Objects.equals(oldValue, newValue)) return;
        vcs.fireVetoableChange(propertyName,oldValue,newValue);
        consumer.accept(this, newValue);
        pcs.firePropertyChange(propertyName,oldValue,newValue);
    }

    @Override
    public PropertyChangeSupport propertyChangeSupport() {
        return pcs;
    }

    @Override
    public VetoableChangeSupport vetoableChangeSupport() {
        return vcs;
    }
}
