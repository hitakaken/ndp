package com.novbank.ndp.kernel.util.stream;

import com.google.common.collect.ForwardingObject;
import com.google.common.collect.Iterators;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Created by hp on 2015/5/14.
 */
public abstract class ForwardingStream<T> extends ForwardingObject implements Stream<T> {
    public ForwardingStream() {}

    @Override
    protected abstract  Stream<T> delegate();

    protected abstract ForwardingStream<T> withThisContext(Stream<T> newStream);

    @Override
    public Stream<T> filter(Predicate<? super T> predicate) {
        return withThisContext(delegate().filter(predicate));
    }

    @Override
    public <R> Stream<R> map(Function<? super T, ? extends R> mapper) {
        return delegate().map(mapper);
    }

    @Override
    public IntStream mapToInt(ToIntFunction<? super T> mapper) {
        return delegate().mapToInt(mapper);
    }

    @Override
    public LongStream mapToLong(ToLongFunction<? super T> mapper) {
        return delegate().mapToLong(mapper);
    }

    @Override
    public DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper) {
        return delegate().mapToDouble(mapper);
    }

    @Override
    public <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) {
        return delegate().flatMap(mapper);
    }

    @Override
    public IntStream flatMapToInt(Function<? super T, ? extends IntStream> mapper) {
        return delegate().flatMapToInt(mapper);
    }

    @Override
    public LongStream flatMapToLong(Function<? super T, ? extends LongStream> mapper) {
        return delegate().flatMapToLong(mapper);
    }

    @Override
    public DoubleStream flatMapToDouble(Function<? super T, ? extends DoubleStream> mapper) {
        return delegate().flatMapToDouble(mapper);
    }

    @Override
    public ForwardingStream<T> distinct() {
        return withThisContext(delegate().distinct());
    }

    @Override
    public ForwardingStream<T> sorted() {
        return withThisContext(delegate().sorted());
    }

    @Override
    public ForwardingStream<T> sorted(Comparator<? super T> comparator) {
        return withThisContext(delegate().sorted(comparator));
    }

    @Override
    public ForwardingStream<T> peek(Consumer<? super T> action) {
        return withThisContext(delegate().peek(action));
    }

    @Override
    public ForwardingStream<T> limit(long maxSize) {
        return withThisContext(delegate().limit(maxSize));
    }

    @Override
    public ForwardingStream<T> skip(long n) {
        return withThisContext(delegate().skip(n));
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        delegate().forEach(action);
    }

    @Override
    public void forEachOrdered(Consumer<? super T> action) {
        delegate().forEachOrdered(action);
    }

    @Override
    public Object[] toArray() {
        return delegate().toArray();
    }

    @Override
    public <A> A[] toArray(IntFunction<A[]> generator) {
        return delegate().toArray(generator);
    }

    @Override
    public T reduce(T identity, BinaryOperator<T> accumulator) {
        return delegate().reduce(identity, accumulator);
    }

    @Override
    public Optional<T> reduce(BinaryOperator<T> accumulator) {
        return delegate().reduce(accumulator);
    }

    @Override
    public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner) {
        return delegate().reduce(identity, accumulator, combiner);
    }

    @Override
    public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner) {
        return delegate().collect(supplier, accumulator, combiner);
    }

    @Override
    public <R, A> R collect(Collector<? super T, A, R> collector) {
        return delegate().collect(collector);
    }

    @Override
    public Optional<T> min(Comparator<? super T> comparator) {
        return delegate().min(comparator);
    }

    @Override
    public Optional<T> max(Comparator<? super T> comparator) {
        return delegate().max(comparator);
    }

    @Override
    public long count() {
        return delegate().count();
    }

    @Override
    public boolean anyMatch(Predicate<? super T> predicate) {
        return delegate().anyMatch(predicate);
    }

    @Override
    public boolean allMatch(Predicate<? super T> predicate) {
        return delegate().allMatch(predicate);
    }

    @Override
    public boolean noneMatch(Predicate<? super T> predicate) {
        return delegate().noneMatch(predicate);
    }

    @Override
    public Optional<T> findFirst() {
        return delegate().findFirst();
    }

    @Override
    public Optional<T> findAny() {
        return delegate().findAny();
    }

    @Override
    public Iterator<T> iterator() {
        return delegate().iterator();
    }

    @Override
    public Spliterator<T> spliterator() {
        return delegate().spliterator();
    }

    @Override
    public boolean isParallel() {
        return delegate().isParallel();
    }

    @Override
    public ForwardingStream<T> sequential() {
        return withThisContext(delegate().sequential());
    }

    @Override
    public ForwardingStream<T> parallel() {
        return withThisContext(delegate().parallel());
    }

    @Override
    public ForwardingStream<T> unordered() {
        return withThisContext(delegate().unordered());
    }

    @Override
    public ForwardingStream<T> onClose(Runnable closeHandler) {
        return withThisContext(delegate().onClose(closeHandler));
    }

    @Override
    public void close() {
        delegate().close();
    }

    public ForwardingStream<T> contact(T... array){
        return withThisContext(StreamUtils.toStream(Iterators.concat(iterator(), Iterators.forArray(array))));
    }

    public ForwardingStream<T> contact(Iterator<T> array){
        return withThisContext(StreamUtils.toStream(Iterators.concat(iterator(), array)));
    }

    public ForwardingStream<T> contact(Iterable<T> array){
        return withThisContext(StreamUtils.toStream(Iterators.concat(iterator(), array.iterator())));
    }
}
