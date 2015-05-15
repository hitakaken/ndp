package com.novbank.ndp.kernel.util.function;

import com.novbank.ndp.kernel.util.Wrapper;

/**
 * Created by hp on 2015/5/15.
 */
public class GuavaToJavaFunctionWrapper<F,T> implements java.util.function.Function<F,T>,Wrapper<com.google.common.base.Function<F,T>> {
    private com.google.common.base.Function<F,T> function;

    public GuavaToJavaFunctionWrapper(com.google.common.base.Function<F,T> function) {
        this.function = function;
    }

    @Override
    public T apply(F input) {
        return function.apply(input);
    }

    @Override
    public com.google.common.base.Function<F,T> unwrap(){
        return function;
    }
}
