package com.novbank.ndp.core.util;

import com.novbank.ndp.core.annotation.NameSpace;

import java.util.function.Function;

/**
 * Created by hp on 2015/6/2.
 */
public class NamespaceUtils {
    public static NameSpace getAnnotation(Class pojoClass){
        return pojoClass.getAnnotation(NameSpace.class)!=null? (NameSpace) pojoClass.getAnnotation(NameSpace.class) :
                (pojoClass.getPackage().getAnnotation(NameSpace.class)!=null? (NameSpace) pojoClass.getPackage().getAnnotation(NameSpace.class):null);
    }

    public static final Function<Class,NameSpace> CLASS_GET_NAMESPACE_ANNOTATION_FUNCTION = NamespaceUtils::getAnnotation;

    public static final Function<Class,String> CLASS_GET_NAMESPACE_STRING_FUNCTION = c -> c.getPackage().getName();

}
