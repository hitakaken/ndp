package com.novbank.ndp.kernel.util.convert;


import java.util.function.Function;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by hp on 2015/5/15.
 */
public class FunctionBasedConverter<F,T> extends AbstractConverter<F,T>{
    private final Function<? super F, ? extends T> forwardFunction;
    private final Function<? super T, ? extends F> backwardFunction;

    public FunctionBasedConverter(Function<? super F, ? extends T> forwardFunction,
                                  Function<? super T, ? extends F> backwardFunction) {
        this.forwardFunction = checkNotNull(forwardFunction);
        this.backwardFunction = checkNotNull(backwardFunction);
    }

    @Override
    protected T doForward(F from) {
        return forwardFunction.apply(from);
    }

    @Override
    protected F doBackward(T to) {
        return backwardFunction.apply(to);
    }
}
