package com.novbank.ndp.core.record;


import javassist.util.proxy.MethodFilter;

/**
 * Created by CaoKe on 2015/6/1.
 */
public interface RecordClass extends PropertyContainer, MethodFilter {
    <T> T newInstance() throws InstantiationException, IllegalAccessException;

    <T> T newInstance(Object... args) throws InstantiationException, IllegalAccessException;

    <T> T getProxyInstance(Record record);

    <T> T getProxyInstance(T proxyObject);

    <T> T getProxyInstance(T proxyObject, Record record);
}
