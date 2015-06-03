package com.novbank.ndp.core.util.function;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Provides functionality to convert between Java8 and Guava Function instances
 *
 * Created by hp on 2015/5/15.
 */
public class FunctionWrapUtils {
    public static <F,T> com.google.common.base.Function<F,T> wrap(java.util.function.Function<F,T> function){
        checkArgument(function != null, "cannot wrap null Function!");
        return function instanceof GuavaToJavaFunctionWrapper?
                ((GuavaToJavaFunctionWrapper) function).unwrap() : new JavaToGuavaFunctionWrapper<>(function);
    }

    public static <F,T> java.util.function.Function<F,T> wrap(com.google.common.base.Function<F,T> function){
        checkArgument(function != null, "cannot wrap null Function!");
        return function instanceof JavaToGuavaFunctionWrapper?
                ((JavaToGuavaFunctionWrapper) function).unwrap() : new GuavaToJavaFunctionWrapper<>(function);
    }

    public static <T> com.google.common.base.Predicate<T> wrap(java.util.function.Predicate<T> predicate){
        checkArgument(predicate != null, "cannot wrap null Predicate!");
        return predicate instanceof GuavaToJavaPredicateWrapper?
                ((GuavaToJavaPredicateWrapper) predicate).unwrap() : new JavaToGuavaPredicateWrapper<>(predicate);
    }

    public static <T> java.util.function.Predicate<T> wrap(com.google.common.base.Predicate<T> predicate){
        checkArgument(predicate != null, "cannot wrap null Predicate!");
        return predicate instanceof JavaToGuavaPredicateWrapper?
                ((JavaToGuavaPredicateWrapper) predicate).unwrap() : new GuavaToJavaPredicateWrapper<>(predicate);
    }

    public static <T> com.google.common.base.Supplier<T> wrap(java.util.function.Supplier<T> supplier){
        checkArgument(supplier != null, "cannot wrap null Supplier!");
        return supplier instanceof GuavaToJavaSupplierWrapper?
                ((GuavaToJavaSupplierWrapper) supplier).unwrap() : new JavaToGuavaSupplierWrapper<>(supplier);
    }

    public static <T> java.util.function.Supplier<T> wrap(com.google.common.base.Supplier<T> supplier){
        checkArgument(supplier != null, "cannot wrap null Supplier!");
        return supplier instanceof JavaToGuavaSupplierWrapper?
                ((JavaToGuavaSupplierWrapper) supplier).unwrap() : new GuavaToJavaSupplierWrapper<>(supplier);
    }
}
