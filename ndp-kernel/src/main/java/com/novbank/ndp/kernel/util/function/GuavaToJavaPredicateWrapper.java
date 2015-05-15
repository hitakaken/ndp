package com.novbank.ndp.kernel.util.function;

import com.novbank.ndp.kernel.util.Wrapper;

/**
 * Created by hp on 2015/5/15.
 */
public class GuavaToJavaPredicateWrapper<T> implements java.util.function.Predicate<T>,Wrapper<com.google.common.base.Predicate<T>> {
    private com.google.common.base.Predicate<T> predicate;

    public GuavaToJavaPredicateWrapper(com.google.common.base.Predicate<T> predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean test(T input) {
        return predicate.apply(input);
    }

    @Override
    public com.google.common.base.Predicate<T> unwrap(){
        return predicate;
    }
}
