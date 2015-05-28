package com.novbank.ndp.kernel.core.enhancement;

import javassist.util.proxy.MethodHandler;

import java.lang.reflect.Method;

/**
 * Created by hp on 2015/5/28.
 */
public class PropertyMethodHandler implements MethodHandler {
    @Override
    public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
        return proceed.invoke(self, args);
    }
}
