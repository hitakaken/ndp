package com.novbank.ndp.kernel.core.enhancement;

import com.novbank.ndp.kernel.core.record.Record;
import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.Proxy;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

import java.lang.reflect.Method;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by hp on 2015/5/28.
 */
public class PropertyContainerEnhancer {
    private PropertyContainerEnhancer() {
    }

    public static <T> T getProxiedInstance(final Class<T> pojoClass,
                                           Object enclosingInstance, final Record doc, final ProxyObject parent,  Object... iArgs){
        checkNotNull(pojoClass);
        boolean isInnerClass = pojoClass.getEnclosingClass() != null;
        Class<T> proxyClass = getOrCreateProxyClass(pojoClass);
        return null;
    }

    private static <T> Class<T> getOrCreateProxyClass(Class<T> pojoClass) {

        if (Proxy.class.isAssignableFrom(pojoClass)) {
            return pojoClass;
        } else {
            ProxyFactory factory = new ProxyFactory();
            factory.setSuperclass(pojoClass);
            PropertyMethodFilter filter = new PropertyMethodFilter();
            factory.setFilter(filter);
            Class<T> proxyClass = (Class<T> )factory.createClass();
            filter.initialize(proxyClass);
            return proxyClass;
        }
    }
}
