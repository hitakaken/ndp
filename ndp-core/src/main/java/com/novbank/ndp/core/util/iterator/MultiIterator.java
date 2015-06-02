package com.novbank.ndp.core.util.iterator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by CaoKe on 2015/6/1.
 */
public class MultiIterator <T> implements Iterator<T>, Serializable {

    private final List<Iterator<T>> iterators = new ArrayList<>();
    private int current = 0;

    public void addIterator(final Iterator<T> iterator) {
        this.iterators.add(iterator);
    }

    @Override
    public boolean hasNext() {
        if (this.current >= this.iterators.size())
            return false;

        Iterator<T> currentIterator = iterators.get(this.current);

        while (true) {
            if (currentIterator.hasNext()) {
                return true;
            } else {
                this.current++;
                if (this.current >= iterators.size())
                    break;
                currentIterator = iterators.get(this.current);
            }
        }
        return false;
    }

    @Override
    public T next() {
        if (this.iterators.isEmpty()) throw new NoSuchElementException();

        Iterator<T> currentIterator = iterators.get(this.current);
        while (true) {
            if (currentIterator.hasNext()) {
                return currentIterator.next();
            } else {
                this.current++;
                if (this.current >= iterators.size())
                    break;
                currentIterator = iterators.get(current);
            }
        }
        throw new NoSuchElementException();
    }

    public void clear() {
        this.iterators.clear();
        this.current = 0;
    }
}
