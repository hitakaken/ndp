package com.novbank.ndp.core.util.function;


import com.novbank.ndp.core.util.Wrapper;

/**
 * Created by hp on 2015/5/15.
 */
public class GuavaToJavaSupplierWrapper<T> implements java.util.function.Supplier<T>,Wrapper<com.google.common.base.Supplier<T>> {
    private com.google.common.base.Supplier<T> supplier;

    public GuavaToJavaSupplierWrapper(com.google.common.base.Supplier<T> supplier) {
        this.supplier = supplier;
    }

    @Override
    public T get() {
        return supplier.get();
    }

    @Override
    public com.google.common.base.Supplier<T> unwrap(){
        return supplier;
    }
}
