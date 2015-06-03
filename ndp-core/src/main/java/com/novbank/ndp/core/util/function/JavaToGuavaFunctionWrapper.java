package com.novbank.ndp.core.util.function;

import com.novbank.ndp.core.util.Wrapper;

/**
 * Created by hp on 2015/5/15.
 */
public class JavaToGuavaFunctionWrapper<F,T> implements com.google.common.base.Function<F,T>,Wrapper<java.util.function.Function<F,T>> {
    private java.util.function.Function<F,T> function;

    public JavaToGuavaFunctionWrapper(java.util.function.Function<F,T> function) {
        this.function = function;
    }

    @Override
    public T apply(F input) {
        return function.apply(input);
    }

    @Override
    public java.util.function.Function<F,T> unwrap() {
        return function;
    }
}
