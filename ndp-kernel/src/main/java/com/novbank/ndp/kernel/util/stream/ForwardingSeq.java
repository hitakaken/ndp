package com.novbank.ndp.kernel.util.stream;

import com.google.common.collect.ForwardingObject;
import com.google.common.collect.Iterators;
import org.jooq.lambda.Seq;
import org.jooq.lambda.tuple.Tuple2;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Created by hp on 2015/5/14.
 */
public abstract class ForwardingSeq<T> extends ForwardingObject implements Seq<T> {
    public ForwardingSeq() {}

    @Override
    protected abstract  Seq<T> delegate();

    protected abstract ForwardingSeq<T> withThisContext(Seq<T> newStream);

    @Override
    public Seq<T> filter(Predicate<? super T> predicate) {
        return withThisContext(delegate().filter(predicate));
    }

    @Override
    public <R> Seq<R> map(Function<? super T, ? extends R> mapper) {
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
    public <R> Seq<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) {
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
    public ForwardingSeq<T> distinct() {
        return withThisContext(delegate().distinct());
    }

    @Override
    public ForwardingSeq<T> sorted() {
        return withThisContext(delegate().sorted());
    }

    @Override
    public ForwardingSeq<T> sorted(Comparator<? super T> comparator) {
        return withThisContext(delegate().sorted(comparator));
    }

    @Override
    public ForwardingSeq<T> peek(Consumer<? super T> action) {
        return withThisContext(delegate().peek(action));
    }

    @Override
    public ForwardingSeq<T> limit(long maxSize) {
        return withThisContext(delegate().limit(maxSize));
    }

    @Override
    public ForwardingSeq<T> skip(long n) {
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
    public ForwardingSeq<T> sequential() {
        return withThisContext(delegate().sequential());
    }

    @Override
    public ForwardingSeq<T> parallel() {
        return withThisContext(delegate().parallel());
    }

    @Override
    public ForwardingSeq<T> unordered() {
        return withThisContext(delegate().unordered());
    }

    @Override
    public ForwardingSeq<T> onClose(Runnable closeHandler) {
        return withThisContext(delegate().onClose(closeHandler));
    }

    @Override
    public void close() {
        delegate().close();
    }

    @Override
    public Stream<T> stream() {
        return delegate().stream();
    }

    public ForwardingSeq<T> concat(ForwardingSeq<T> other) {
        return concat(other.stream());
    }

    public ForwardingSeq<T> concat(Seq<T> other) {
        return concat(other.stream());
    }

    @Override
    public ForwardingSeq<T> concat(Stream<T> other) {
        return withThisContext(delegate().concat(other));
    }

    @Override
    public ForwardingSeq<T> concat(T other) {
        return withThisContext(delegate().concat(other));
    }

    @Override
    public ForwardingSeq<T> concat(T... other) {
        return withThisContext(delegate().concat(other));
    }

    @Override
    public ForwardingSeq<T> cycle() {
        return withThisContext(delegate().cycle());
    }

    @Override
    public <U> Seq<Tuple2<T, U>> zip(Seq<U> other) {
        return delegate().zip(other);
    }

    @Override
    public <U, R> Seq<R> zip(Seq<U> other, BiFunction<T, U, R> zipper) {
        return delegate().zip(other,zipper);
    }

    @Override
    public Seq<Tuple2<T, Long>> zipWithIndex() {
        return delegate().zipWithIndex();
    }

    @Override
    public <U> U foldLeft(U seed, BiFunction<U, ? super T, U> function) {
        return delegate().foldLeft(seed,function);
    }

    @Override
    public <U> U foldRight(U seed, BiFunction<? super T, U, U> function) {
        return delegate().foldRight(seed,function);
    }

    @Override
    public <U> Seq<U> scanLeft(U seed, BiFunction<U, ? super T, U> function) {
        return delegate().scanLeft(seed,function);
    }

    @Override
    public <U> Seq<U> scanRight(U seed, BiFunction<? super T, U, U> function) {
        return delegate().scanRight(seed,function);
    }

    @Override
    public ForwardingSeq<T> reverse() {
        return withThisContext(delegate().reverse());
    }

    @Override
    public ForwardingSeq<T> shuffle() {
        return withThisContext(delegate().shuffle());
    }

    @Override
    public ForwardingSeq<T> shuffle(Random random) {
        return withThisContext(delegate().shuffle(random));
    }

    @Override
    public ForwardingSeq<T> skipWhile(Predicate<? super T> predicate) {
        return withThisContext(delegate().skipWhile(predicate));
    }

    @Override
    public ForwardingSeq<T> skipUntil(Predicate<? super T> predicate) {
        return withThisContext(delegate().skipUntil(predicate));
    }

    @Override
    public ForwardingSeq<T> limitWhile(Predicate<? super T> predicate) {
        return withThisContext(delegate().limitWhile(predicate));
    }

    @Override
    public ForwardingSeq<T> limitUntil(Predicate<? super T> predicate) {
        return withThisContext(delegate().limitUntil(predicate));
    }

    @Override
    public ForwardingSeq<T> intersperse(T value) {
        return withThisContext(delegate().intersperse(value));
    }

    private Tuple2<Seq<T>, Seq<T>> withThisContext(Tuple2<Seq<T>, Seq<T>> tuple2){
        return new Tuple2<>(withThisContext(tuple2.v1()),withThisContext(tuple2.v2()));
    }

    @Override
    public Tuple2<Seq<T>, Seq<T>> duplicate() {
        return withThisContext(delegate().duplicate());
    }

    @Override
    public Tuple2<Seq<T>, Seq<T>> partition(Predicate<? super T> predicate) {
        return withThisContext(delegate().partition(predicate));
    }

    @Override
    public Tuple2<Seq<T>, Seq<T>> splitAt(long position) {
        return withThisContext(delegate().splitAt(position));
    }


    @Override
    public Tuple2<Optional<T>, Seq<T>> splitAtHead() {
        Tuple2<Optional<T>, Seq<T>> tuple2 = delegate().splitAtHead();
        return new Tuple2<>(tuple2.v1(),withThisContext(tuple2.v2()));
    }

    @Override
    public Seq<T> slice(long from, long to) {
        return withThisContext(delegate().slice(from,to));
    }

    @Override
    public <C extends Collection<T>> C toCollection(Supplier<C> collectionFactory) {
        return delegate().toCollection(collectionFactory);
    }

    @Override
    public List<T> toList() {
        return delegate().toList();
    }

    @Override
    public Set<T> toSet() {
        return delegate().toSet();
    }

    @Override
    public <K, V> Map<K, V> toMap(Function<T, K> keyMapper, Function<T, V> valueMapper) {
        return delegate().toMap(keyMapper,valueMapper);
    }

    @Override
    public String toString(String separator) {
        return delegate().toString(separator);
    }

    @Override
    public <U extends Comparable<U>> Optional<T> minBy(Function<T, U> function) {
        return delegate().minBy(function);
    }

    @Override
    public <U> Optional<T> minBy(Function<T, U> function, Comparator<? super U> comparator) {
        return delegate().minBy(function, comparator);
    }

    @Override
    public <U extends Comparable<U>> Optional<T> maxBy(Function<T, U> function) {
        return delegate().maxBy(function);
    }

    @Override
    public <U> Optional<T> maxBy(Function<T, U> function, Comparator<? super U> comparator) {
        return delegate().maxBy(function,comparator);
    }

    @Override
    public <U> Seq<U> ofType(Class<U> type) {
        return delegate().ofType(type);
    }

    @Override
    public <U> Seq<U> cast(Class<U> type) {
        return delegate().cast(type);
    }

    @Override
    public <K> Map<K, List<T>> groupBy(Function<? super T, ? extends K> classifier) {
        return delegate().groupBy(classifier);
    }

    @Override
    public <K, A, D> Map<K, D> groupBy(Function<? super T, ? extends K> classifier, Collector<? super T, A, D> downstream) {
        return delegate().groupBy(classifier,downstream);
    }

    @Override
    public <K, D, A, M extends Map<K, D>> M groupBy(Function<? super T, ? extends K> classifier, Supplier<M> mapFactory, Collector<? super T, A, D> downstream) {
        return delegate().groupBy(classifier, mapFactory, downstream);
    }

    @Override
    public String join() {
        return delegate().join();
    }

    @Override
    public String join(CharSequence delimiter) {
        return delegate().join(delimiter);
    }

    @Override
    public String join(CharSequence delimiter, CharSequence prefix, CharSequence suffix) {
        return delegate().join(delimiter,prefix,suffix);
    }

}
