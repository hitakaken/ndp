package com.novbank.ndp.kernel.util.convert;

/**
 * Created by hp on 2015/5/15.
 */
public interface Converter<F, T> {
    T convert(F from);

    @Deprecated
    F source(T to);

    Converter<T,F> reverse();

    <B> Converter<B,T> composite(Converter<B,F> before);

    <A> Converter<F,A> andThen(Converter<T,A> after);
}
