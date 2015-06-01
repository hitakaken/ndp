package com.novbank.ndp.core.helper.reflect;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hp on 2015/6/1.
 */
public class ProxyPropertyClass<T> {
    private Class<T> pojoClass;
    private Class<T> proxyClass;

    public ProxyPropertyClass(Class pojoClass) {
        this.pojoClass = pojoClass;
        initialize();
    }

    private void initialize(){
        Map<String,Method> getters = new HashMap<>();
        Map<String,Method> setters = new HashMap<>();
        try {
            for (PropertyDescriptor pd : Introspector.getBeanInfo(pojoClass).getPropertyDescriptors()) {
                if (pd.getReadMethod() != null && !"class".equals(pd.getName()))
                    getters.put(pd.getName(), pd.getReadMethod());
                if (pd.getWriteMethod() != null && !"class".equals(pd.getName()))
                    setters.put(pd.getName(), pd.getWriteMethod());
            }
        } catch (IntrospectionException e) {
            //ignore;
        }
    }

    public T newInstance(){
        try {
            return proxyClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public T newInstance(Object... args){
        try {
            return proxyClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
