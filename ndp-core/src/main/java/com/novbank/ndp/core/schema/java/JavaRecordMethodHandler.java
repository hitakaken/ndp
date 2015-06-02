package com.novbank.ndp.core.schema.java;

import javassist.util.proxy.MethodHandler;

import java.lang.reflect.Method;

/**
 * Created by hp on 2015/6/2.
 */
public class JavaRecordMethodHandler implements MethodHandler {
    public JavaRecordMethodHandler(JavaRecordClass javaRecordClass) {

    }

    @Override
    public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
        return null;
    }
}
