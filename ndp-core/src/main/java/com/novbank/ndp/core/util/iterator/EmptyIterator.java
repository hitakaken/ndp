package com.novbank.ndp.core.util.iterator;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by CaoKe on 2015/6/1.
 */
public class EmptyIterator<S> implements Iterator<S>, Serializable {

    private static final EmptyIterator INSTANCE = new EmptyIterator<>();

    private EmptyIterator() {
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public S next() {
        throw new NoSuchElementException();
    }

    public static <S> Iterator<S> instance() {
        return INSTANCE;
    }
}
