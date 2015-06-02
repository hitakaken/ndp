package com.novbank.ndp.core.util.iterator;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by CaoKe on 2015/6/1.
 */
public class SingleIterator <T> implements Iterator<T>,Serializable {

    private final T t;
    private boolean alive = true;

    protected SingleIterator(final T t) {
        this.t = t;
    }

    @Override
    public boolean hasNext() {
        return this.alive;
    }

    @Override
    public T next() {
        if (!this.alive)
            throw new NoSuchElementException();
        else {
            this.alive = false;
            return t;
        }
    }
}
