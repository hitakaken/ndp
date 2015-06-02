package com.novbank.ndp.core.schema.type;

import com.novbank.ndp.core.schema.namespace.NamespaceSupport;
import org.apache.avro.Schema;
import org.apache.parquet.schema.MessageType;

import java.util.function.Predicate;

/**
 * Created by CaoKe on 2015/6/1.
 */
public interface RecordType {
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


    default boolean isPrimitive(){
        return type() == Type.STRING || type() == Type.BYTES || type() == Type.INT || type() == Type.LONG
                || type() == Type.FLOAT ||type() == Type.DOUBLE || type() == Type.BOOLEAN ||type() == Type.NULL ;
    }

    default boolean isArrayLike(){
        return type() == Type.LIST || type() == Type.SET;
    }

    default boolean isObjectLike(){
        return type() == Type.RECORD || type() == Type.ENUM || type() == Type.FIXED  || type() == Type.MAP;
    }

    default boolean isUnion(){
        return type() == Type.UNION;
    }

    default boolean isPropertyContainer(){
        return type() == Type.RECORD || type() == Type.MAP;
    }

    default boolean isRecord(){
        return type() == Type.RECORD;
    }

    Schema asAvro();

}
