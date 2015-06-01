package com.novbank.ndp.core.record;

import com.google.common.collect.Maps;
import com.novbank.ndp.core.helper.reflect.PropertyContainerHelper;
import javassist.util.proxy.ProxyFactory;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by CaoKe on 2015/6/1.
 */
public class JavaRecordClass extends AbstractRecordClass{
    private Class pojoClass;
    private Map<String,String> getterSignatures;
    private Map<String,String> setterSignatures;
    private Map<String,Method> getters;
    private Map<String,Method> setters;
    private Class proxyClass;

    public JavaRecordClass(RecordSchema schema, Class pojoClass){
        super(schema);
        this.pojoClass = pojoClass;
        initialize();
    }

    public void initialize(){
        getterSignatures = Maps.newHashMap();
        setterSignatures = Maps.newHashMap();
        getters = Maps.newHashMap();
        setters = Maps.newHashMap();
        try {
            for (PropertyDescriptor pd : Introspector.getBeanInfo(pojoClass).getPropertyDescriptors()) {
                if (pd.getReadMethod() != null && !"class".equals(pd.getName())){
                    getterSignatures.put(pd.getReadMethod().toString(),pd.getName());
                    getters.put(pd.getName(), pd.getReadMethod());
                }
                if (pd.getWriteMethod() != null && !"class".equals(pd.getName())){
                    setterSignatures.put(pd.getWriteMethod().toString(), pd.getName());
                    setters.put(pd.getName(), pd.getWriteMethod());
                }

            }
        } catch (IntrospectionException e) {
            //ignore;
        }
        ProxyFactory factory = new ProxyFactory();
        factory.setSuperclass(pojoClass);
        factory.setInterfaces(new Class[]{PropertyContainer.class});
        factory.setFilter(this);
        proxyClass = factory.createClass();
    }


    public boolean isGetter(Method method){
        return method!=null && getters.containsKey(method.toString());
    }

    public boolean isSetter(Method method){
        return method!=null && setters.containsKey(method.toString());
    }

    @Override
    public boolean isHandled(Method m) {
        return PropertyContainerHelper.isPropertyContainerMethod(m) || isGetter(m) || isSetter(m);
    }

    @Override
    public <V> RecordProperty<V> of(V value) {
        return null;
    }

    public MethodHandle getMethodHandler(){
        return null;
    }

    @Override
    public <T> T newInstance() throws InstantiationException, IllegalAccessException {

        return null;
    }

    @Override
    public <T> T newInstance(Object... args) throws InstantiationException, IllegalAccessException {
        return null;
    }

    @Override
    public <T> T getProxyInstance(Record record) {
        return null;
    }

    @Override
    public <T> T getProxyInstance(T proxyObject) {
        return null;
    }

    @Override
    public <T> T getProxyInstance(T proxyObject, Record record) {
        return null;
    }


}
