package com.novbank.ndp.core.util.function;

import com.novbank.ndp.core.util.Wrapper;

/**
 * Created by hp on 2015/5/15.
 */
public class JavaToGuavaSupplierWrapper<T> implements com.google.common.base.Supplier<T>,Wrapper<java.util.function.Supplier<T>> {
    private java.util.function.Supplier<T> supplier;

    public JavaToGuavaSupplierWrapper(java.util.function.Supplier<T> predicate) {
        this.supplier = supplier;
    }

    @Override
    public T get() {
        return supplier.get();
    }

    @Override
    public java.util.function.Supplier<T> unwrap() {
        return supplier;
    }
}
