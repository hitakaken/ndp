package com.novbank.ndp.kernel.util.convert;


/**
 * Created by hp on 2015/5/15.
 */
public class GuavaConverter<F,T> extends AbstractConverter<F,T>{
    protected com.google.common.base.Converter<F,T> delegate;

    public GuavaConverter(com.google.common.base.Converter<F, T> delegate) {
        this.delegate = delegate;
    }

    @Override
    protected T doForward(F from) {
        return delegate.convert(from);
    }

    @Override
    protected F doBackward(T to) {
        return delegate.reverse().convert(to);
    }

    @Override
    public Converter<T, F> reverse() {
        Converter<T, F> result = reverse;
        return (result == null) ? reverse = new GuavaConverter<>(delegate.reverse()) : result;
    }
}
