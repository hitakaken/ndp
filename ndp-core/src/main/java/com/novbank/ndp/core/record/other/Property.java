package com.novbank.ndp.core.record.other;

import com.novbank.ndp.core.helper.empty.EmptyProperty;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by hp on 2015/6/1.
 */
public interface Property<V> {
    String key();

    V value() throws NoSuchElementException;

    boolean isPresent();

    default void ifPresent(final Consumer<? super V> consumer){
        if (this.isPresent())
            consumer.accept(this.value());
    }

    default V orElse(final V otherValue){
        return this.isPresent() ? this.value() : otherValue;
    }

    default V orElseGet(final Supplier<? extends V> valueSupplier){
        return this.isPresent() ? this.value() : valueSupplier.get();
    }

    default <E extends Throwable> V orElseThrow(final Supplier<? extends E> exceptionSupplier) throws E{
        if (this.isPresent()) return this.value();
        else
            throw exceptionSupplier.get();
    }

    PropertyContainer container();

    void remove();

    static <V> Property<V> empty() {
        return EmptyProperty.instance();
    }

    /**
     * Common exceptions to use with a property.
     */
    class Exceptions {

        private Exceptions() {
        }

        public static IllegalArgumentException propertyKeyCanNotBeEmpty() {
            return new IllegalArgumentException("Property key can not be the empty string");
        }

        public static IllegalArgumentException propertyKeyCanNotBeNull() {
            return new IllegalArgumentException("Property key can not be null");
        }

        public static IllegalArgumentException propertyValueCanNotBeNull() {
            return new IllegalArgumentException("Property value can not be null");
        }

        public static IllegalArgumentException propertyKeyCanNotBeAHiddenKey(final String key) {
            return new IllegalArgumentException("Property key can not be a hidden key: " + key);
        }

        public static IllegalStateException propertyDoesNotExist() {
            return new IllegalStateException("The property does not exist as it has no key, value, or associated element");
        }

        public static IllegalStateException propertyDoesNotExist(final PropertyContainer element, final String key) {
            return new IllegalStateException("The property does not exist as the key has no associated value for the provided element: " + element + ":" + key);
        }

        public static IllegalArgumentException dataTypeOfPropertyValueNotSupported(final Object val) {
            return new IllegalArgumentException(String.format("Property value [%s] is of type %s is not supported", val, val.getClass()));
        }

        public static IllegalStateException propertyRemovalNotSupported() {
            return new IllegalStateException("Property removal is not supported");
        }
    }
}
