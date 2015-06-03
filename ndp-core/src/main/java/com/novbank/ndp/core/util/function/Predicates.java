package com.novbank.ndp.core.util.function;


import org.jooq.lambda.Seq;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Created by CaoKe on 2015/5/17.
 */
public class Predicates {
    private Predicates() {
    }

    public static final Predicate ALWAYS_TRUE = o -> true;
    public static final Predicate ALWAYS_FALSE = o -> false;
    public static final Predicate IS_NULL = o -> o ==null;
    public static final Predicate NOT_NULL = o -> o !=null;

    /**
     * Returns a predicate that always evaluates to {@code true}.
     */
    public static <T> Predicate<T> alwaysTrue() {
        return (Predicate<T>)ALWAYS_TRUE;
    }

    /**
     * Returns a predicate that always evaluates to {@code false}.
     */
    public static <T> Predicate<T> alwaysFalse() {
        return (Predicate<T>)ALWAYS_FALSE;
    }

    /**
     * Returns a predicate that evaluates to {@code true} if the object reference
     * being tested is null.
     */
    public static <T> Predicate<T> isNull() {
        return (Predicate<T>)IS_NULL;
    }

    /**
     * Returns a predicate that evaluates to {@code true} if the object reference
     * being tested is not null.
     */
    public static <T> Predicate<T> notNull() {
        return (Predicate<T>)NOT_NULL;
    }

    /**
     * Returns a predicate that evaluates to {@code true} if the given predicate
     * evaluates to {@code false}.
     */
    public static <T> Predicate<T> not(Predicate<T> predicate) {
        return predicate.negate();
    }

    /**
     * Returns a predicate that evaluates to {@code true} if each of its
     * components evaluates to {@code true}. The components are evaluated in
     * order, and evaluation will be "short-circuited" as soon as a false
     * predicate is found. It defensively copies the iterable passed in, so future
     * changes to it won't alter the behavior of this predicate. If {@code
     * components} is empty, the returned predicate will always evaluate to {@code
     * true}.
     */
    public static <T> Predicate<T> and(
            Iterable<? extends Predicate<? super T>> components) {
        return Seq.seq(components).filter(notNull()).
                collect(() -> (Predicate<T>)ALWAYS_TRUE,  Predicate::and, Predicate::and);
    }

    /**
     * Returns a predicate that evaluates to {@code true} if each of its
     * components evaluates to {@code true}. The components are evaluated in
     * order, and evaluation will be "short-circuited" as soon as a false
     * predicate is found. It defensively copies the array passed in, so future
     * changes to it won't alter the behavior of this predicate. If {@code
     * components} is empty, the returned predicate will always evaluate to {@code
     * true}.
     */
    @SafeVarargs
    public static <T> Predicate<T> and(Predicate<? super T>... components) {
        return Stream.of(components).filter(notNull()).
                collect(() -> (Predicate<T>)ALWAYS_TRUE,  Predicate::and, Predicate::and);
    }

    /**
     * Returns a predicate that evaluates to {@code true} if both of its
     * components evaluate to {@code true}. The components are evaluated in
     * order, and evaluation will be "short-circuited" as soon as a false
     * predicate is found.
     */
    public static <T> Predicate<T> and(Predicate<? super T> first,
                                       Predicate<? super T> second) {
        return and(first,second);
    }

    /**
     * Returns a predicate that evaluates to {@code true} if any one of its
     * components evaluates to {@code true}. The components are evaluated in
     * order, and evaluation will be "short-circuited" as soon as a
     * true predicate is found. It defensively copies the iterable passed in, so
     * future changes to it won't alter the behavior of this predicate. If {@code
     * components} is empty, the returned predicate will always evaluate to {@code
     * false}.
     */
    public static <T> Predicate<T> or(
            Iterable<? extends Predicate<? super T>> components) {
        return Seq.seq(components).filter(notNull()).
                collect(() -> (Predicate<T>)ALWAYS_FALSE,  Predicate::or, Predicate::or);
    }

    /**
     * Returns a predicate that evaluates to {@code true} if any one of its
     * components evaluates to {@code true}. The components are evaluated in
     * order, and evaluation will be "short-circuited" as soon as a
     * true predicate is found. It defensively copies the array passed in, so
     * future changes to it won't alter the behavior of this predicate. If {@code
     * components} is empty, the returned predicate will always evaluate to {@code
     * false}.
     */
    public static <T> Predicate<T> or(Predicate<? super T>... components) {
        return Stream.of(components).filter(notNull()).
                collect(() -> (Predicate<T>)ALWAYS_FALSE,  Predicate::or, Predicate::or);
    }

