package com.novbank.ndp.core.schema.type;


import com.novbank.ndp.core.exception.NdpRuntimeException;
import com.novbank.ndp.core.schema.namespace.Namespace;
import com.novbank.ndp.core.schema.namespace.Namespaces;
import org.apache.avro.Schema;
import org.apache.parquet.schema.MessageType;

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

    private static class StringType extends AbstractRecordType{
        public StringType() {
            super(STRING, Schema.create(Schema.Type.STRING));
        }
    }

    private static class BytesType extends AbstractRecordType{
        public BytesType() {
            super(BYTES, Schema.create(Schema.Type.BYTES));
        }
    }

    private static class IntType extends AbstractRecordType{
        public IntType() {
            super(INT, Schema.create(Schema.Type.INT));
        }
    }

    private static class LongType extends AbstractRecordType{
        public LongType() {
            super(LONG, Schema.create(Schema.Type.LONG));
        }
    }

    private static class FloatType extends AbstractRecordType{
        public FloatType() {
            super(FLOAT, Schema.create(Schema.Type.FLOAT));
        }
    }

    private static class DoubleType extends AbstractRecordType{
        public DoubleType() {
            super(DOUBLE, Schema.create(Schema.Type.DOUBLE));
        }
    }

    private static class BooleanType extends AbstractRecordType {
        public BooleanType() {
            super(BOOLEAN, Schema.create(Schema.Type.BOOLEAN));
        }
    }

    private static class NullType extends AbstractRecordType{
        public NullType() {
            super(NULL, Schema.create(Schema.Type.NULL));
        }
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
