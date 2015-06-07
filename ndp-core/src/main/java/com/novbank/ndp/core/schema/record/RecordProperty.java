package com.novbank.ndp.core.schema.record;

import com.novbank.ndp.core.schema.type.RecordType;

/**
 * Created by hp on 2015/6/1.
 */
public interface RecordProperty  {
    String DEFAULT_LABEL = "property";

    String getDisplay();

    String getHint();

    RecordType getType();

    Object getValue(Object thisObj);

    boolean validate(Object thisObj, Object newValue);

    void setValue(Object thisObj, Object newValue);

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
