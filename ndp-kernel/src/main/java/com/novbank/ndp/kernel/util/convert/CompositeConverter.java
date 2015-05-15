package com.novbank.ndp.kernel.util.convert;

/**
 * Created by hp on 2015/5/15.
 */
public class CompositeConverter<F, M, T> extends AbstractConverter<F,T>{
    final Converter<F, M> first;
    final Converter<M, T> second;

    public CompositeConverter(Converter<F, M> first, Converter<M, T> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    protected T doForward(F from) {
        throw new AssertionError();
    }

    @Override
    protected F doBackward(T t) {
        throw new AssertionError();
    }

    @Override
    public T convert(F from) {
        return second.convert(first.convert(from));
    }

    @Override
    public F source(T to) {
        return first.source(second.source(to));
    }
}
