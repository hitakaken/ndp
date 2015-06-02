package com.novbank.ndp.core.schema;

import com.novbank.ndp.core.schema.namespace.Namespace;
import com.novbank.ndp.core.schema.type.RecordType;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Function;

/**
 * Created by hp on 2015/6/2.
 */
public abstract class AbstractRecordProperty implements RecordProperty {
    protected Namespace namespace;
    protected String name;
    protected RecordClass owner;
    protected String display;
    protected String hint;
    protected RecordType type;
    protected Function getter;
    protected BiConsumer setter;
    protected BiPredicate validator;

    public AbstractRecordProperty(RecordClass owner, Namespace namespace, String name) {
        this.owner = owner;
        this.namespace = namespace;
        this.name = name;
    }

    @Override
    public Namespace getNamespace() {
        return namespace;
    }

    public void setNamespace(Namespace namespace) {
        this.namespace = namespace;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    @Override
    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    @Override
    public RecordType getType() {
        return type;
    }

    public void setType(RecordType type) {
        this.type = type;
    }

    @Override
    public Object getValue(Object thisObj) {
        return getter!=null?getter.apply(thisObj):null;
    }

    @Override
    public boolean validate(Object thisObj, Object newValue) {
        return validator == null || validator.test(thisObj,newValue);
    }

    @Override
    public void setValue(Object thisObj, Object newValue) {
        if(setter!=null)
            setter.accept(thisObj, newValue);
    }
}
