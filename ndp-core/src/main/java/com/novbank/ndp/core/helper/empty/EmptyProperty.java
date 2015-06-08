package com.novbank.ndp.core.helper.empty;

import com.novbank.ndp.core.Constants;
import com.novbank.ndp.core.record.other.Property;
import com.novbank.ndp.core.record.other.PropertyContainer;

import java.util.NoSuchElementException;

/**
 * Created by hp on 2015/6/1.
 */
public class EmptyProperty<V> implements Property<V> {
    private static final EmptyProperty INSTANCE = new EmptyProperty();

    private EmptyProperty() {

    }

    @Override
    public String key() {
        throw Exceptions.propertyDoesNotExist();
    }

    @Override
    public V value() throws NoSuchElementException {
        throw Exceptions.propertyDoesNotExist();
    }

    @Override
    public boolean isPresent() {
        return false;
    }

    @Override
    public PropertyContainer container() {
        throw Exceptions.propertyDoesNotExist();
    }

    @Override
    public void remove() {

    }

    @Override
    public String toString() {
        return Constants.EMPTY_PROPERTY;
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(final Object object) {
        return object instanceof EmptyProperty;
    }

    public int hashCode() {
        return 1281483122;
    }

    public static <V> Property<V> instance() {
        return INSTANCE;
    }
}
