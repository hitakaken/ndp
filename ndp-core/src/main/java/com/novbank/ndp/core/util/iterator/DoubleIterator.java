package com.novbank.ndp.core.util.iterator;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by CaoKe on 2015/6/1.
 */
public class DoubleIterator <T> implements Iterator<T>, Serializable {

    private final T a;
    private final T b;
    private char current = 'a';

    protected DoubleIterator(final T a, final T b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean hasNext() {
        return this.current != 'x';
    }

    @Override
    public T next() {
        if (this.current == 'x')
            throw new NoSuchElementException();
        else {
            if (this.current == 'a') {
                this.current = 'b';
                return this.a;
            } else {
                this.current = 'x';
                return this.b;
            }
        }
    }
}
