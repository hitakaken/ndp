package com.novbank.ndp.core.schema.type;

import com.novbank.ndp.core.schema.namespace.NamespaceSupport;
import org.apache.avro.Schema;
import org.apache.parquet.schema.MessageType;

import java.util.function.Predicate;

/**
 * Created by CaoKe on 2015/6/1.
 */
public interface RecordType extends NamespaceSupport {
    enum Type{
        //Java Object
        RECORD,
        ENUM,
        //Array Like
        LIST, SET,
        MAP,
        //
        UNION, FIXED,
        //Base
        STRING, BYTES, INT, LONG, FLOAT, DOUBLE, BOOLEAN, NULL;
    }
    Type type();

    Class asPojoClass();

    Schema asAvro();

    MessageType asParquet();

    boolean isInstance(Object object);

    boolean isAssignableFrom(Class clazz);

    boolean isAssignableFrom(RecordType otherType);


}
