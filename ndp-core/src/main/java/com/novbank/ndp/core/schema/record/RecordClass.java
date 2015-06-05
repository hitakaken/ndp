package com.novbank.ndp.core.schema.record;


import com.novbank.ndp.core.record.PropertyContainer;
import com.novbank.ndp.core.record.Record;
import com.novbank.ndp.core.schema.namespace.NamespaceSupport;
import com.novbank.ndp.core.schema.type.RecordType;
import javassist.util.proxy.MethodFilter;

/**
 * Created by CaoKe on 2015/6/1.
 */
public interface RecordClass extends PropertyContainer, MethodFilter, NamespaceSupport, RecordType {
    RecordSchema schema();

    <T> T newInstance() throws InstantiationException, IllegalAccessException;

    <T> T newInstance(Object... args) throws InstantiationException, IllegalAccessException;

    <T> T getProxyInstance(Record record);

    <T> T getProxyInstance(T proxyObject);

    <T> T getProxyInstance(T proxyObject, Record record);
}
