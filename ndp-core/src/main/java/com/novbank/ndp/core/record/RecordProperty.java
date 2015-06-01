package com.novbank.ndp.core.record;

/**
 * Created by hp on 2015/6/1.
 */
public interface RecordProperty<V> extends Property<V>, PropertyContainer {
    String DEFAULT_LABEL = "recordProperty";
    enum Cardinality {
        single, list, set
    }

    class Exceptions {
        private Exceptions() {
        }

        public static UnsupportedOperationException userSuppliedIdsNotSupported() {
            return new UnsupportedOperationException("Record Property does not support user supplied identifiers");
        }

        public static UnsupportedOperationException userSuppliedIdsOfThisTypeNotSupported() {
            return new UnsupportedOperationException("Record Property does not support user supplied identifiers of this type");
        }

        public static UnsupportedOperationException multiPropertiesNotSupported() {
            return new UnsupportedOperationException("Multiple properties on a record is not supported");
        }

        public static UnsupportedOperationException metaPropertiesNotSupported() {
            return new UnsupportedOperationException("Properties on a record property is not supported");
        }
    }

}
