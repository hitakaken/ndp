package com.novbank.ndp.kernel.util.function;

import com.novbank.ndp.kernel.util.Wrapper;

/**
 * Created by hp on 2015/5/15.
 */
public class JavaToGuavaPredicateWrapper<T> implements com.google.common.base.Predicate<T>,Wrapper<java.util.function.Predicate<T>> {
    private java.util.function.Predicate<T> predicate;

    public JavaToGuavaPredicateWrapper(java.util.function.Predicate<T> predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean apply(T input) {
        return predicate.test(input);
    }

    @Override
    public java.util.function.Predicate<T> unwrap() {
        return predicate;
    }
}
