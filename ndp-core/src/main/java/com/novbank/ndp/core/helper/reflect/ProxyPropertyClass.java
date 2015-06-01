package com.novbank.ndp.core.helper.reflect;

/**
 * Created by hp on 2015/6/1.
 */
public class ProxyPropertyClass<T> {
    private Class<T> pojoClass;
    private Class<T> proxyClass;

    public ProxyPropertyClass(Class pojoClass) {
        this.pojoClass = pojoClass;
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
