package com.novbank.ndp.core.schema.type;

/**
 * Created by hp on 2015/6/2.
 */
public interface RecordTypeContainer {
    void registerRecordType(RecordType type);

    void unregisterRecordType(String name);

    void unregisterRecordType(RecordType type);

    RecordType getRecordType(String className);

    RecordType getRecordType(Class clazz);
}
