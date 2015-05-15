package com.novbank.ndp.kernel.util.convert;

/**
 * Created by hp on 2015/5/15.
 */
public class ReverseConverter<F,T> extends AbstractConverter<F,T> {
    final Converter<T, F> original;

    ReverseConverter(Converter<T, F> original) {
        this.original = original;
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
        return original.source(from);
    }

    @Override
    public F source(T to) {
        return original.convert(to);
    }

    @Override
    public Converter<T, F> reverse() {
        return original;
    }
}
