package com.novbank.ndp.core.schema.java;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.novbank.ndp.core.Constants;
import com.novbank.ndp.core.annotation.NameSpace;
import com.novbank.ndp.core.helper.reflect.PropertyContainerHelper;
import com.novbank.ndp.core.record.PropertyContainer;
import com.novbank.ndp.core.record.Record;
import com.novbank.ndp.core.schema.*;
import com.novbank.ndp.core.schema.namespace.Namespace;
import com.novbank.ndp.core.schema.namespace.NamespaceContainer;
import com.novbank.ndp.core.schema.namespace.SimpleNamespaceContainer;
import com.novbank.ndp.core.util.NamespaceUtils;
import javassist.util.proxy.Proxy;
import javassist.util.proxy.ProxyFactory;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.invoke.MethodHandle;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Created by CaoKe on 2015/6/1.
 */
public class JavaRecordClass extends AbstractRecordClass {
    private Class pojoClass;
    private Map<String,String> getterSignatures;
    private Map<String,String> setterSignatures;
    private Class proxyClass;

    public JavaRecordClass(RecordSchema schema, Class pojoClass){
        super(schema);
        this.pojoClass = pojoClass;
        initialize();
    }

    public void initialize(){
        //1.确定命名空间
        String namespaceString = pojoClass.getPackage().getName();
        NamespaceContainer namespaces  = schema!=null?schema:new SimpleNamespaceContainer();
        Namespace namespace = namespaces.getOrCreateNamespace(pojoClass,
                NamespaceUtils.CLASS_GET_NAMESPACE_STRING_FUNCTION, NamespaceUtils.CLASS_GET_NAMESPACE_ANNOTATION_FUNCTION);
        setNamespace(namespace);
        //2. 确定类名
        setName(pojoClass.getSimpleName());
        //3. 遍历属性
        getterSignatures = Maps.newHashMap();
        setterSignatures = Maps.newHashMap();
        try {
            for (PropertyDescriptor pd : Introspector.getBeanInfo(pojoClass).getPropertyDescriptors()) {
                if("class".equals(pd.getName())) continue;
                Set annotations = Sets.newHashSet();
                Field field = null;
                Method getter = null,setter = null;
                //获取 Field
                try {
                    field = pojoClass.getField(Constants.isJavaKeyword(pd.getName())?"_"+pd.getName():pd.getName());
                    if(Modifier.isTransient(field.getModifiers())){
                        field = null;
                    }else
                        annotations.addAll(Arrays.asList(field.getAnnotations()));
                } catch (NoSuchFieldException e) {
                    //ignore
                }
                //获取 Getter Method
                if(pd.getReadMethod()!=null && !Modifier.isTransient(pd.getReadMethod().getModifiers())){
                    getter = pd.getReadMethod();
                    annotations.addAll(Arrays.asList(getter.getAnnotations()));
                }
                //获取 Setter Method
                if(pd.getWriteMethod()!=null && !Modifier.isTransient(pd.getWriteMethod().getModifiers())){
                    setter = pd.getWriteMethod();
                    annotations.addAll(Arrays.asList(setter.getAnnotations()));
                }
                //判断是否为 Transient 属性
                if(annotations.stream().filter(a -> a.getClass().getCanonicalName().endsWith(".Transient") ).count()>0)
                    continue;
                if(getter!=null) getterSignatures.put(getter.toString(),pd.getName());
                if(setter!=null) setterSignatures.put(setter.toString(),pd.getName());
                //获取命名空间
                Optional optional = annotations.stream().filter(a -> a.getClass().equals(NameSpace.class)).findFirst();
                Namespace propertyNamespace = optional.isPresent() ? namespaces.getNamespace((NameSpace)optional.get()) : namespace;
                property(pd.getName(),
                        new JavaRecordProperty(this, propertyNamespace, pd.getName(), field, getter, setter, annotations, pd.getPropertyType()));
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
        return method!=null && getterSignatures.containsKey(method.toString());
    }

    public boolean isSetter(Method method){
        return method!=null && setterSignatures.containsKey(method.toString());
    }

    @Override
    public boolean isHandled(Method m) {
        return PropertyContainerHelper.isPropertyContainerMethod(m) || isGetter(m) || isSetter(m);
    }

    public MethodHandle getMethodHandler(){
        return null;
    }

    @Override
    public <T> T newInstance() throws InstantiationException, IllegalAccessException {
        T proxy = (T) proxyClass.newInstance();
        ((Proxy)proxy).setHandler(new JavaRecordMethodHandler(this));
        return proxy;
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