    /**
     * Returns a predicate that evaluates to {@code true} if either of its
     * components evaluates to {@code true}. The components are evaluated in
     * order, and evaluation will be "short-circuited" as soon as a
     * true predicate is found.
     */
    public static <T> Predicate<T> or(
            Predicate<? super T> first, Predicate<? super T> second) {
        return or(first,second);
    }

    /**
     * Returns a predicate that evaluates to {@code true} if the object being
     * tested {@code equals()} the given target or both are null.
     */
    public static <T> Predicate<T> isEqual(Object targetRef){
        return Predicate.isEqual(targetRef);
    }

    /**
     * Returns a predicate that evaluates to {@code true} if the object being
     * tested is an instance of the given class. If the object being tested
     * is {@code null} this predicate evaluates to {@code false}.
     *
     * <p>If you want to filter an {@code Iterable} to narrow its type, consider
     * using {@link com.google.common.collect.Iterables#filter(Iterable, Class)}
     * in preference.
     */
    public static Predicate<Object> instanceOf(Class<?> clazz) {
        return clazz::isInstance;
    }

    /**
     * Returns a predicate that evaluates to {@code true} if the class being
     * tested is assignable from the given class.  The returned predicate
     * does not allow null inputs.
     */
    public static Predicate<Class<?>> assignableFrom(Class<?> clazz) {
        return clazz::isAssignableFrom;
    }

    /**
     * Returns a predicate that evaluates to {@code true} if the object reference
     * being tested is a member of the given collection. It does not defensively
     * copy the collection passed in, so future changes to it will alter the
     * behavior of the predicate.
     *
     * <p>This method can technically accept any {@code Collection<?>}, but using
     * a typed collection helps prevent bugs. This approach doesn't block any
     * potential users since it is always possible to use {@code
     * Predicates.<Object>in()}.
     *
     * @param target the collection that may contain the function input
     */
    public static <T> Predicate<T> in(Collection<? extends T> target) {
        return e -> {try { return  target.contains(e);} catch (Exception ex) {return false;}};
    }

    /**
     * Returns the composition of a function and a predicate. For every {@code x},
     * the generated predicate returns {@code predicate(function(x))}.
     *
     * @return the composition of the provided function and predicate
     */
    public static <A, B> Predicate<A> compose(
            Predicate<B> predicate, Function<A, ? extends B> function) {
        return o -> predicate.test(function.apply(o));
    }

    /**
     * Returns a predicate that evaluates to {@code true} if the
     * {@code CharSequence} being tested contains any match for the given
     * regular expression pattern. The test used is equivalent to
     * {@code Pattern.compile(pattern).matcher(arg).find()}
     *
     * @throws java.util.regex.PatternSyntaxException if the pattern is invalid
     */
    public static Predicate<CharSequence> containsPattern(String patternString) {
        final Pattern pattern = Pattern.compile(patternString);
        return contains(pattern);
    }

    /**
     * Returns a predicate that evaluates to {@code true} if the
     * {@code CharSequence} being tested contains any match for the given
     * regular expression pattern. The test used is equivalent to
     * {@code pattern.matcher(arg).find()}
     */
    public static Predicate<CharSequence> contains(Pattern pattern) {
        return cs -> pattern.matcher(cs).find();
    }

    /**
     * Returns a predicate that evaluates to {@code true} if the
     * {@code CharSequence} being tested contains any match for the given
     * regular expression pattern. The test used is equivalent to
     * {@code Pattern.compile(pattern).matches(arg).find()}
     *
     * @throws java.util.regex.PatternSyntaxException if the pattern is invalid
     */
    public static Predicate<CharSequence> matchesPattern(String patternString) {
        final Pattern pattern = Pattern.compile(patternString);
        return matches(pattern);
    }

    /**
     * Returns a predicate that evaluates to {@code true} if the
     * {@code CharSequence} being tested contains any match for the given
     * regular expression pattern. The test used is equivalent to
     * {@code pattern.matcher(arg).matches()}
     */
    public static Predicate<CharSequence> matches(Pattern pattern) {
        return cs -> pattern.matcher(cs).matches();
    }


}
