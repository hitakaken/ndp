package com.novbank.ndp.kernel.core.enhancement;

import javassist.util.proxy.MethodFilter;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hp on 2015/5/28.
 */
public class PropertyMethodFilter implements MethodFilter {
    Map<String,Method> getters = new HashMap<>();
    Map<String,Method> setters = new HashMap<>();

    public PropertyMethodFilter(){  }

    public void initialize(Class clazz){
        try {
            for (PropertyDescriptor pd : Introspector.getBeanInfo(clazz).getPropertyDescriptors()) {
                if (pd.getReadMethod() != null && !"class".equals(pd.getName()))
                    getters.put(pd.getName(), pd.getReadMethod());
                if (pd.getWriteMethod() != null && !"class".equals(pd.getName()))
                    setters.put(pd.getName(), pd.getWriteMethod());
            }
        } catch (IntrospectionException e) {
            //ignore;
        }
    }
    @Override
    public boolean isHandled(Method m) {
        return getters.containsKey(m) || setters.containsKey(m);
    }
}
