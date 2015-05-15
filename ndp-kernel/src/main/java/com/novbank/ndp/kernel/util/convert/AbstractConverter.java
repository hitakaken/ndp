package com.novbank.ndp.kernel.util.convert;

import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by hp on 2015/5/15.
 */
public abstract class AbstractConverter<F,T> implements Converter<F,T> {
    private final boolean handleNullAutomatically;

    protected transient Converter<T, F> reverse;

    protected AbstractConverter() {
        this(true);
    }

    protected AbstractConverter(boolean handleNullAutomatically) {
        this.handleNullAutomatically = handleNullAutomatically;
    }

    protected abstract T doForward(F from);

    protected abstract F doBackward(T to);

    @Nullable
    T correctedDoForward(@Nullable F from) {
        if (handleNullAutomatically) {
            return from == null ? null : checkNotNull(doForward(from));
        } else {
            return doForward(from);
        }
    }

    @Nullable
    F correctedDoBackward(@Nullable T to) {
        if (handleNullAutomatically) {
            return to == null ? null : checkNotNull(doBackward(to));
        } else {
            return doBackward(to);
        }
    }

    @Override
    public T convert(F from) {
        return correctedDoForward(from);
    }

    @Override
    public F source(T to) {
        return correctedDoBackward(to);
    }

    @Override
    public Converter<T, F> reverse() {
        Converter<T, F> result = reverse;
        return (result == null) ? reverse = new ReverseConverter<T, F>(this) : result;
    }

    @Override
    public <B> Converter<B, T> composite(Converter<B,F> before) {
        return new CompositeConverter<B,F,T>(before,this);
    }

    @Override
    public <A> Converter<F, A> andThen(Converter<T, A> after) {
        return new CompositeConverter<F,T,A>(this,after);
    }
}
