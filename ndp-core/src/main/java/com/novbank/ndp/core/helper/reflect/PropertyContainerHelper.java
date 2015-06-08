package com.novbank.ndp.core.helper.reflect;

import com.google.common.collect.Maps;
import com.novbank.ndp.core.record.other.PropertyContainer;

import java.lang.reflect.Method;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by hp on 2015/6/1.
 */
public class PropertyContainerHelper {
    private PropertyContainerHelper() {
    }

    private static Map<String, Method> PROPERTY_CONTAINER_METHODS;

    public static Map<String, Method> getPropertyContainerMethods(){
        if(PROPERTY_CONTAINER_METHODS!=null)
            return PROPERTY_CONTAINER_METHODS;
        PROPERTY_CONTAINER_METHODS = Maps.newHashMap();
        for(Method method:PropertyContainer.class.getDeclaredMethods())
            PROPERTY_CONTAINER_METHODS.put(method.toString(),method);
        return PROPERTY_CONTAINER_METHODS;
    }

    public static boolean isPropertyContainerMethod(Method method){
        return method!=null && getPropertyContainerMethods().containsKey(method.toString());
    }

    public static boolean keyExists(final String key, final String... providedKeys) {
        if (0 == providedKeys.length) return true;
        if (1 == providedKeys.length) return key.equals(providedKeys[0]);
        else {
            for (final String temp : providedKeys) {
                if (temp.equals(key))
                    return true;
            }
            return false;
        }
    }
}
