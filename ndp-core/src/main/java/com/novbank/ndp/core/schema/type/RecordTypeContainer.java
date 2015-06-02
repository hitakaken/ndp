package com.novbank.ndp.core.schema.type;

/**
 * Created by hp on 2015/6/2.
 */
public interface RecordTypeContainer {
    void bind(String className, RecordType type);

    default void bind(Class javaClass, RecordType type){
        bind(javaClass.getCanonicalName(), type);
    }

    void unbind(String className, RecordType type);

    default void unbind(Class javaClass, RecordType type){
        unbind(javaClass.getCanonicalName(),type);
    }

    void unbind(String className);

    default void unbind(Class javaClass){
        unbind(javaClass.getCanonicalName());
    }

    void unbind(RecordType type);

    RecordType getRecordType(String className);

    default RecordType getRecordType(Class clazz){
        return getRecordType(clazz.getCanonicalName());
    }
}
