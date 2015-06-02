package com.novbank.ndp.core.schema.type;

/**
 * Created by hp on 2015/6/2.
 */
public class SimpleRecordTypeContainer implements RecordTypeContainer {
    @Override
    public void registerRecordType(RecordType type) {

    }

    @Override
    public void unregisterRecordType(String name) {

    }

    @Override
    public void unregisterRecordType(RecordType type) {

    }

    @Override
    public RecordType getRecordType(String className) {
        return null;
    }

    @Override
    public RecordType getRecordType(Class clazz) {
        return null;
    }
}
