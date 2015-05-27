package com.novbank.ndp.kernel.core.metadata.schema;

import org.apache.avro.Schema;
import org.apache.parquet.schema.Type;

import javax.validation.Validator;

/**
 * Created by ken on 15-5-26.
 */
public interface RecordProperty extends Comparable<RecordProperty>{
    String getName();

    String getPrefix();

    String getLocalPart();

    String getFullName();

    Schema.Field getAvroField();

    Type getParquetType();

    Validator getValidator();
}
