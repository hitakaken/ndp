package com.novbank.ndp.core.schema.java;

import com.novbank.ndp.core.schema.AbstractRecordProperty;
import com.novbank.ndp.core.schema.namespace.Namespace;
import com.novbank.ndp.core.schema.RecordClass;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by hp on 2015/6/2.
 */
public class JavaRecordProperty extends AbstractRecordProperty{
    protected Field field;
    protected Method getter;
    protected Method setter;
    protected Set annotations;
    public JavaRecordProperty(RecordClass recordClass, Namespace namespace, String name,
                              Field field, Method getter, Method setter, Set annotations, Class<?> propertyType) {
        super(recordClass,namespace ,name);
        this.field = field;
        this.getter = getter;
        this.setter = setter;
        this.annotations = annotations;
    }
}
