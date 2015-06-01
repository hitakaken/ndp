package com.novbank.ndp.core.helper.reflect;

import com.novbank.ndp.core.record.PropertyContainer;
import javassist.util.proxy.ProxyFactory;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by hp on 2015/6/1.
 */
public class PropertyContainerHelper {
    private PropertyContainerHelper() {
    }

    public Class getProxiedClass(Class<?> pojoClass){
        checkNotNull(pojoClass);
        if (PropertyContainer.class.isAssignableFrom(pojoClass))
            return pojoClass;
        ProxyFactory f = new ProxyFactory();
        f.setSuperclass(pojoClass);
        f.setInterfaces(new Class[]{PropertyContainer.class});

        return f.createClass();
    }
}
