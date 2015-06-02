package com.novbank.ndp.core.schema.type;


import com.novbank.ndp.core.exception.NdpRuntimeException;
import com.novbank.ndp.core.schema.namespace.Namespaces;
import org.apache.avro.Schema;

import static com.novbank.ndp.core.schema.type.RecordType.Type.*;

/**
 * Created by hp on 2015/6/2.
 */
public class RecordTypes {
    public static RecordType create(RecordType.Type type){
        switch (type) {
            case STRING:  return new StringType();
            case BYTES:   return new BytesType();
            case INT:     return new IntType();
            case LONG:    return new LongType();
            case FLOAT:   return new FloatType();
            case DOUBLE:  return new DoubleType();
            case BOOLEAN: return new BooleanType();
            case NULL:    return new NullType();
            default: throw new NdpRuntimeException("Can't create a: "+type);
        }
    }

    private static class BooleanType extends AbstractRecordType {
        public BooleanType() {
            super(BOOLEAN.name().toLowerCase(), Namespaces.JAVA, BOOLEAN, Boolean.class, Schema.create(Schema.Type.BOOLEAN), null);
        }
    }

    private static class StringType extends AbstractRecordType{

    }

    private static class BytesType extends AbstractRecordType{

    }

    private static class IntType extends AbstractRecordType{

    }

    private static class LongType extends AbstractRecordType{

    }

    private static class FloatType extends AbstractRecordType{

    }

    private static class DoubleType extends AbstractRecordType{

    }

    private static class NullType extends AbstractRecordType{
        public NullType() {
            super(NULL.name().toLowerCase(), Namespaces.JAVA, NULL, null, Schema.create(Schema.Type.NULL), null);
        }
        @Override
        public boolean isInstance(Object object) {
            return object == null;
        }
        @Override
        public boolean isAssignableFrom(Class clazz) {
            return clazz == null;
        }
        @Override
        public boolean isAssignableFrom(RecordType otherType) {
            return otherType.asPojoClass() == null;
        }
    }

    private static abstract class NamedType{

    }

    private static class EnumType{

    }

    private static class ArrayType{

    }

    private static class MapType{

    }

    private static class UnionType{

    }



}
