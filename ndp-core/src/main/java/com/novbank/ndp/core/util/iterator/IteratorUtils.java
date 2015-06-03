package com.novbank.ndp.core.util.iterator;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by CaoKe on 2015/6/1.
 */
public class IteratorUtils {
    private IteratorUtils() {
    }

    public static final <S> Iterator<S> of(final S a) {
        return new SingleIterator<>(a);
    }

    public static final <S> Iterator<S> of(final S a, S b) {
        return new DoubleIterator<>(a, b);
    }

    ///////////////

    public static final <S extends Collection<T>, T> S fill(final Iterator<T> iterator, final S collection) {
        while (iterator.hasNext()) {
            collection.add(iterator.next());
        }
        return collection;
    }

    public static final long count(final Iterator iterator) {
        long ix = 0;
        for (; iterator.hasNext(); ++ix) iterator.next();
        return ix;
    }

    public static <S> List<S> list(final Iterator<S> iterator) {
        return fill(iterator, new ArrayList<>());
    }

    ///////////////////

    public static <T> boolean allMatch(final Iterator<T> iterator, final Predicate<T> predicate) {
        while (iterator.hasNext()) {
            if (!predicate.test(iterator.next())) {
                return false;
            }
        }

        return true;
    }

    public static <T> boolean anyMatch(final Iterator<T> iterator, final Predicate<T> predicate) {
        while (iterator.hasNext()) {
            if (predicate.test(iterator.next())) {
                return true;
            }
        }

        return false;
    }

    public static <T> boolean noneMatch(final Iterator<T> iterator, final Predicate<T> predicate) {
        while (iterator.hasNext()) {
            if (predicate.test(iterator.next())) {
                return false;
            }
        }

        return true;
    }

    ///////////////////

    public static <K, S> Map<K, S> collectMap(final Iterator<S> iterator, final Function<S, K> key) {
        return collectMap(iterator, key, Function.identity());
    }

    public static <K, S, V> Map<K, V> collectMap(final Iterator<S> iterator, final Function<S, K> key, final Function<S, V> value) {
        final Map<K, V> map = new HashMap<>();
        while (iterator.hasNext()) {
            final S obj = iterator.next();
            map.put(key.apply(obj), value.apply(obj));
        }
        return map;
    }

    public static <K, S> Map<K, List<S>> groupBy(final Iterator<S> iterator, final Function<S, K> groupBy) {
        final Map<K, List<S>> map = new HashMap<>();
        while (iterator.hasNext()) {
            final S obj = iterator.next();
            map.computeIfAbsent(groupBy.apply(obj), k -> new ArrayList<>()).add(obj);
        }
        return map;
    }

    public static <S> S reduce(final Iterator<S> iterator, final S identity, final BinaryOperator<S> accumulator) {
        S result = identity;
        while (iterator.hasNext()) {
            result = accumulator.apply(result, iterator.next());
        }

        return result;
    }

    public static <S> S reduce(final Iterable<S> iterable, final S identity, final BinaryOperator<S> accumulator) {
        return reduce(iterable.iterator(), identity, accumulator);
    }

    public static <S, E> E reduce(final Iterator<S> iterator, final E identity, final BiFunction<E, S, E> accumulator) {
        E result = identity;
        while (iterator.hasNext()) {
            result = accumulator.apply(result, iterator.next());
        }

        return result;
    }

    public static <S, E> E reduce(final Iterable<S> iterable, final E identity, final BiFunction<E, S, E> accumulator) {
        return reduce(iterable.iterator(), identity, accumulator);
    }

    ///////////////

    public static final <S, E> Iterator<E> map(final Iterator<S> iterator, final Function<S, E> function) {
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public E next() {
                return function.apply(iterator.next());
            }
        };
    }

    public static final <S, E> Iterable<E> map(final Iterable<S> iterable, final Function<S, E> function) {
        return new Iterable<E>() {
            @Override
            public Iterator<E> iterator() {
                return IteratorUtils.map(iterable.iterator(), function);
            }
        };
    }

    ///////////////

    public static final <S> Iterator<S> filter(final Iterator<S> iterator, final Predicate<S> predicate) {


        return new Iterator<S>() {
            S nextResult = null;

            @Override
            public boolean hasNext() {
                if (null != this.nextResult) {
                    return true;
                } else {
                    advance();
                    return null != this.nextResult;
                }
            }

            @Override
            public S next() {
                try {
                    if (null != this.nextResult) {
                        return this.nextResult;
                    } else {
                        advance();
                        if (null != this.nextResult)
                            return this.nextResult;
                        else
                            throw new NoSuchElementException();
                    }
                } finally {
                    this.nextResult = null;
                }
            }

            private final void advance() {
                this.nextResult = null;
                while (iterator.hasNext()) {
                    final S s = iterator.next();
                    if (predicate.test(s)) {
                        this.nextResult = s;
                        return;
                    }
                }
            }
        };
    }

    public static final <S> Iterable<S> filter(final Iterable<S> iterable, final Predicate<S> predicate) {
        return new Iterable<S>() {
            @Override
            public Iterator<S> iterator() {
                return IteratorUtils.filter(iterable.iterator(), predicate);
            }
        };
    }

    ///////////////////

    public static final <S, E> Iterator<E> flatMap(final Iterator<S> iterator, final Function<S, Iterator<E>> function) {
        return new Iterator<E>() {

            private Iterator<E> currentIterator = Collections.emptyIterator();

            @Override
            public boolean hasNext() {
                if (this.currentIterator.hasNext())
                    return true;
                else {
                    while (iterator.hasNext()) {
                        this.currentIterator = function.apply(iterator.next());
                        if (this.currentIterator.hasNext())
                            return true;
                    }
                }
                return false;
            }

            @Override
            public E next() {
                if (this.hasNext())
                    return this.currentIterator.next();
                else
                    throw new NoSuchElementException();
            }
        };
    }


    ///////////////////

    public static final <S> Iterator<S> concat(final Iterator<S>... iterators) {
        final MultiIterator<S> iterator = new MultiIterator<>();
        for (final Iterator<S> itty : iterators) {
            iterator.addIterator(itty);
        }
        return iterator;
    }

    ///////////////////

    public static Iterator asIterator(final Object o) {
        final Iterator itty;
        if (o instanceof Iterable)
            itty = ((Iterable) o).iterator();
        else if (o instanceof Iterator)
            itty = (Iterator) o;
        else if (o instanceof Object[])
            itty = new ArrayIterator<>((Object[]) o);
        else if (o instanceof Stream)
            itty = ((Stream) o).iterator();
        else if (o instanceof Map)
            itty = ((Map) o).entrySet().iterator();
        else if (o instanceof Throwable)
            itty = of(((Throwable) o).getMessage());
        else
            itty = of(o);
        return itty;
    }

    public static List asList(final Object o) {
        return list(asIterator(o));
    }
}
